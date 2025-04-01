package com.example.orchestrator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.example.orchestrator.dto.UserDTO;

@Service
public class OrchestratorService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${app.jms.queueCreateUser}")
    private String queueCreateUser;

    @Value("${app.jms.queueCreateTypeUser}")
    private String queueCreateTypeUser;

    @Value("${app.jms.queueOrchestratorEvents}")
    private String queueOrchestratorEvents;

    public void startUserCreation(UserDTO userDTO) {
        // 1. Enviar mensaje al UserService
        userDTO.setStatus("CREATE_USER");
        jmsTemplate.convertAndSend(queueCreateUser, userDTO, message -> {
            message.setStringProperty("_type", "user");
            return message;
        });
    }

    public void continueWithTypeUserCreation(UserDTO userDTO) {
        // 2. Enviar mensaje al TypeUserService
        userDTO.setStatus("CREATE_TYPEUSER");
        jmsTemplate.convertAndSend(queueCreateTypeUser, userDTO);
    }

    public void handleError(UserDTO userDTO) {
        // 3. Logica de rollback o compensación (opcional)
        // Por ejemplo, podrías enviar un mensaje a una cola de rollback
        // jmsTemplate.convertAndSend("queueUserRollback", userDTO);
        System.out.println("Ocurrió un error. Lanzando compensación para el usuario: " + userDTO.getId());
    }
}