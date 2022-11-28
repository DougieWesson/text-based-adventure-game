# Text Adventure Game

In this project you will be making a retro text adventure game.

Text adventure games, also known as interactive fiction games, allow a player to interact with the game world with text commands that are interpreted by the game.

Game actions typically involve moving from location to location (for simplicity, most locations offer possibilities to move north, east, south or west, but not all directions are always available depending on the location), talking to or interacting with game characters, picking up and using items and solving puzzles.

The aim of the game could be to navigate to a specific location or to satisfy some other requirement.

The content (story, characters, items, places, hints, etc) are for you to invent.

I have written the following user stories to allow you to keep to a set structure and to constrain the scope.

## User Stories

### 5-1 Moving between Rooms
As a player...<br>
...I want to move from room to room and eventually reach an end room.<br>
...So that I can get to a destination room

Acceptance Criteria:
 - Player starts in a room.
 - Each room has possibilities to move north, east, south or west.
 - Different rooms may restrict the available directions (i.e. 1 or more directions might be blocked)
 - Player types commands to move north, east, south or west to other rooms. Feedback is given to the player if the move is successful or not.
 - Moving to a new room gives a brief description of the new room the player is in.
 - When the player reaches the final room, the game ends.

### 5-2 Show a Map
As a player...<br>
...I want to see a map<br>
...So that I know where I have been and plan where to go next

Acceptance criteria:
 - A map is shown when a game starts in a new window.
 - The map can be hidden and shown with typed commands.
 - The map updates when the player moves between rooms.
 - The map shows distinguishable room types, including the current player location and the starting room.

### 5-3 Interaction
As a player...<br>
...I want to interact with items and characters in a room.<br>
...So that rooms are more interesting.

Acceptance Criteria:
 - Player can use a command to list the available items and characters in a room
 - Player can interact with items and characters with commands in the form 
 ```
 [verb] [item/character]
 ```
 - Each item available in a room can be inspected for more information.
 - Interacting with items and/or characters in the correct way may change the game state (affect other items/characters in the same or other rooms)
 - Interactions may also result in new directions becoming available in a room.

### 5-4 Inventory System
As a player...<br>
...I want to pick up items and add them to my inventory<br>
...so that I can use the items to solve puzzles

Acceptance Criteria
 - Player can use a "pickup [item]" command to add the item to their inventory.
 - The item persists in the inventory until it is no longer needed for the game.
 - The item can be used with other items and/or characters, this works in the same way as an interaction.
 - Player can list the items that currently exist in their inventory.

### 5-5 Levels
As a player...<br>
...I want to play in 3 levels/maps
...so that I feel a sense of progression.

Acceptance criteria
 - Player starts on level 1.
 - Levels should be named appropriately for the narrative context (i.e. other than "level 1, level 2, level 3.").
 - Each level is a new map of rooms.
 - When the end of a level is reached it is not possible to go back (try to avoid dead states)
 - The game is over at the end of level 3.

### 5-6 Room images
As a player...<br>
...I want some rooms to show an image to reepresent it
...so that I have some visual feedback to enhance the game play experience.

Acceptance criteria
 - If the new room has an image, the image is shown in a new window when the room is first entered.

### 5-7 Cheats
As a CHEATER...<br>
...I want to use secret commands to CHEAT<br>
...because I am a filthy CHEATER!

Acceptance criteria
 - Player can enter cheat commands to move to a specific room and level.
 - Moving to a room and level with a cheat should not cause a dead state i.e...
   - Player should be given inventory they are expected to have at that point
   - Other game states should change appropriately to avoid a dead state.