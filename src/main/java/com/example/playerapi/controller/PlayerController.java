package com.example.playerapi.controller;

import com.example.playerapi.model.Player;
import com.example.playerapi.service.PlayerService;
import com.example.playerapi.exception.PlayerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{name}")
    public ResponseEntity<Player> getPlayer(@PathVariable String name) {
        Player player = playerService.findPlayerOrThrow(name);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> registerPlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.registerPlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/getOrCreate/{name}")
    public ResponseEntity<Player> getOrCreatePlayer(@PathVariable String name) {
        Player player = playerService.getOrCreatePlayer(name);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}