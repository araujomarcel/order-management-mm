package br.com.devpasso.order_management.application.usecase.product;

// application/usecase/product/UpdateProductStockUseCase.java

import br.com.devpasso.order_management.domain.exception.ResourceNotFoundException;
import br.com.devpasso.order_management.domain.model.Product;
import br.com.devpasso.order_management.domain.repository.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateProductStockUseCase {

    private final ProductRepositoryPort repository;

    public Product execute(UUID id, Integer quantity) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Produto não encontrado: " + id));

        Product updated = new Product(
                existing.id(),
                existing.name(),
                existing.description(),
                existing.price(),
                quantity
        );

        return repository.save(updated);
    }
}