package com.bytesMenu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PratoRequestDTO(
        @NotBlank(message = "Prato é obrigatório!")
        @Size(min = 3, max = 100)
        String name,
        @Size(min = 3, max = 300)
        String description,
        @NotNull(message = "Preço é obrigatório!")
        BigDecimal price,
        Boolean available
) {
}
