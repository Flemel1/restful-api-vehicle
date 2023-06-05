package com.example.restfulapivehicle.responses;

import java.util.Map;

public class ResponseBasic {
    private Map<String, Object> data;

    public ResponseBasic(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }
    
}
