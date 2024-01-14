package com.flowcontrolback.repositories;

import com.flowcontrolback.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
