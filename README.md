# Football Team
A Java project demonstrating object-oriented programming concepts with a focus on football team operations, collections, and generics.

## Project Structure
```
src/main/java/com/solvd/
├── collections/        # Custom collection implementations
├── exceptions/         # Custom exceptions
├── interfaces/         # Interface definitions
├── models/             # Domain models
│   └── game/           # game-related models
│   └── market/         # market-related models
│   └── referees/       # referee-related models
│   └── team/           # referee-related models
│   └── utils/          # utility classes
└── App.java            # Application entry point
```
## Features
- Football game management
- Team composition and management
- Skills tracking
- Funds calculations
- Various football players and referee types support
- Comprehensive exception handling
- Generic classes for type safety
- Custom LinkedList implementation
- Multiple collection types (List, Set, Map, Queue, Tree)
- Proper encapsulation with getters and setters

## Collections and Generics
The project demonstrates the use of various collection types and generic classes:

- CustomLinkedList<T> - A custom implementation of a linked list with basic operations
Contains a private inner Node<T> class with encapsulated data
Provides methods like add, get, remove, size, and isEmpty
Full encapsulation with proper getters and setters

- Competititon<T extends Game> - Generic game management using collection Queue for scheduling games

- AbstractMarket<T> - Generic market maintainance using collection
HashSet for market (only unique players can appear in market)

- FootballTeam <T extends FootballPlayer> - Generic football team management using collection ArrayList<T> to store football team members

- RewardTracker - Class for tracking rewards using collection Map<FootballPlayer, List<String>> to store each football player's rewards

- SkillsRating - Class for ranging football teams using collection TreeMap<FootballTeam, Integer> to rate each football team based on its skill score

## Custom Exceptions

The project includes several custom exceptions:

- DuplicatePlayerRoleException - Thrown when the same football player with a different role is being added to a market
- IncompleteTeamException - Thrown when a team does not have all the necessary players
- InsufficientFundsException - Thrown when a manager doesn't have enough fund to buy a player
- InvalidGameException - Thrown when a game cannot be scheduled due to the lack of players in one of the teams
- SameReferees - Thrown when the same referee ia assigned to defferent roles

## Encapsulation
All classes follow proper encapsulation principles:

- Private fields with public getters and setters
- Inner classes (like Node in LinkedList) properly encapsulated
- Logger instances are private and static final

## Build and Run
```bash
# The project uses *Maven* for dependency management and building.

# Compile the project
mvn compile

# Run the application
mvn exec:java

# Package the application
mvn package
```


## Requirements
```
Java 21
Maven 3.8+
```