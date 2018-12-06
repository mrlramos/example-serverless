package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;


class DAO {

    public Cidade create(Cidade cid) {

        Cidade cidade = new Cidade();
        cidade.setNome(cid.getNome());

        return cid;
    }

    public List<Cidade> read() {
        
       return Stream.of( 
        new Cidade(1L, "cornelio", new Estado(1L, "PR")),
        new Cidade(1L, "cornelio", new Estado(1L, "PR")),
        new Cidade(1L, "cornelio", new Estado(1L, "PR")),
        new Cidade(1L, "cornelio", new Estado(1L, "PR"))    

       ).collect(Collectors.toList());
        
    }

    public Cidade update(Cidade cid) {
        cid.setId(cid.getId() + 1L);
        cid.setNome(cid.getNome() + "mudou");
        return cid;
    }

    public int delete(Long id) {
        return 200;
    }

}

public class Function {
    

    DAO dao = new DAO();

    @FunctionName("cria-cidade")
    public Cidade create(
            @HttpTrigger(name = "criarCi", 
            methods = { HttpMethod.POST }, route = "city") Cidade cidade) {
        return dao.create(cidade);
    }

    @FunctionName("ler-Cidade")
    public List<Cidade> read(
            @HttpTrigger(name = "lerCi",
             methods = { HttpMethod.GET }, route = "city") String cidade) {
        return dao.read();
    }

    @FunctionName("alt-Cidade")
    public Cidade update(
            @HttpTrigger(name = "alterarCi", 
            methods = { HttpMethod.PUT }, route = "city") Cidade cidade) {
        return dao.update(cidade);
    }

    @FunctionName("del-Cidade")
    public int delete(
        @HttpTrigger(name = "deletarCi", 
        methods = {HttpMethod.DELETE }, route = "city/{id}")
         @BindingName("id") Long id) {
        return dao.delete(id);
    }

}


class Cidade {
    private Long id;
    private String nome;
    private Estado estado;

    public Cidade(){

    }
    public Cidade(Long id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}

class Estado {
    private Long id;
    private String nome;

    public Estado(){

    }

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
