package com.flowcontrolback.components.actions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long>, JpaSpecificationExecutor<Action> {}
