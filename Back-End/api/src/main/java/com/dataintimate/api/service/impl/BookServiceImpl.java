package com.dataintimate.api.service.impl;

import com.dataintimate.api.dto.request.RequestBookDto;
import com.dataintimate.api.dto.response.ResponseBookDto;
import com.dataintimate.api.entity.Book;
import com.dataintimate.api.exception.NotFoundException;
import com.dataintimate.api.repo.BookRepo;
import com.dataintimate.api.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseBookDto saveBook(RequestBookDto requestBookDto) {
        if (!bookRepo.existsById(requestBookDto.getId())){
            Book book = modelMapper.map(requestBookDto, Book.class);
            bookRepo.save(book);
            return modelMapper.map(book,ResponseBookDto.class);
        }
        throw new NotFoundException("Book is Already Saved..!");
    }

    @Override
    public int getRowCount() {
        return bookRepo.getRowCount();
    }

    @Override
    public String getId() {
        return bookRepo.getId();
    }

    @Override
    public ArrayList<ResponseBookDto> getAllBooks() {
        List<Book> all=bookRepo.findAll();
       return modelMapper.map(all,new TypeToken<ArrayList<ResponseBookDto>>(){}.getType());

    }

    @Override
    public void deleteBook(String id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return;

        } else {
            throw new NotFoundException("Not a Book Found");
        }
    }
}
