package com.springboot.multiply.controllers;

import com.springboot.multiply.models.Historial;
import com.springboot.multiply.models.Numero;
import com.springboot.multiply.services.HistorialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/multiply")
public class MultiplyController {

    private HistorialService historialService;

    MultiplyController(HistorialService historialService){ this.historialService = historialService; }

    @PostMapping
    public Integer multiply(@RequestBody Numero numeros){

        Historial historial = new Historial();
        historial.setEndpoint("/multiply");
        historial.setDateStart(Calendar.getInstance().getTime());
        historial.setUsuario("");
        historialService.addHistorial(historial);
        return numeros.getNum1() * numeros.getNum2();
    }


}
