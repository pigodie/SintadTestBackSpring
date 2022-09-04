package com.sintad.challenge.web;

import com.sintad.challenge.model.Entidad;
import com.sintad.challenge.repository.EntidadRepository;
import com.sintad.challenge.utils.JWTUtil;
import com.sintad.challenge.web.DTO.EntidadDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/entidad")
public class EntidadController {
    @Autowired
    private EntidadRepository entidadRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/listar")
    List<Entidad> listar(@RequestHeader(value = "Authorization") String token)
    {

        if (jwtUtil.getValue(token) == null) {
         return new ArrayList<>();
    }
        return entidadRepository.findAll();
    }

    @GetMapping("/{id}")
    Entidad obtener(@RequestHeader(value = "Authorization") String token, @PathVariable Integer id) {

        if (jwtUtil.getValue(token) == null) {
           return new Entidad();
        }
        return entidadRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Entidad crear(@RequestHeader(value = "Authorization") String token, @Validated @RequestBody EntidadDTO entidadDTO) {
        if (jwtUtil.getValue(token) == null) {
            return new Entidad();
        }
        Entidad entidad = new ModelMapper().map(entidadDTO, Entidad.class);
        return entidadRepository.save(entidad);
    }


    @PutMapping("/{id}")
    Entidad actualizar(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable Integer id,
            @Validated @RequestBody EntidadDTO entidadDTO
    ) {

        if (jwtUtil.getValue(token) == null) {
            return new Entidad();
        }
        Entidad entidad = entidadRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        new ModelMapper().map(entidadDTO, entidad);

        entidadRepository.save(entidad);

        return entidad;
    }



}
