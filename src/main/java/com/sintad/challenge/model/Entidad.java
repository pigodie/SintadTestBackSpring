package com.sintad.challenge.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_entidad")
public class Entidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntidad;
    @OneToOne
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "idTipoDocumento")
    private Documento documento;
    private String nroDocumento;
    private String razonSocial;
    private String nombreComercial;
    @OneToOne
    @JoinColumn(name = "idTipoContribuyente", referencedColumnName = "idTipoContribuyente")
    private Contribuyente contribuyente;
    private String direccion;
    private String telefono;
    private boolean estado;
}
