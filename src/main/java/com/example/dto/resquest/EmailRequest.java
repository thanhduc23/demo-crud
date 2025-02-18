package com.example.dto.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {

    private String to;
    private String subject;
    private String name;
    private String message;
    private String signature;
}
