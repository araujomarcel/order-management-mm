package br.com.devpasso.order_management.infraestructure.persistence;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity //anota que a classe é um banco
@Table(name = "products") //define o nome da tabela no banco
@Getter //Lombok: cria todos os getters
@Setter //Lombok: Cria todos os setters
@NoArgsConstructor //Cria o construtor vazio (obrigatório pro JPA)
@AllArgsConstructor// Lombok: Cria o construtor com todos os campos
@Builder// Lombok: Ajuda a criar o objeto de forma limpa



public class Product {
    @Id                                      // 3. Define que este campo é a Chave Primária (PK)
    @GeneratedValue(strategy = GenerationType.UUID) // 4. Manda o Hibernate gerar o UUID automático
    private UUID id;

    @Column(nullable = false, length = 150)  // O nome não pode ser nulo e tem tamanho máximo
    private String name;

    @Column(length = 500)                    // Descrição opcional, limite de 500 caracteres
    private String description;

    @Column(nullable = false, precision = 10, scale = 2) // Define formato decimal/dinheiro (ex: 99999999.99)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false) // Converte o camelCase para o snake_case do banco
    private Integer stockQuantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    }

