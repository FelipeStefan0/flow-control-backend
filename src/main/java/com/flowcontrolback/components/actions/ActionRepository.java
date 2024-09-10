package com.flowcontrolback.components.actions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action> {

    Optional<Action> findById(Long id);

}
