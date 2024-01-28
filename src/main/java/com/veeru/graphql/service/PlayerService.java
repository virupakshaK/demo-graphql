package com.veeru.graphql.service;

import com.veeru.graphql.model.Player;
import com.veeru.graphql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);
    public List<Player> findAll(){
        return players;
    }

    public Optional<Player> findOne(Integer id){
        return players.stream().filter(player -> player.id() == id).findFirst();
    }

    public Player create(String name, Team team){
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id){
        Player player = players.stream().filter(p -> p.id() == id).findFirst().orElseThrow(IllegalArgumentException::new
        );
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team){
         Player updatePlayer = new Player(id, name, team);

        Optional<Player> optional= players.stream().filter(p -> p.id() == id).findFirst();
        if(optional.isPresent()){
            Player player= optional.get();
            int index = players.indexOf(player);
            players.set(index, updatePlayer);
        }else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatePlayer;
    }

    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Ashwini", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "KL Rahul", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "Shewag", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.GT));
    }

}
