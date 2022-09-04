package com.sintad.challenge.web;

import com.sintad.challenge.model.Contribuyente;
import com.sintad.challenge.model.Documento;
import com.sintad.challenge.repository.ContribuyenteRepository;
import com.sintad.challenge.utils.JWTUtil;
import com.sintad.challenge.web.DTO.ContribuyenteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/contribuyente")
public class ContribuyenteController {
    @Autowired
    private ContribuyenteRepository contribuyenteRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/listar")
    List<Contribuyente> listar(@RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getValue(token) == null) {
            return new ArrayList<>();
        }
        return contribuyenteRepository.findAll();
    }
    @GetMapping("/{id}")
    Contribuyente obtener(@RequestHeader(value = "Authorization") String token,@PathVariable Integer id) {
        if (jwtUtil.getValue(token) == null) {
            return new Contribuyente();
        }
        return contribuyenteRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Contribuyente crear(@RequestHeader(value = "Authorization") String token,@Validated @RequestBody ContribuyenteDTO contribuyenteDTO) {
        if (jwtUtil.getValue(token) == null) {
            return new Contribuyente();
        }
        Contribuyente contribuyente = new ModelMapper().map(contribuyenteDTO, Contribuyente.class);
        return contribuyenteRepository.save(contribuyente);
    }


    @PutMapping("/{id}")
    Contribuyente actualizar(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable Integer id,
            @Validated @RequestBody ContribuyenteDTO contribuyenteDTO
    ) {
        if (jwtUtil.getValue(token) == null) {
            return new Contribuyente();
        }
        Contribuyente contribuyente = contribuyenteRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        new ModelMapper().map(contribuyenteDTO, contribuyente);

        contribuyenteRepository.save(contribuyente);

        return contribuyente;
    }

}
