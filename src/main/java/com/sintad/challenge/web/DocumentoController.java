package com.sintad.challenge.web;

import com.sintad.challenge.model.Documento;
import com.sintad.challenge.model.Entidad;
import com.sintad.challenge.repository.DocumentoRepository;
import com.sintad.challenge.utils.JWTUtil;
import com.sintad.challenge.web.DTO.DocumentoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/documento")
public class DocumentoController {
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/listar")
    List<Documento> listar(@RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getValue(token) == null) {
            return new ArrayList<>();
        }
        return documentoRepository.findAll();
    }
    @GetMapping("/{id}")
    Documento obtener(@PathVariable Integer id,@RequestHeader(value = "Authorization") String token) {

        if (jwtUtil.getValue(token) == null) {
            return new Documento();
        }
        return documentoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Documento crear(@RequestHeader(value = "Authorization") String token,@Validated @RequestBody DocumentoDTO documentoDTO) {
        if (jwtUtil.getValue(token) == null) {
            return new Documento();
        }
        Documento documento = new ModelMapper().map(documentoDTO, Documento.class);
        return documentoRepository.save(documento);
    }


    @PutMapping("/{id}")
    Documento actualizar(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable Integer id,
            @Validated @RequestBody DocumentoDTO documentoDTO
    ) {
        if (jwtUtil.getValue(token) == null) {
            return new Documento();
        }
        Documento documento = documentoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        new ModelMapper().map(documentoDTO, documento);

        documentoRepository.save(documento);

        return documento;
    }

}
