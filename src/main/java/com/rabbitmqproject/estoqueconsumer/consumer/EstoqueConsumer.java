package com.rabbitmqproject.estoqueconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmqproject.estoqueconsumer.constants.RabbitMQConstants;
import com.rabbitmqproject.estoqueconsumer.dto.EstoqueDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EstoqueConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConstants.ESTOQUE_QUEUE)
    public void receiveEstoque(String message) throws JsonProcessingException {
        EstoqueDTO estoqueDTO = objectMapper.readValue(message, EstoqueDTO.class);
        log.info("jsonMessage: ".concat(message));
        log.info("Item de estoque recebido: "
                .concat("codigoProduto: ").concat(estoqueDTO.getCodigoProduto())
                .concat(", quantidade: ").concat(String.valueOf(estoqueDTO.getQuantidade()))
        );
    }
}
