/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miaplicacion.ProyectoFinalL.Controller;

import com.miaplicacion.ProyectoFinalL.Dto.dtoHabilidades;
import com.miaplicacion.ProyectoFinalL.Entity.Habilidades;
import com.miaplicacion.ProyectoFinalL.Security.Controller.Mensaje;
import com.miaplicacion.ProyectoFinalL.Service.SHabilidades;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habilidades")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://frontloren.web.app")
public class CHabilidades {
    @Autowired 
    SHabilidades sHabilidades;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidades>> list(){
        List<Habilidades> list = sHabilidades.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHabilidades dtohabilidades){
       if(StringUtils.isBlank(dtohabilidades.getNombre())) 
           return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       if(sHabilidades.existsByNombre(dtohabilidades.getNombre()))
           return new ResponseEntity( new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
       
       Habilidades habiliDades = new Habilidades(dtohabilidades.getNombre(), dtohabilidades.getPorcentaje());
       sHabilidades.save(habiliDades);
       
       return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHabilidades dtohabilidades){
        if(!sHabilidades.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        if(sHabilidades.existsByNombre(dtohabilidades.getNombre()) && sHabilidades.getByNombre(dtohabilidades.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtohabilidades.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST); 
        
        Habilidades habiliDades = sHabilidades.getOne(id).get();
        habiliDades.setNombre(dtohabilidades.getNombre());
        habiliDades.setPorcentaje(dtohabilidades.getPorcentaje());
        
        sHabilidades.save(habiliDades);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHabilidades.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        sHabilidades.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidades> getById(@PathVariable("id") int id){
        if(!sHabilidades.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Habilidades habiliDades = sHabilidades.getOne(id).get();
        return new ResponseEntity(habiliDades, HttpStatus.OK);
    }
}
