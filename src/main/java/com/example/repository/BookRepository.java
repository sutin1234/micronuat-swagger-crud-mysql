package com.example.repository;

import com.example.entity.BookEntity;
import io.micronaut.data.repository.CrudRepository;

public interface BookRepository  extends CrudRepository<BookEntity, Long> {
    BookEntity find(String title);
}
