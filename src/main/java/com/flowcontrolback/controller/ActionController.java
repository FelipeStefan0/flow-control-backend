package com.flowcontrolback.controller;

import com.flowcontrolback.entities.Action;
import com.flowcontrolback.services.ActionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RequestMapping("/action")
@RestController
public class ActionController {

    @Autowired
    private ActionService service;

    @GetMapping
    public ResponseEntity<Action> getAll() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Action> create(@RequestBody Action action) {
        ResponseEntity<Action> response = service.create(action);
        return response;
    }

    @DeleteMapping
    public ResponseEntity<Action> delete() {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Action> edit() {
        return ResponseEntity.ok().build();
    }

}
