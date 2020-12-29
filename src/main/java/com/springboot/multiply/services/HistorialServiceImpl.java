package com.springboot.multiply.services;

import com.springboot.multiply.models.Historial;
import com.springboot.multiply.repositories.HistorialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialServiceImpl implements HistorialService{

    HistorialRepository historialRepository;

    public HistorialServiceImpl(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public String addHistorial(Historial historial) {
        historialRepository.save(historial);
        return "";
    }

    public List<Historial> getHistorialPaginatorSort(Pageable paging) {

        Page<Historial> pagedResult = historialRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }else {
            return new ArrayList<>();
        }
    }
}
