package br.com.devpasso.order_management.infraestructure.web.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder // O Builder do Lombok funciona muito bem com Records!
public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        LocalDateTime createdAt // Adicionado o campo que faltava
) {}