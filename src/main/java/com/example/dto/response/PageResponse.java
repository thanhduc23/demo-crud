package com.example.dto.response;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Builder
@Getter
public class PageResponse<T>{
    int pageNo;
    int pageSize;
    int totalPage;
    T items;
}
