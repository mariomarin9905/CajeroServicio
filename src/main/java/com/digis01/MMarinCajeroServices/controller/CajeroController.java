package com.digis01.MMarinCajeroServices.controller;

import com.digis01.MMarinCajeroServices.DAO.IFondo;
import com.digis01.MMarinCajeroServices.DAO.IUsuario;
import com.digis01.MMarinCajeroServices.JPA.Fondo;
import com.digis01.MMarinCajeroServices.JPA.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("cajeroapi")
public class CajeroController {
    @Autowired
    private IFondo iFondo;
    @Autowired
    private IUsuario iUsuario;    
    private record Monto(Double cantidad){}    
    
    @PutMapping    
    public ResponseEntity AtualizaFondo(@RequestBody Monto monto){
        try {
            Result result = new Result();
            List<Fondo> fondos = this.iFondo.findAll();
            result.object = fondos;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return null;
        }        
    }
   
}
