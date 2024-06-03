package com.flowcontrolback.components.actions;

import com.flowcontrolback.models.classes.ApiResponse;
import com.flowcontrolback.models.classes.Interval;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/actions")
@RestController
@CrossOrigin(
        origins = {"http://192.168.0.100:8100", "http://localhost:8100"},
        allowedHeaders = "*"
)
public class ActionController {

  private final ActionService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<Action>>> list() {
    ApiResponse<List<Action>> response = new ApiResponse<>();
    List<Action> actions = service.list();
    response.of(HttpStatus.OK, "Listado com sucesso!", actions);
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @GetMapping("/filterByDateInterval")
  public ResponseEntity<ApiResponse<List<Action>>> filterByDateInterval(@RequestBody Interval interval) {
    ApiResponse<List<Action>> response = new ApiResponse<>();
    List<Action> actionsFiltered = service.getActionsByDateInterval(interval);
    response.of(HttpStatus.OK, "Registros filtrados com sucesso!", actionsFiltered);
    return  ResponseEntity.status(response.getStatus()).body(response);
  }

  @GetMapping("/getByDate")
  public ResponseEntity<ApiResponse<List<Action>>> getByDate(ActionCriteria criteria) {
    ApiResponse<List<Action>> response = new ApiResponse<>();
    response = service.getByDay(criteria);
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Action>> create(@RequestBody Action action) {
    ApiResponse<Action> response = new ApiResponse<>();
    Action createdAction = service.create(action);
    response.of(HttpStatus.OK, "Registrado com sucesso!", createdAction);
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @DeleteMapping
  public ResponseEntity<ApiResponse<Action>> delete(@RequestParam Long id) throws Exception {
    ApiResponse<Action> response = new ApiResponse<>();
    service.delete(id);
    response.of(HttpStatus.OK, "Deletado com sucesso!");
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PutMapping
  public ResponseEntity<ApiResponse<Action>> update(@RequestBody Action action) {
    ApiResponse<Action> response = new ApiResponse<>();
    Action actionChanged = service.update(action);
    response.of(HttpStatus.OK, "Resgistro modificado com sucesso!", actionChanged);
    return ResponseEntity.status(response.getStatus()).body(response);
  }
}
