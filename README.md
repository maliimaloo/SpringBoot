# Documentation des Classes

## `SecurityConfig`
- **Fonctionnalité :** Configuration de la sécurité de l'application avec Spring Security.
- **Description :**
    - `@Configuration` : Indique que cette classe contient des configurations pour le contexte Spring.
    - `@EnableWebSecurity` : Active la configuration de sécurité Web de Spring Security.
    - **Méthodes :**
        - `securityFilterChain(HttpSecurity http)`: Configure les règles de sécurité pour les requêtes HTTP. Ici, l'authentification est requise pour toutes les requêtes vers `/players/**`, avec une authentification de base HTTP et un formulaire de connexion activés. La protection CSRF est désactivée pour simplifier les tests.
        - `userDetailsService()`: Définit un utilisateur en mémoire avec un nom d'utilisateur et un mot de passe. Cet utilisateur est utilisé pour l'authentification dans l'application.

## `PlayerController`
- **Fonctionnalité :** Gère les requêtes HTTP pour les opérations sur les joueurs.
- **Description :**
    - `@RestController` : Indique que cette classe est un contrôleur Spring qui gère les requêtes HTTP et renvoie des réponses.
    - `@RequestMapping("/players")` : Définit le chemin de base pour les requêtes à ce contrôleur.
    - **Méthodes :**
        - `getPlayer(@PathVariable String name)`: Récupère un joueur par son nom. Renvoie une réponse avec le joueur trouvé ou une erreur 404 si le joueur n'existe pas.
        - `registerPlayer(@RequestBody Player player)`: Enregistre un nouveau joueur avec les informations fournies dans la requête. Renvoie le joueur créé avec une réponse 201 (créé).
        - `getOrCreatePlayer(@PathVariable String name)`: Récupère un joueur par son nom, ou le crée s'il n'existe pas encore. Renvoie le joueur avec une réponse 200 (OK).

## `ErrorResponse`
- **Fonctionnalité :** Structure de réponse pour les erreurs.
- **Description :**
    - **Propriétés :**
        - `message`: Contient le message d'erreur.
    - **Constructeurs et Méthodes :**
        - Constructeur : Initialise l'objet avec un message d'erreur.
        - `getMessage()`, `setMessage(String message)`: Accesseurs pour la propriété `message`.

## `GlobalExceptionHandler`
- **Fonctionnalité :** Gère les exceptions globales et personnalise les réponses d'erreur.
- **Description :**
    - `@ControllerAdvice` : Indique que cette classe fournit des conseils pour les contrôleurs Spring, comme la gestion des exceptions.
    - **Méthodes :**
        - `handlePlayerNotFound(PlayerNotFoundException ex)`: Gère les exceptions `PlayerNotFoundException` et renvoie une réponse d'erreur avec un statut 404 (Non trouvé).
        - `handleGenericException(Exception ex)`: Gère les exceptions générales et renvoie une réponse d'erreur avec un statut 500 (Erreur interne du serveur).

## `PlayerNotFoundException`
- **Fonctionnalité :** Exception personnalisée pour indiquer qu'un joueur n'a pas été trouvé.
- **Description :**
    - **Constructeur :** Initialise l'exception avec un message indiquant le nom du joueur non trouvé.

## `Player`
- **Fonctionnalité :** Représente une entité de joueur dans la base de données.
- **Description :**
    - `@Entity` : Indique que cette classe est une entité JPA.
    - **Propriétés :**
        - `id`: Identifiant unique du joueur, généré automatiquement.
        - `name`: Nom du joueur.
    - **Constructeurs et Méthodes :**
        - Constructeur par défaut et constructeur avec `name`.
        - `getId()`, `getName()`: Accesseurs pour les propriétés `id` et `name`.

## `PlayerRepository`
- **Fonctionnalité :** Interface de repository pour gérer les opérations CRUD sur les entités `Player`.
- **Description :**
    - **Méthodes :**
        - `findByName(String name)`: Trouve un joueur par son nom.

## `PlayerService`
- **Fonctionnalité :** Fournit des services pour gérer les joueurs.
- **Description :**
    - **Méthodes :**
        - `getPlayerByName(String name)`: Récupère un joueur par son nom.
        - `registerPlayer(Player player)`: Enregistre un nouveau joueur.
        - `findPlayerOrThrow(String name)`: Récupère un joueur ou lance une exception si le joueur n'existe pas.
        - `getOrCreatePlayer(String name)`: Récupère un joueur ou le crée s'il n'existe pas encore.