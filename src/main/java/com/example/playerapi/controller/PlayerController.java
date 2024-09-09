package com.example.playerapi.controller;

import com.example.playerapi.model.Player;
import com.example.playerapi.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Player> getPlayer(@PathVariable String name) {
        Player player = playerService.findPlayerOrThrow(name);
        return ResponseEntity.ok(player);
    }

    @PostMapping
    public ResponseEntity<Player> registerPlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.registerPlayer(player);
        return ResponseEntity.status(201).body(savedPlayer);
    }

    @GetMapping("/getOrCreate/{name}")
    public ResponseEntity<Player> getOrCreatePlayer(@PathVariable String name) {
        Player player = playerService.getOrCreatePlayer(name);
        return ResponseEntity.ok(player);
    }
}