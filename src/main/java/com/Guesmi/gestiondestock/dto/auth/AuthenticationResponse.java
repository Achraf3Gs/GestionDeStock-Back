package com.Guesmi.gestiondestock.dto.auth;


import com.Guesmi.gestiondestock.model.Roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String message;

    private String token;

    private String name;

    private String address;

    private String idEntreprise;

    private Integer id;






}
