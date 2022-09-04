package com.sintad.challenge.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_tipo_documento")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoDocumento;
    private String codigo;
    private String nombre;
    private String descripcion;
    private boolean estado;


}
