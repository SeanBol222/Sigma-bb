package com.bolivar.bioingenieria.app.sigma_bb.reports_hexagon;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {
    @RabbitListener(queues = "reports-all-queue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
