package com.rabbitmqproject.estoqueconsumer.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class EstoqueDTO implements Serializable {
    private String codigoProduto;
    private int quantidade;
}
