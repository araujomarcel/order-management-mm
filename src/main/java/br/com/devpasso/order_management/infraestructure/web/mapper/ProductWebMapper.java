package br.com.devpasso.order_management.infraestructure.web.mapper;// infrastructure/web/mapper/ProductWebMapper.java

import br.com.devpasso.order_management.application.dto.CreateProductCommand;
import br.com.devpasso.order_management.application.dto.UpdateProductCommand;
import br.com.devpasso.order_management.domain.model.Product;
import br.com.devpasso.order_management.infraestructure.web.request.CreateProductRequest;
import br.com.devpasso.order_management.infraestructure.web.request.UpdateProductRequest;
import br.com.devpasso.order_management.infraestructure.web.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper {

    public CreateProductCommand toCommand(CreateProductRequest request) {
        return new CreateProductCommand(
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity()
        );
    }

    public UpdateProductCommand toCommand(UpdateProductRequest request) {
        return new UpdateProductCommand(
                request.name(),
                request.description(),
                request.price()
        );
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.name(),
                product.description(),
                product.price(),
                product.stockQuantity()
        );
    }
}