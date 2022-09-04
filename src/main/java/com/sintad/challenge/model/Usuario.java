package com.sintad.challenge.model;

import lombok.Data;

@Data
public class Usuario {
    private int id;
    private String user;
    private String pass;
    private String token;
}
