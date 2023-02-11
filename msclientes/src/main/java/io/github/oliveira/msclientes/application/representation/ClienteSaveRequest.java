package io.github.oliveira.msclientes.application.representation;

import io.github.oliveira.msclientes.domain.Cliente;
import lombok.Data;

@Data // cria get e sets automaticamente
public class ClienteSaveRequest {

    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
