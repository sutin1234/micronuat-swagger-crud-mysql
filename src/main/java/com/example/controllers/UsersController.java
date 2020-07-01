package com.example.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller("api/v1/users")
public class UsersController {

    @Get("/")
    public HttpResponse<?> users() {
        Map<String, Object> m = new HashMap<>();
        m.put("status", "200");
        m.put("body", "Users");
        return HttpResponse.ok().body(m);
    }

    @Get("/{id}")
    public HttpResponse<?> userDetail(@PathVariable int id) {
        Map<String, Object> m = new HashMap<>();
        m.put("status", "200");
        m.put("body", id);

        return HttpResponse.status(HttpStatus.OK).body(m);
    }

    @Post("/user")
    public HttpStatus userAdd() {
        return HttpStatus.OK;
    }

    @Put("/user/{id}")
    public HttpStatus userUpdate(@PathVariable int id) {
        return HttpStatus.OK;
    }

    @Delete("/user/{id}")
    public HttpStatus userDelete(@PathVariable int id) {
        return HttpStatus.OK;
    }
}