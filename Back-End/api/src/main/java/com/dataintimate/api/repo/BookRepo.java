package com.dataintimate.api.repo;

import com.dataintimate.api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface BookRepo extends JpaRepository<Book,String> {

    @Query(value = "SELECT COUNT(id) AS NumberOfBooks FROM book", nativeQuery = true)
    int getRowCount();

    @Query(value = "SELECT id FROM book ORDER BY id DESC LIMIT 1",nativeQuery = true)
    String getId();

}
