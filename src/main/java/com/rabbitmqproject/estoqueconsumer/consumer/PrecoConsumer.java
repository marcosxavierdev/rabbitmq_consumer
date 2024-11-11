package com.rabbitmqproject.estoqueconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmqproject.estoqueconsumer.constants.RabbitMQConstants;
import com.rabbitmqproject.estoqueconsumer.dto.PrecoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PrecoConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConstants.PRECO_QUEUE)
    public void receivePreco(String message) throws JsonProcessingException {
        PrecoDTO precoDTO = objectMapper.readValue(message, PrecoDTO.class);
        log.info("jsonMessage: ".concat(message));
        log.info("Atualizacao de valor recebido: "
                .concat("codigoProduto: ").concat(precoDTO.getCodigoProduto())
                .concat(", valor: ").concat(String.valueOf(precoDTO.getValor()))
        );
    }
}
