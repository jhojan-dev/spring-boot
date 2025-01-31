package com.company.books.backend.model.dao;

import com.company.books.backend.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibroDao extends JpaRepository<Libro, Long> {

}
