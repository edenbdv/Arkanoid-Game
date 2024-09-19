# Arkanoid Game

This Arkanoid game was developed as part of an Object-Oriented Programming course. It features multiple levels that gradually increase in difficulty and is implemented on a user-friendly graphical interface.

## Game Levels

<img width="593" alt="‏‏לכידה0" src="https://github.com/user-attachments/assets/ced7d8f7-8cfe-46b7-943d-47f7a3e5e688">
<img width="600" alt="‏‏לכידה1" src="https://github.com/user-attachments/assets/99388e74-4303-4c1e-8b64-da9e3f425152">
<img width="600" alt="‏‏לכידה2" src="https://github.com/user-attachments/assets/b64be56c-8265-4f32-8c67-cd07218e40a2">


## Design Patterns

### Builder Design Pattern
The Builder design pattern is employed to construct various game components in a step-by-step process, providing flexibility and ease of use. Each builder class is responsible for creating specific game elements, such as balls, blocks, borders, and paddles. This approach ensures more readable and maintainable code and allows easy adjustments to the properties of game objects during their construction.

### Observer Design Pattern
The Observer design pattern was utilized to manage events between different game objects. Game objects act as Observables, while listener classes function as Observers, subscribing to game events such as block removal, ball updates, and score changes. This design pattern supports a modular code structure, allowing new event listeners to be added without altering the original game objects.

## Controls
- Use the left and right arrow keys to move the paddle.
- Press `P` to pause the game.
- Press the `Spacebar` to resume playing.
- Clear all the blocks to progress through the game, or try again if you lose.

## Dependencies
- Compatible with Windows, Linux, and macOS.
- Requires Git.
- Keyboard with functioning `Spacebar`, `P` key, and arrow keys.
- Apache Ant.

## Installation and Execution
To play the game, download the `biuoop-1.4.jar` file from the release section and follow the instructions to run it.
