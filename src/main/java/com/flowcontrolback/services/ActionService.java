package com.flowcontrolback.services;

import com.flowcontrolback.entities.Action;
import com.flowcontrolback.repositories.ActionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Data
public class ActionService {

    @Autowired
    private ActionRepository repository;

    public ResponseEntity<Action> create(Action action) {
        action.setHours(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        Action response = repository.save(action);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
