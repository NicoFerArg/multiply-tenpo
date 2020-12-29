package com.springboot.multiply.services;

import com.springboot.multiply.models.Historial;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface HistorialService {

    public String addHistorial(Historial historial);
    public List<Historial> getHistorialPaginatorSort(Pageable paging);

}
