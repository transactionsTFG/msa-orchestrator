package com.example.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orchestrator.dto.UserDTO;
import com.example.orchestrator.service.OrchestratorService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/orchestrator")
@RequiredArgsConstructor
public class OrchestratorController {

    private final OrchestratorService orchestratorService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        orchestratorService.startUserCreation(userDTO);
        return ResponseEntity.ok("Solicitud para crear usuario recibida. Revisa logs para ver la orquestación.");
        // try {
        // } catch (Exception e) {
        //     return ResponseEntity.internalServerError().body(e.getMessage());
        // }
    }

    @GetMapping("/getUser")
    public String getUser() {
        return "hola";
    }
    
}