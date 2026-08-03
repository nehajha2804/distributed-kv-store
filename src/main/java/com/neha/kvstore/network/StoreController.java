package com.neha.kvstore.network;

import com.neha.kvstore.node.KeyValueStore;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final KeyValueStore store;

    public StoreController(KeyValueStore store){
        this.store = store;
    }

    @PutMapping("/{key}")
    public ResponseEntity<String> put(@PathVariable String key, @RequestBody String value){
        store.put(key, value);
        return ResponseEntity.ok("Stored:" + key + " = " + value);
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> get(@PathVariable String key){
        String value =  store.get(key);
        if(value == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> delete(@PathVariable String key) {
        boolean deleted = store.delete(key);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Deleted: " + key);
    }

    @GetMapping("/_size")
    public ResponseEntity<Integer> size() {
        return ResponseEntity.ok(store.size());
    }

}
