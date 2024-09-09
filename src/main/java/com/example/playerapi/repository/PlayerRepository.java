package com.example.playerapi.repository;

import com.example.playerapi.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface de repository pour la gestion des entités Player.
 * Étend JpaRepository pour fournir des opérations CRUD et des méthodes de requêtes personnalisées.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);
}
