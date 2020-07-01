package com.example.controllers;

import com.example.entity.BookEntity;
import com.example.repository.BookRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.inject.Inject;
import java.util.Optional;




@Controller("/api/v1/books")
@Validated
public class BooksController {

    /***
     * @param name The person's name
     * @return The greeting message
     */


    @Inject
    BookRepository bookRepository;

    @Get("/")
    @Operation(summary = "BooksController",
            description = "Access & Management on BooksController"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type="string"))
    )
    @ApiResponse(responseCode = "404", description = "Books not found in databases")
    @ApiResponse(responseCode = "500", description = "Server Internal Error")
    @Tag(name = "BOOK Controller")
    public MutableHttpResponse<Iterable<BookEntity>> all(){
        Iterable<BookEntity> books = bookRepository.findAll();
        return HttpResponse.ok(books);
    }

    @Get("/{id}")
    @Tag(name = "BOOK Controller")
    public BookEntity find(@PathVariable("id") Long id){
        return bookRepository.findById(id).orElse(null);
    }

    @Post("/add")
    public BookEntity addBook(@RequestBody BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    @Put("/{id}")
    public Optional<BookEntity> update(@PathVariable("id") Long id, @RequestBody BookEntity bookEntity){
        Optional<BookEntity> bookOpt = bookRepository.findById(id);
       if(!bookOpt.isPresent()) {
           return bookOpt;
       }
       bookEntity.setId(id);
       return Optional.of(bookRepository.save(bookEntity));
    }
    
    @Delete("/{id}")
    public Optional<BookEntity> delete(@PathVariable("id") Long id){
        Optional<BookEntity> bookOpt = bookRepository.findById(id);
        if(bookOpt.isPresent()){
            bookRepository.deleteById(id);
        }
        return bookOpt;
        
    }




}
