package br.com.devpasso.order_management.infraestructure.web.controller;

import br.com.devpasso.order_management.application.dto.CreateProductCommand;
import br.com.devpasso.order_management.application.dto.UpdateProductCommand;
import br.com.devpasso.order_management.application.usecase.product.*;
import br.com.devpasso.order_management.domain.model.Product;
import br.com.devpasso.order_management.infraestructure.web.mapper.ProductWebMapper;
import br.com.devpasso.order_management.infraestructure.web.request.CreateProductRequest;
import br.com.devpasso.order_management.infraestructure.web.request.UpdateProductRequest;
import br.com.devpasso.order_management.infraestructure.web.request.UpdateStockRequest;
import br.com.devpasso.order_management.infraestructure.web.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ProductWebMapper mapper;

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest request) {
        CreateProductCommand command = mapper.toCommand(request);
        Product created = createProductUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toResponse(created));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca produto por ID")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id) {
        Product product = getProductByIdUseCase.execute(id);
        return ResponseEntity.ok(mapper.toResponse(product));
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    public ResponseEntity<Page<ProductResponse>> listAll(Pageable pageable) {
        return ResponseEntity.ok(listProductsUseCase.execute(pageable)
                .map(mapper::toResponse));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity<ProductResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProductRequest request) {
        UpdateProductCommand command = mapper.toCommand(request);
        Product updated = updateProductUseCase.execute(id, command);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Atualiza estoque do produto")
    public ResponseEntity<ProductResponse> updateStock(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateStockRequest request) {
        Product updated = updateProductStockUseCase.execute(id, request.quantity());
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um produto")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteProductUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}