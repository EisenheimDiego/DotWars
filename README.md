# DotWars

<p align="center"> <img src="./src/main/java/ac/ucr/b66958/proyecto/images/logo.png" width="300"> </p>

### Dot Wars is a game that can be played by two players. 
### In the game, both players will fight to destroy every opponent's dot that can destroy their dots.
### This will be accomplished by moving your dots strategically, in order for them to not be damaged, but for you to attack.

## Inside the game

### Once the app is running:

- Both players must type their names.
- A board size must be selected from the default options, or typed in the personalized option.
- Once all this data has been provided, you can go ahead and click the **Begin new game** button.
- Example of data:
<p align="center"> <img src="./src/main/java/ac/ucr/b66958/proyecto/images/data.png" width="300"> </p>

### Once the game has begun:

- The game will indicate which player's turn is up. ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/turn.png "ShowTurn")
- It will also indicate the amount of dots that have been killed. ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/dotslost.png "dotsLost")
- As well as its dots information. 
- <p align="center"> <img src="./src/main/java/ac/ucr/b66958/proyecto/images/dots.png"> </p>
- The player whose turn is to play will have various options during it's turn, such like:
    - Quit the game ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/quit.png "Quit")
    - >The game will finish and the player who chose to quit the game, will lose.
    - Save the current state of the game ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/save.png "Save")
    - >You must select the folder where the game will be saved and provide a name for it.
    - Load an existing state of a game ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/load.png "Load")
    - >If you load a game, your current game will be lost if it was not saved before.
      > You will explore your files and select the game you want to resume.
    - Restart the current game ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/restart.png "Restart")
    - >Restarting a game will take the game to its initial state where there were no moves or attacks done

# How To Play?

- When your turn to play arrives, you will have two actions to perform and two options for each action:
1. Play: you get to move a dot and attack an enemy dot.
   - ### Move
      - Click on the button:  ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/move.png "Move")
      - Choose a dot to be moved:
     - <p align="center"> <img src="./src/main/java/ac/ucr/b66958/proyecto/images/chosen.png"> </p>
      - Using the arrow keys on your keyboard, move your dot to the desired position.
   - ### Attack
      - Click on the button: ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/attack.png "Attack")
      - Choose a dot, the same way you chose when moving.
      - And then choose the enemy dot you want to attack:
     - <p align="center"> <img src="./src/main/java/ac/ucr/b66958/proyecto/images/attacked.png"> </p>
      - If your enemy is within a ratio of your hit distance, you will attack it, otherwise the attack will be wasted.
2. Pass: you pass your turn to the opposite player. ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/pass.png "Pass")

# Mana Dots
- Distributed throughout the map, are mana dots that look like this:
 > <p align="center"> <img src="./src/main/java/ac/ucr/b66958/proyecto/images/manaSquare.png" width="100"> </p>
- If at the end of your turn, one or more dot ends positioned over one mana dot...
- ... In your next turn, those dots will gain 1 point of mana.

## Mana points

- When accumulating mana points, you will get to use them in 4 different ways.
  - Life: ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/life.png "Life")
    > In order for increase your life in one point, you will require 1 mana point.
  - Strength: ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/strength.png "Strength")
    > In case you want to increase your strength in one point you will require 2 mana points.
  - Hit Distance: ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/hitdistance.png "Hit Distance")
    > To increase your hit distance, or range, you will require 3 mana points.
  - Step Distance: ![alt text](./src/main/java/ac/ucr/b66958/proyecto/images/stepdistance.png "Step Distance")
    > And finally, to increase in one point your step distance, or moving steps, you require 1 mana point.

### Distributing mana points in your action

- If you want to distribute some mana points, hit the specific skill you want to improve.
- Then choose the dot that will be benefited.
  > If your dot has the minimum amount of mana points to increase your selected skill, it will proceed to do so.

  > Otherwise, the game will indicate you that the distribution of points is unavailable.

  > WARNING! The strength, the life and step distance can't be increased over 10 points.
  > So if the skill is at its maximum level, the distribution will remain unavailable in this case, too.
