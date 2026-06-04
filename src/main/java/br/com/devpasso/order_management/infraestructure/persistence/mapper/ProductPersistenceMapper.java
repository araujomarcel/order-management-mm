package br.com.devpasso.order_management.infraestructure.persistence.mapper;

import br.com.devpasso.order_management.domain.model.Product;
import br.com.devpasso.order_management.infraestructure.persistence.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.id());
        entity.setName(product.name());
        entity.setDescription(product.description());
        entity.setPrice(product.price());
        entity.setStockQuantity(product.stockQuantity());
        return entity;
    }

    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity()
        );
    }
}
