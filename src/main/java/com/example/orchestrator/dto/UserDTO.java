package com.example.orchestrator.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String typeUser;
    private String status; // para indicar en qué estado de la saga estamos

}
