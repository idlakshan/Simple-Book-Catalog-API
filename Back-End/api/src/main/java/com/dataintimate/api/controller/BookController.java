package com.dataintimate.api.controller;

import com.dataintimate.api.dto.request.RequestBookDto;
import com.dataintimate.api.dto.response.ResponseBookDto;
import com.dataintimate.api.service.BookService;
import com.dataintimate.api.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    static String bookUploadPath;

    @GetMapping("/newId")
    public ResponseEntity<StandardResponse> getRegNo(){
        String id;

        if (bookService.getRowCount() > 0) {

            id = bookService.getId();
        } else {
            id = "B-000";
        }

        return new ResponseEntity<>(new StandardResponse(200,"Success",id),
                HttpStatus.OK
        );

    }



    @PostMapping(path = "image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse uploadImage(@RequestPart("bookImg") MultipartFile bookImg) {

        try {
            String projectPath = String.valueOf(new File("D:\\Edu\\Software Engineering\\IJSE\\Projects\\Book Catalog API(Data Intimate Intern)\\Front-End\\assets\\images\\Upload"));
            File uploadDir = new File(projectPath + "\\bookImages");
            uploadDir.mkdir();

            bookUploadPath = uploadDir.getAbsolutePath() + "\\" + bookImg.getOriginalFilename();
            bookImg.transferTo(new File(bookUploadPath));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new StandardResponse(201, "Images Uploaded successfully..!", null);

    }

    @PostMapping
    public ResponseEntity<StandardResponse> saveBook(@RequestBody RequestBookDto requestBookDto){
        requestBookDto.setImg(bookUploadPath);
        ResponseBookDto bookResponseDto = bookService.saveBook(requestBookDto);
        return new ResponseEntity<>(new StandardResponse(201,"Book Saved...!",bookResponseDto),
                HttpStatus.CREATED
        );
    }



}
