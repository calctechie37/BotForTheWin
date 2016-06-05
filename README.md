# Term2FinalProject

Current Goal
<br>
We plan to optimize the Connect Four Bot some more.  After all, Charles is planning to work on SuperTicTacToe and Patrick is considering doing an Othello bot.

##<b>To Play Connect Four</b>

      $ git clone https://github.com/calctechie37/BotForTheWin.git
      $ cd BotForTheWin/DemoBuild/
      $ javac Game.java
      $ java Game

##<b>Things to Patch Up...</b>
<b>Connect Four</b>
* Modify the instruction to tell the user how to quit ("q")
<b>Othello</b>
* Code needed to tell who's turn it is and which color the player is playing as...
* Bot has not been written
<b>Super TicTacToe</b>

##<b>Development Log (Othello)</b>
<b>6/5/16</b>
<br>
Development is progressing again after a week (or so) of inactivity...
<br>
Patrick: Added functions in the board class to add and remove pieces.  Also wrote a testing class to make sure the new functions work as desired.
<br>

<b>5/31/16</b>
<br>
Patrick: Modify the coding style for the board and main class.  Wrote the foundation for the othello bot.
<br>

<b>5/27/16 - 5/28/16</b>
<br>
Patrick: Wrote the board class and a function to test the board
<br>

##<b>Development Log (Super TicTacToe)</b>

<b>5/26/16 - 5/27/16</b>
<br>
Charles: Fixed bugs in adding to board. Added checks for winning an inner board or the whole board.
<br>

<b>5/25/16: Version 0.2</b>
<br>
Charles: Got the adding to board to work and user input.
<br>

<b>5/24/16: Version 0.1</b>
<br>
Charles: Started working on the Super TicTacToe game. Created the board in a text file and the game.
<br>

##<b>Development Log (Connect Four)</b>

<b>5/27/16: Version 0.61</b>
<br>
Patrick: Tweaked the speed of the bot some more base on players' inputs.  Also, modified the user interface to let the player know that the bot is thinking on its turn, which also makes it easier to see when the turn has switched.  Modified the board class to make it even more flexible for future board size changes.
<br>

<b>5/26/16: Version 0.6</b>
<br>
Patrick: Modified the Connect Four project to use a rectangular board of 6x7, future modification of board sizes will be a lot easier.  Also, tweaked the Connect Four bot to balance the speed a bit due to larger search depth.
<br>

<b>5/24/16: Version 0.55</b>
<br>
Patrick: Made a small optimization that speeded up the bot by another 35 seconds with a slight slowdown in generating the first move.
<br>

<b>5/23/16: Version 0.51</b>
<br>
Patrick: Made a couple of optimization to speed the bot up further.  Also improved the user interface: 'X' are colored red, 'O' are colored blue, the most recent move is colored green, the terminal is cleared each time the board is updated, markings are displayed on both the top of the board and bottom of the board to help distinguish the columns, and a brief introduction is added.
<br>

<b>5/21/16 - 5/22/16: Version 0.5</b>
<br>
Patrick: Finished incorporating the evaluation function into the minimax algorithm, reorganized code into separate objects, and incorporated the user input into the reorganized code, and optimized the Bot.
<br>
Optimization made to the Bot:<br>
- Implemented alpha-beta pruning with ordering of possible moves (Shaved 24 minutes)<br>
- Cut down on expensive computations (Shaved 2 minutes)<br>
- Small optimization in loops (Shaved ~50 seconds)<br>
<br>

<b>5/20/16: Version 0.4</b>
<br>
Patrick: Fixed IndexOutOfBoundException Error and logic error in the Bot's decision tree algorithm
<br>

<b>5/18/16: Version 0.3</b>
<br>
Charles: Improved on the user input functionality
<br>
Patrick: Performed more research on heuristic evaluation functions
<br>

<b>5/17/16: Version 0.2</b>
<br>
Patrick: Fixed bugs in the checkBoard function and added diagonal checks to the checkBoard function.  Also, updated Main.java with new tests.
<br>
Charles: Fixing bugs in the userInput function
<br>

<b>5/13/16 - 5/16/16: Version 0.1</b>
<br>
Patrick: Merged the Board and ConnectFour classes. Made the functions for the Board. 
<br>
Charles: Started writing the game engine and user input functionality.

