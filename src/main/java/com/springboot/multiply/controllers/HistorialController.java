package com.springboot.multiply.controllers;

import com.springboot.multiply.models.Historial;
import com.springboot.multiply.services.HistorialService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    private HistorialService historialService;

    HistorialController(HistorialService historialService){
        this.historialService = historialService;
    }

    public String addHistorial(Historial historial){
        historialService.addHistorial(historial);
        return "Historial updated.";
    }

    @GetMapping
    public List<Historial> getHistorialPaginatorSort(Pageable paging){
        Historial historial = new Historial();
        historial.setDateStart(Calendar.getInstance().getTime());
        historial.setEndpoint("/historial");
        historial.setUsuario("");
        historialService.addHistorial(historial);
        return historialService.getHistorialPaginatorSort(paging);
    }

}
