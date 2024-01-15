package com.flowcontrolback.services;

import com.flowcontrolback.entities.Action;
import com.flowcontrolback.models.ApiResponse;
import com.flowcontrolback.repositories.ActionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository repository;

    public Action create(Action action) {
        Action response = null;
        action.setHours(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        response = repository.save(action);
        return response;
    }

}
