package com.springboot.multiply.repositories;

import com.springboot.multiply.models.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialRepository extends JpaRepository<Historial, Integer> {
}
