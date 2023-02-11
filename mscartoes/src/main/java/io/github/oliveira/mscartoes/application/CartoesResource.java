package io.github.oliveira.mscartoes.application;

import io.github.oliveira.mscartoes.domain.Cartao;
import io.github.oliveira.mscartoes.domain.ClienteCartao;
import io.github.oliveira.mscartoes.representation.CartaoSaveRequest;
import io.github.oliveira.mscartoes.representation.CartoesPorClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService service;
    private final ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request){
        Cartao cartao = request.toModel();
        service.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda){
        List<Cartao> list = service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoes(String cpf){
        List<ClienteCartao>  lista = clienteCartaoService.lsitaCartaoByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = lista.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }
}
