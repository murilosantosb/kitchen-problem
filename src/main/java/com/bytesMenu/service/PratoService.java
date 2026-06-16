package com.bytesMenu.service;

import com.bytesMenu.dto.AtualizarDisponivelDTO;
import com.bytesMenu.dto.PratoRequestDTO;
import com.bytesMenu.entity.Prato;
import com.bytesMenu.repository.PratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    public List<Prato> listarTodos() {
        return pratoRepository.findAll();
    }

    public Prato buscarPorId(Long id) {
        return pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado com id: " + id));
    }

    public List<Prato> buscarPratosDisponiveis() {
        return pratoRepository.findByDisponivelTrue();
    }

    public Prato criarPrato(PratoRequestDTO dto) {
        Prato prato = Prato.builder()
                .nome(dto.name())
                .descricao(dto.description())
                .preco(dto.price())
                .disponivel(dto.available() != null ? dto.available() : true)
                .build();

        return pratoRepository.save(prato);
    }

    public Prato atualizar(PratoRequestDTO dto, Long id) {
        Prato prato = buscarPorId(id);

        prato.setNome(dto.name());
        prato.setDescricao(dto.description());
        prato.setPreco(dto.price());
        prato.setDisponivel(dto.available() != null ? dto.available() : prato.getDisponivel());

        return pratoRepository.save(prato);
    }

    public Prato alterarDisponibilidade(Long id) {
        Prato prato = buscarPorId(id);
        prato.setDisponivel(!prato.getDisponivel());

        return pratoRepository.save(prato);
    }

    public void deletar(Long id) {
        if (!pratoRepository.existsById(id)) {
            throw new RuntimeException("Prato não encontrado com id: " + id);
        }
        pratoRepository.deleteById(id);
    }
}
