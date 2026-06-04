package br.com.devpasso.order_management.infraestructure.persistence.repository;

import br.com.devpasso.order_management.domain.repository.ProductRepositoryPort;
import br.com.devpasso.order_management.domain.model.Product; // Seu modelo de domínio
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository // <-- Isso faz o Spring encontrar o Bean que estava faltando!
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        // Busca as entidades do banco e converte para o modelo de domínio puro
        return productJpaRepository.findAll(pageable)
                .map(entity -> Product.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .price(entity.getPrice())
                        .stockQuantity(entity.getStockQuantity())
                        .createdAt(entity.getCreatedAt())
                        .build());
    }
}