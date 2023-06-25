package com.dataintimate.api.service;

import com.dataintimate.api.dto.request.RequestBookDto;
import com.dataintimate.api.dto.response.ResponseBookDto;

public interface BookService {

    ResponseBookDto saveBook(RequestBookDto bookRequestDto);

    int getRowCount();

    String getId();
}
