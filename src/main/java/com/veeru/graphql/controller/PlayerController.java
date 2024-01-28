package com.veeru.graphql.controller;

import com.veeru.graphql.model.Player;
import com.veeru.graphql.model.Team;
import com.veeru.graphql.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service){
        this.service = service;
    }
    @QueryMapping
    public List<Player> findAll(){
        return service.findAll();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id){

        return service.findOne(id);
    }
    @MutationMapping
    public Player create(@Argument String name, @Argument Team team) {
        return service.create(name, team);
    }

    @MutationMapping
    public Player delete(@Argument Integer id){
        return service.delete(id);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team){
        return service.update(id, name, team);
    }


}
