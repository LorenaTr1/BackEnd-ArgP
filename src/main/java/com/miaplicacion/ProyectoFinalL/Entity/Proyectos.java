/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miaplicacion.ProyectoFinalL.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proyectos {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String nombreE;
   private String descripcionE;

   public Proyectos() {
   }

   public Proyectos(String nombreE, String descripcionE) { 
       this.nombreE = nombreE;
       this.descripcionE = descripcionE;
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public String getNombreE() {
       return nombreE;
   }

   public void setNombreE(String nombreE) {
       this.nombreE = nombreE;
   }

   public String getDescripcionE() {
       return descripcionE;
   }

   public void setDescripcionE(String descripcionE) {
       this.descripcionE = descripcionE;
   }
    
   public void getDescripcionE( String descripcionE){
       this.descripcionE = descripcionE;
   } 
}
