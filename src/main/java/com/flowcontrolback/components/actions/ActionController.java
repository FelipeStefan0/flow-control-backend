package com.flowcontrolback.components.actions;

import com.flowcontrolback.models.classes.ApiResponse;
import com.flowcontrolback.models.classes.Interval;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/actions")
@RestController
public class ActionController {

  private final ActionService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<Action>>> get(ActionCriteria criteria) {
    ApiResponse<List<Action>> response = new ApiResponse<>();
    List<Action> actions = service.list(criteria);
    if(actions.isEmpty())
      response.of(HttpStatus.NOT_FOUND, "Não ocorreu entradas ou saídas hoje");
    else response.of(HttpStatus.OK, "Listado com sucesso!", actions);
    return ResponseEntity.status(response.getStatus()).body(response);
  }

//  @GetMapping("/filterByDateInterval")
//  public ResponseEntity<ApiResponse<List<Action>>> getByDateInterval(@RequestBody Interval interval) {
//    ApiResponse<List<Action>> response = new ApiResponse<>();
//    List<Action> actionsFiltered = service.getActionsByDateInterval(interval);
//    response.of(HttpStatus.OK, "Registros filtrados com sucesso!", actionsFiltered);
//    return  ResponseEntity.status(response.getStatus()).body(response);
//  }

  @PostMapping
  public ResponseEntity<ApiResponse<Action>> create(@Valid @RequestBody ActionDTO actionDTO) {
    ApiResponse<Action> response = new ApiResponse<>();
    Action createdAction = service.create(actionDTO);
    response.of(HttpStatus.OK, "Registrado com sucesso!", createdAction);
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Action>> delete(@PathVariable Long id) {
    ApiResponse<Action> response = new ApiResponse<>();
    service.delete(id);
    response.of(HttpStatus.OK, "Deletado com sucesso!");
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Action>> update(@PathVariable Long id, @RequestBody Action action) {
    ApiResponse<Action> response = new ApiResponse<>();
    Action actionChanged = service.update(id, action);
    response.of(HttpStatus.OK, "Resgistro modificado com sucesso!", actionChanged);
    return ResponseEntity.status(response.getStatus()).body(response);
  }
}
