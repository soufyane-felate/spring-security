package com.example.demoJwt.dto;

import lombok.Data;

@Data
public class EventRequest {
    private String title;
    private String description;
    private String location;
    private String startTime;
    private String endTime;
}