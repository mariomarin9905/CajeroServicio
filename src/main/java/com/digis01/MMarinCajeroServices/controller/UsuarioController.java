
package com.digis01.MMarinCajeroServices.controller;

import com.digis01.MMarinCajeroServices.DAO.IUsuario;
import com.digis01.MMarinCajeroServices.JPA.Result;
import com.digis01.MMarinCajeroServices.JPA.Usuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarioapi")
public class UsuarioController {
    
    @Autowired
    private IUsuario iUsuario;
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity UsuarioById(@PathVariable int idUsuario){
        try {
            Result result = new Result();            
            result.object = iUsuario.findById(idUsuario);
            result.correct = true;
            if (result.object == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody Usuario usuario){
        try {
            Result result = new Result();            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            return null;
        }
    }
    
}
