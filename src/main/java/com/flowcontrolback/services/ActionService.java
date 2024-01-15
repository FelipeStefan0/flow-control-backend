package com.flowcontrolback.services;

import com.flowcontrolback.entities.Action;
import com.flowcontrolback.repositories.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public void delete(Long id) throws Exception {
        Optional<Action> action = repository.findById(id);
        if(action.isEmpty())
            throw new Exception("O registro informado não existe!");
        else {
            repository.delete(action.get());
        }
    }

    public Action edit(Action action) {
        LocalDateTime hours = repository.findById(action.getId()).get().getHours();
        action.setHours(hours);
        Action response = action;
        response = repository.save(action);
        return response;
    }
}
