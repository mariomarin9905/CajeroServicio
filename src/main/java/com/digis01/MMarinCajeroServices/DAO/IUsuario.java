
package com.digis01.MMarinCajeroServices.DAO;

import com.digis01.MMarinCajeroServices.JPA.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuario extends JpaRepository<Usuario, Integer> {
    
}
