package com.example.dto.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDetailResponse implements Serializable {
    private Long id;
    private String name;
    private String email;
}
