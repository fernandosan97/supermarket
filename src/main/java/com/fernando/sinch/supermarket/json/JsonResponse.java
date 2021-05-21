package com.fernando.sinch.supermarket.json;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class JsonResponse implements Serializable {
    private final Map<String, Object> results;
    private ResponseStatus response;
    private String message;

    public JsonResponse() {
        response = ResponseStatus.SUCCESS;
        results = new HashMap<>();
    }

    public JsonResponse(ResponseStatus response) {
        this.response = response;
        results = new HashMap<>();
    }

    public void put(String key, Object value) {
        results.put(key, value);
    }
}
