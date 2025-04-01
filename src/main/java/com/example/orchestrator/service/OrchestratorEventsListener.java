package com.example.orchestrator.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.orchestrator.dto.UserDTO;

@Component
public class OrchestratorEventsListener {

    @Autowired
    private OrchestratorService orchestratorService;

    @JmsListener(destination = "${app.jms.queueOrchestratorEvents}")
    public void onOrchestratorEvent(UserDTO userDTO) {
        String status = userDTO.getStatus();

        switch (status) {
            case "USER_CREATED":
                // Ok, pasamos a crear TypeUser
                orchestratorService.continueWithTypeUserCreation(userDTO);
                break;

            case "USER_CREATION_FAILED":
                orchestratorService.handleError(userDTO);
                break;

            case "TYPEUSER_CREATED":
                System.out.println("Se completó la creación del TypeUser. SAGA finalizada para usuario: " + userDTO.getId());
                break;

            case "TYPEUSER_CREATION_FAILED":
                orchestratorService.handleError(userDTO);
                break;

            default:
                System.out.println("Evento desconocido: " + status);
                break;
        }
    }
}