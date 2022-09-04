package com.sintad.challenge.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_tipo_contribuyente")
public class Contribuyente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoContribuyente;
    private String nombre;
    private boolean estado;
}
