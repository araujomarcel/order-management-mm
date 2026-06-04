package br.com.devpasso.order_management.infraestructure.persistence.repository;

import br.com.devpasso.order_management.domain.model.Product;
import br.com.devpasso.order_management.domain.repository.ProductRepositoryPort;
import br.com.devpasso.order_management.infraestructure.persistence.ProductEntity;
import br.com.devpasso.order_management.infraestructure.persistence.mapper.ProductPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public void delete(Product product) {
        jpaRepository.deleteById(product.id());
    }
}