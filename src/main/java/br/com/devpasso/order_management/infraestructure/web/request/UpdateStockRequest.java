package br.com.devpasso.order_management.infraestructure.web.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateStockRequest(
        @NotNull
        @PositiveOrZero
        Integer quantity
) {}