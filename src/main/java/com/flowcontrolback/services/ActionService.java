package com.flowcontrolback.services;

import com.flowcontrolback.entities.Action;
import com.flowcontrolback.repositories.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository repository;

    public List<Action> list() {
        List<Action> response = null;
        response = repository.findAll();
        return response;
    }
    
    public Action create(Action action) {
        Action response = null;
        action.setHours(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        response = repository.save(action);
        return response;
    }

}
