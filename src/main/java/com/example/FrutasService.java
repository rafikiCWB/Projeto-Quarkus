package com.example;

import io.quarkus.arc.Lock;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Lock
@ApplicationScoped
public final class FrutasService {

    @Inject
    IdentificadorTransacao identificadorTransacao;

    @Lock(value = Lock.Type.READ, time = 3, unit = TimeUnit.SECONDS)
    public List<Fruta> list() {
        System.out.println(identificadorTransacao.getIdentificacaoTransacao());
        return Fruta.listAll();
    }

    @Transactional // Transactional is required for PanacheEntity.persist()
    public void notaFruta(InserirFrutaDTO inserirFrutaDTO) {
        System.out.println(identificadorTransacao.getIdentificacaoTransacao());
        Fruta fruta = new Fruta();
        fruta.nome = inserirFrutaDTO.getNome();
        fruta.qtd = inserirFrutaDTO.getQtd();
        fruta.persist();
    }
}