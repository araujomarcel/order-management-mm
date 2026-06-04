package br.com.devpasso.order_management.application.usecase.product;
import br.com.devpasso.order_management.application.dto.CreateProductCommand;
import br.com.devpasso.order_management.domain.exception.BusinessException;
import br.com.devpasso.order_management.domain.model.Product;
import br.com.devpasso.order_management.domain.repository.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductRepositoryPort repository;

    public Product execute(CreateProductCommand command) {
        if (repository.existsByName(command.name())) {
            throw new BusinessException(
                    "Produto com nome '" + command.name() + "' já existe");
        }

        Product product = new Product(
                null,
                command.name(),
                command.description(),
                command.price(),
                command.stockQuantity()
        );

        return repository.save(product);
    }
}