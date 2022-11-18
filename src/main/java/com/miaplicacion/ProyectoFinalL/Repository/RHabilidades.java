/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.miaplicacion.ProyectoFinalL.Repository;

import com.miaplicacion.ProyectoFinalL.Entity.Habilidades;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHabilidades extends JpaRepository<Habilidades, Integer> {
    Optional<Habilidades> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}
