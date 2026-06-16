package com.bytesMenu.controller;

import com.bytesMenu.dto.AtualizarDisponivelDTO;
import com.bytesMenu.dto.PratoRequestDTO;
import com.bytesMenu.entity.Prato;
import com.bytesMenu.service.PratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pratos")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @PostMapping
    public ResponseEntity<Prato> criar(@Valid @RequestBody PratoRequestDTO dto) {
        Prato prato = pratoService.criarPrato(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prato);
    }

    @GetMapping
    public ResponseEntity<List<Prato>> listarTodos() {
        return ResponseEntity.ok(pratoService.listarTodos());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Prato>> listarDisponiveis() {
        return ResponseEntity.ok(pratoService.buscarPratosDisponiveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pratoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prato> atualizar(@PathVariable Long id, @RequestBody PratoRequestDTO prato) {
        return ResponseEntity.ok(pratoService.atualizar(prato, id));
    }

    @PatchMapping("/{id}/disponivel")
    public ResponseEntity<Prato> alterarDisponibilidade(@PathVariable Long id) {
        return ResponseEntity.ok(pratoService.alterarDisponibilidade(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Prato> deletar(@PathVariable Long id) {
        pratoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
