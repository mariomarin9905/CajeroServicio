
package com.digis01.MMarinCajeroServices.DAO;

import com.digis01.MMarinCajeroServices.JPA.Fondo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IFondo  extends JpaRepository<Fondo, Integer> {
    
}
