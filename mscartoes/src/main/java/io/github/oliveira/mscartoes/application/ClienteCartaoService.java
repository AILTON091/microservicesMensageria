package io.github.oliveira.mscartoes.application;

import io.github.oliveira.mscartoes.domain.ClienteCartao;
import io.github.oliveira.mscartoes.infra.repositor.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository clienteCartaoRepository;

    public List<ClienteCartao> lsitaCartaoByCpf(String cpf){
        return clienteCartaoRepository.findByCpf(cpf);
    }

}
