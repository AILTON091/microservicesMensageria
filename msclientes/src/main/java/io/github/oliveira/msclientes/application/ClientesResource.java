package io.github.oliveira.msclientes.application;

import io.github.oliveira.msclientes.application.representation.ClienteSaveRequest;
import io.github.oliveira.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor // Da biblioteca LOMBOK cria construtor automatica, não sendo mais necessario o auto @Autowired
public class ClientesResource {

    private final ClienteService service;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
         Cliente cliente = request.toModel();
         service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest() // url corrente - /clientes
                .query("cpf={cpf}") // parametro de busca
                .buildAndExpand(cliente.getCpf()) // builda o parametro {cpf}
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = service.getByCPF(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
