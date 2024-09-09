package com.example.playerapi.service;

import com.example.playerapi.exception.APIException;
import com.example.playerapi.model.Player;
import com.example.playerapi.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player findPlayerOrThrow(String name) {
        try {
            return playerRepository.findByName(name)
                    .orElseThrow(() -> new APIException(APIException.ErrorCode.PLAYER_NOT_FOUND,
                            "Player not found with name: " + name));
        } catch (Exception ex) {
            throw new APIException(APIException.ErrorCode.INTERNAL_ERROR,
                    "An unexpected error occurred while finding player: " + ex.getMessage());
        }
    }

    public Player registerPlayer(Player player) {
        try {
            if (playerRepository.findByName(player.getName()).isPresent()) {
                throw new APIException(APIException.ErrorCode.PLAYER_ALREADY_EXISTS,
                        "Player already exists with name: " + player.getName());
            }
            return playerRepository.save(player);
        } catch (Exception ex) {
            throw new APIException(APIException.ErrorCode.INTERNAL_ERROR,
                    "An unexpected error occurred while registering player: " + ex.getMessage());
        }
    }

    public Player getOrCreatePlayer(String name) {
        try {
            return playerRepository.findByName(name)
                    .orElseGet(() -> playerRepository.save(new Player(name)));
        } catch (Exception ex) {
            throw new APIException(APIException.ErrorCode.INTERNAL_ERROR,
                    "An unexpected error occurred while getting or creating player: " + ex.getMessage());
        }
    }
}