package com.franquicia.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "franquicia")
public class FranquiciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idFranquicia;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
