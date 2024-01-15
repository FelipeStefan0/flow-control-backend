package com.flowcontrolback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private int status = HttpStatus.OK.value();
    private String message = "";
    private T data = null;

    public ApiResponse<T> of(T data) {
        this.data = data;
        return this;
    }

    public ApiResponse<T> of(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.message = message;
        return this;
    }

    public ApiResponse<T> of(String message, T data) {
        this.message = message;
        this.data = data;
        return this;
    }

    public ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
        return this;
    }

}
