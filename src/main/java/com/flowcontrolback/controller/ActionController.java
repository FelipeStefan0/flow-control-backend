package com.flowcontrolback.controller;

import com.flowcontrolback.entities.Action;
import com.flowcontrolback.models.ApiResponse;
import com.flowcontrolback.services.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/action")
@RestController
public class ActionController {

    private final ActionService service;

    @GetMapping
    public ResponseEntity<Action> getAll() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Action>> create(@RequestBody Action action) {
        ApiResponse<Action> response = new ApiResponse<>();
        Action createdAction = service.create(action);
        response.of(HttpStatus.OK, "Entrada registrada com sucesso!", createdAction);
        return ResponseEntity.status(response.getStatus()).body(response);""
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
