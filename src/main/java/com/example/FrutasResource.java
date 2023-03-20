package com.example;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/frutas")
public class FrutasResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> list() {
        return Fruta.listAll();
    }

    @POST
    @Transactional
    public void novaFruta() {
        Fruta fruta = new Fruta();
        fruta.nome = "Banana";
        fruta.qtd = 5;
        fruta.persist();
    }
}