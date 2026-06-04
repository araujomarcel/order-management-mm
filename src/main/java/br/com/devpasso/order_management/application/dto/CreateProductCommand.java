package br.com.devpasso.order_management.application.dto;

import java.math.BigDecimal;

public record CreateProductCommand(
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity
) {}