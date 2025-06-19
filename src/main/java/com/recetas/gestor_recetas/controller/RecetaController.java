package com.recetas.gestor_recetas.controller;

import com.recetas.gestor_recetas.dto.RecetaDTO;
import com.recetas.gestor_recetas.exception.ValidacionException;
import com.recetas.gestor_recetas.model.Receta;
import com.recetas.gestor_recetas.service.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {
    private final RecetaService recetaService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Receta>> listarRecetas(){
        return ResponseEntity.ok(recetaService.obtenerRecetas());
    }

    @PostMapping
    public ResponseEntity<Receta> crearReceta(
            @Valid @RequestBody RecetaDTO recetaDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidacionException(result.getFieldErrors());
        }

        Receta nuevaReceta = modelMapper.map(recetaDTO, Receta.class);
        return new ResponseEntity<>(
                recetaService.crearReceta(nuevaReceta, recetaDTO.getAutorId()),
                HttpStatus.CREATED
        );
    }

}