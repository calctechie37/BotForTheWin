# Term2FinalProject

Current Goal
<br>
We plan to start out with Connect Four. After we complete a basic version of Connect Four, we plan to reevaulate our position and either move on to Chess or expand on our version of Connect Four.  This will allow us to start with something that is pretty doable and then try something more complex knowing that we can always fall back on to our original project and expand it should things go downhil.

To Play Connect Four
- Clone the repo
- cd into DemoBuild/
- $ javac Game.java
- $ java Game
<br>

<b>Development Log</b>

5/21/16: Version 0.6
<br>
Patrick: Finished incorporating the evaluation function into the minimax algorithm, reorganized code into separate objects, and incorporated the user input into the reorganized code, and optimized the Bot.
<br>
Optimization made to the Bot:<br>
- Implemented alpha-beta pruning with ordering of possible moves (Shaved 24 minutes)<br>
- Cut down on expensive computations (Shaved 2 minutes)<br>
- Small optimization in loops (Shaved ~30 seconds)<br>
<br>
Note: The Bot currently implements a hackish optimization to meet user's demand for speed where for the first eight moves it looks only 11 moves ahead and afterward it looks 15 moves ahead.
<br>

5/20/16: Version 0.4
<br>
Patrick: Fixed IndexOutOfBoundException Error and logic error in bot
<br>

5/18/16: Version 0.3
<br>
Charles: Improved on the user input functionality
<br>
Patrick: Performed more research on heuristic evaluation functions
<br>

5/17/16: Version 0.2
<br>
Patrick: Fixed bugs in the checkBoard function and added diagonal checks to the checkBoard function.  Also, updated Main.java with new tests.
<br>
Charles: Fixing bugs in the userInput function
<br>

5/13/16 - 5/16/16: Version 0.1
<br>
Patrick: Merged the Board and ConnectFour classes. Made the functions for the Board. 
<br>
Charles: Started writing the game engine and user input functionality.
