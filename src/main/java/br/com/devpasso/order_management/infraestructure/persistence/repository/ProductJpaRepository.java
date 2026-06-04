package br.com.devpasso.order_management.infraestructure.persistence.repository;

import br.com.devpasso.order_management.infraestructure.persistence.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByNameIgnoreCase(String name);
}