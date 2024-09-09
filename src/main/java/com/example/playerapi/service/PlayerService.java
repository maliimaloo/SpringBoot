package com.example.playerapi.service;

import com.example.playerapi.model.Player;
import com.example.playerapi.repository.PlayerRepository;
import com.example.playerapi.exception.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayerByName(String name) {
        return playerRepository.findByName(name);
    }

    public Player registerPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player findPlayerOrThrow(String name) {
        Player player = getPlayerByName(name);
        if (player == null) {
            throw new PlayerNotFoundException(name);
        }
        return player;
    }

    public Player getOrCreatePlayer(String name) {
        Player player = playerRepository.findByName(name);
        if (player == null) {
            player = new Player(name);
            player = playerRepository.save(player);
        }
        return player;
    }
}
