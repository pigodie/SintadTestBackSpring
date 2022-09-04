package com.sintad.challenge.web.DTO;


import com.sintad.challenge.model.Contribuyente;
import com.sintad.challenge.model.Documento;
import lombok.Data;

@Data
public class EntidadDTO {


        private Documento documento;
        private String nroDocumento;
        private String razonSocial;
        private String nombreComercial;
        private Contribuyente contribuyente;
        private String direccion;
        private String telefono;
        private boolean estado;

}
