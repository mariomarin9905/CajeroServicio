package com.digis01.MMarinCajeroServices.controller;

import com.digis01.MMarinCajeroServices.DAO.IFondo;
import com.digis01.MMarinCajeroServices.DAO.IUsuario;
import com.digis01.MMarinCajeroServices.JPA.Fondo;
import com.digis01.MMarinCajeroServices.JPA.Result;
import com.digis01.MMarinCajeroServices.JPA.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cajeroapi")
public class CajeroRestController {

    @Autowired
    private IFondo iFondo;
    @Autowired
    private IUsuario iUsuario;

    @PutMapping("/{idUsuario}")
    public ResponseEntity AtualizaFondo(@RequestBody Result monto, @PathVariable int idUsuario) {
        try {
            Result result = new Result();
            double retiro = (double) monto.object;
            Usuario usuario = this.iUsuario.findById(idUsuario).get();
            if (usuario.getSaldo() < retiro) {
                result.correct = false;
                result.errorMessage = "Saldo de usuario insuficiente";
                return ResponseEntity.badRequest().body(result);
            }
            List<Fondo> fondos = this.iFondo.findAll();            
            double totalFondo = this.SumaFondos(fondos);
            if (retiro > totalFondo) {
                result.correct = false;
                result.errorMessage = "Fondos del cajero insuficiente";
                return ResponseEntity.status(404).body(result);
            }
            if (retiro % fondos.get(fondos.size() - 1).getDenominacion() != 0) {
                result.correct = false;
                result.errorMessage = "No es posible hacer la operacion dado la menor denominacion ";
                return ResponseEntity.status(400).body(result);
            }
            List<Fondo> resultado = this.calculaRetiro(retiro, fondos);
            this.iFondo.saveAll(fondos);
            usuario.setSaldo((usuario.getSaldo()-retiro));
            this.iUsuario.save(usuario);
            result.correct = true;
            result.object = resultado;
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return null;
        }
    }

    private double SumaFondos(List<Fondo> fondos) {
        double suma = 0;
        for (Fondo fondo : fondos) {
            double operacion = (fondo.getCantidad() * fondo.getDenominacion());
            suma += (double) operacion;
        }
        return suma;
    }
    
    private List<Fondo> calculaRetiro(double retiro, List<Fondo> fondos) {
        List<Fondo> resultado = new ArrayList();
        int i = 0;
        while ((retiro != 0) && (i < fondos.size() - 1)) {
            Fondo fondoAux = new Fondo();
            Fondo fondo = fondos.get(i);
            double divisor = Math.min(retiro / fondo.getDenominacion(), fondo.getCantidad());
            if (divisor >= 2) {
                int multiplo = (int) divisor;
                retiro -= (multiplo * fondo.getDenominacion());
                fondoAux.setIdFondo(fondo.getIdFondo());
                fondoAux.setTipo(fondo.getTipo());
                fondoAux.setCantidad((int) divisor);
                fondoAux.setDenominacion(fondo.getDenominacion());
                resultado.add(fondoAux);
                fondo.setCantidad(fondo.getCantidad() - multiplo);
            }
            i++;
        }
        return resultado;
    }
}
