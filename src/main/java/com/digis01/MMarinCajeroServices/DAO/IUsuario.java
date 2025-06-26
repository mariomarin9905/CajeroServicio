
package com.digis01.MMarinCajeroServices.DAO;

import com.digis01.MMarinCajeroServices.JPA.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IUsuario extends JpaRepository<Usuario, Integer> {
    
    @Query("FROM Usuario U  WHERE U.UserName = :userName")
    Usuario findByUserName(String userName);
}
