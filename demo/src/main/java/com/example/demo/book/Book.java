package com.example.demo.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Book {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "o campo título é obrigatório")
    String title;

    @NotBlank(message = "o campo autor é obrigatório")
    String autor;

    @NotBlank(message = "o campo editora é obrigatório")
    String editora;

    @Size(min = 10, message = "a descrição deve ter pelo menos 10 caracteres")
    String description;

}