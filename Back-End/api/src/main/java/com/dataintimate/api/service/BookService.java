package com.dataintimate.api.service;

import com.dataintimate.api.dto.request.RequestBookDto;
import com.dataintimate.api.dto.response.ResponseBookDto;

import java.util.ArrayList;

public interface BookService {

    ResponseBookDto saveBook(RequestBookDto bookRequestDto);

    int getRowCount();

    String getId();

    ArrayList<ResponseBookDto> getAllBooks();

    void deleteBook(String id);

}
