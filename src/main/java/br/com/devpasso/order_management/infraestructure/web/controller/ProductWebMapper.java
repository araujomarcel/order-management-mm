package br.com.devpasso.order_management.infraestructure.web.controller;

import br.com.devpasso.order_management.domain.model.Product;
import br.com.devpasso.order_management.infraestructure.web.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper {

    public ProductResponse toResponse(Product product) {
        if (product == null) return null;

        // O builder mapeia os dados do domínio para os campos do record
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .createdAt(product.getCreatedAt())
                .build();
    }
}