# Mouse Controls:
Left click any “dead” cell to bring it back to life. (Gray -> Yellow)
Left click any “live” cell to turn it into a dead one. (Yellow -> Gray)

# Keyboard Controls:
Period
Will progress the game by one generation.
Space Bar 
Will start / stop the automatic progression of the game.
Left Arrow 
Will slow the automatic progression of the game, up to 5000 ticks (5 seconds / tick).
Right Arrow
Will accelerate the automatic progression of the game, up to 50 ticks (0.05 seconds / tick).
Down Arrow 
Will reset the game and stop the automatic progression.

# Game Notes:
Default progression is set to false. To turn on automatic progression, press the space bar.
Default automatic tick is 500, or 0.5 seconds per tick.
The default unit size is 25 as it makes it very easy to click on individual squares. To see more complex patterns, such as the one in the demonstration video, it is recommended to change the “unitSize” variable at the top of “Panel.java” to 10 or 5. (This will increase the number of cells available)

# Playing the Game:
The simplest “fun” shape that I have found from research is the “R-pentomino,” which is attached as an image in this folder. This simple shape made from only 5 squares was found by Conway himself, and stabilizes in a whopping 1103 generations. Try this one out before anything else.
Another interesting shape is the “Glider,” which glides across the screen diagonally. (Attached as an image) The “Glider Constructor,” also attached as an image, is a complex shape that constructs Gliders. When trying out complex shapes, count every square as a single misplaced square will result in something different than what was intended. (I have tried out the shapes and they all work)
The current tick speed can be checked by looking at the console, where it will be logged.
Example status message:
Current Tick Speed: 500
Game Running Status: false
It is recommended that you change the tick speed while the game is “paused.” (Running status == false)

# Game Mechanics:
This is a zero-player game with 4 distinct rules that govern it: (Rules sourced from Wikipedia)
1.	Any live cell with fewer than two live neighbours die, as if by underpopulation.
2.	Any live cell with two or three live neighbours lives on to the next generation.
3.	Any live cell with more than three live neighbours dies, as if by overpopulation.
4.	Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
The game is Turing Complete, which means that it can simulate any Turing machine, including itself. Please watch this short video showing The Game of Life simulated in The Game of Life if interested:
https://www.youtube.com/watch?v=xP5-iIeKXE8

# Additional Resources:
Watch the R-Pentomino: https://www.youtube.com/watch?v=bTPN3spiq1I
Watch the Glider Constructor: https://www.youtube.com/watch?v=fyrtJn5eK5U 
Watch a Glider Constructor-Constructor: https://www.youtube.com/watch?v=7TgXE72uRyM
