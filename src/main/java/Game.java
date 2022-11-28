public class Game {
  private Player player;

  private Level levelOne;
  private Level levelTwo;
  private Level levelThree;
  private Level currentLevel;

  private boolean quitting = false;

  public Game(Player player, Level levelOne, Level levelTwo, Level levelThree) {
    this.levelOne = levelOne;
    this.levelTwo = levelTwo;
    this.levelThree = levelThree;
    this.currentLevel = levelOne;
    this.player = player;
  }

  public Level getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(Level currentLevel) {
    this.currentLevel = currentLevel;
  }

  public boolean hasFinishedLevelOne() {
    return this.player.getCurrentRoom().equals(levelOne.getEndingRoom());
  }

  public boolean hasFinishedLevelTwo() {
    return this.player.getCurrentRoom().equals(levelTwo.getEndingRoom());
  }

  public boolean hasFinishedGame() {
    return this.player.getCurrentRoom().equals(levelThree.getEndingRoom());
  }


  public String runCommand(String command) {
    String feedback;
    switch (command.split(" ")[0]) {
      case "move":
      case "go":
      case "run":
      case "crawl":
        feedback = moveCommand(command);
        break;
      case "inspect":
        feedback = inspectCommand(command);
        break;
      case "break":
      case "destroy":
        feedback = breakCommand(command);
        break;
      case "address":
      case "speak":
      case "talk":
        feedback = talkToCommand(command);
        break;
      case "show":
        feedback = showMap(command);
        break;
      case "hide":
        feedback = hideMap(command);
        break;
      case "take":
      case "grab":
      case "get":
      case "collect":
      case "pickup":
        feedback = takeCommand(command);
        break;
      case "check":
        if (command.split(" ").length > 1 && command.split(" ")[1].equals("inventory")) {
          feedback = checkInventory();
        } else feedback = "Check what?";
        break;
      case "use":
        feedback = useCommand(command);
        break;
      case "quit":
        feedback = "Goodbye";
        break;
      default:
        feedback = "I don't understand";
    }
    if (hasFinishedLevelOne()) {
      this.levelOne.getBoardWindow().hideBoard();
      this.levelTwo.getBoardWindow().showBoard();
      feedback = levelOne.getSuccessMessage();
      this.getPlayer().setCurrentRoom(levelTwo.getStartingRoom());
      this.setCurrentLevel(levelTwo);
      this.getPlayer().setLevel(levelTwo);
      this.getCurrentLevel().getBoardWindow().getBoard().setCell(this.player.getCurrentRoom().getRow(), this.player.getCurrentRoom().getColumn(), CellType.CURRENT_ROOM);
      this.levelTwo.getBoardWindow().repaint();
    }
    if (hasFinishedLevelTwo()) {
      this.levelTwo.getBoardWindow().hideBoard();
      this.levelThree.getBoardWindow().showBoard();
      feedback = levelTwo.getSuccessMessage();
      this.getPlayer().setCurrentRoom(levelThree.getStartingRoom());
      this.setCurrentLevel(levelThree);
      this.getPlayer().setLevel(levelThree);
      this.getCurrentLevel().getBoardWindow().getBoard().setCell(this.player.getCurrentRoom().getRow(), this.player.getCurrentRoom().getColumn(), CellType.CURRENT_ROOM);
      this.levelThree.getBoardWindow().repaint();
    }
    if (hasFinishedGame()) {
      feedback = levelThree.getSuccessMessage();
    }
    return feedback;
  }

  public Player getPlayer() {
    return player;
  }

  public String moveCommand(String command) {
    String feedback;
    try {
      String direction = command.split(" ")[1];
      switch (direction) {
        case "north":
          feedback = player.move(Direction.NORTH);
          break;
        case "east":
          feedback = player.move(Direction.EAST);
          break;
        case "south":
          feedback = player.move(Direction.SOUTH);
          break;
        case "west":
          feedback = player.move(Direction.WEST);
          break;
        default:
          feedback = "You cannot move there!";
      }
    } catch (Exception e) {
      feedback = "Where are you gonna go?";
    }
    return feedback;
  }

  public String inspectCommand(String command) {
    String feedback = "What did you want to inspect?";
    String nameOfThing;
    try {
      nameOfThing = command.split(" ")[1];
    } catch (Exception e) {
      return feedback;
    }
    if (nameOfThing.equals("room")) {
      return player.getCurrentRoom().listContents();
    }
    try {
      RoomThing foundThing = player.getCurrentRoom().getContents().get(nameOfThing);
      return foundThing.inspect();
    } catch (Exception e) {
      return feedback;
    }
  }

  public String validateUseCommand(String command) {
    String firstInput;
    String secondInput;
    if (player.getInventory().isEmpty()) {
      return "You don't have anything to use.";
    }
    try {
    firstInput = command.split(" ")[1];
    } catch (Exception e) {
      return "What do you want to use?";
    }
    try {
      String firstThing = player.getInventory().get(firstInput).name;
    } catch (Exception e) {
      return "That's not a thing in your inventory.";
    }
    try {
      secondInput = command.split(" ")[2];
    } catch (Exception e) {
      return "What do you want to use that on?";
    }
    try {
      String secondThing = player.getCurrentRoom().getContents().get(secondInput).name;
    } catch (Exception e) {
      return "That's not a thing in this room.";
    }
    return "No errors";
  }

  public String useCommand(String command) {
    if (!validateUseCommand(command).equalsIgnoreCase("No errors")) {
      return validateUseCommand(command);
    }
    String firstInput = command.split(" ")[1];
    String firstThing = player.getInventory().get(firstInput).getName().toLowerCase();
    String secondInput = command.split(" ")[2];
    String secondThing = player.getCurrentRoom().getContents().get(secondInput).getName().toLowerCase();
    String roomName = player.getCurrentRoom().getName().toLowerCase();

    if (firstThing.equals("keycard") && secondThing.equals("grate") && roomName.equals("office")) {
      levelOne
              .getLevelRooms()
              .get("office")
              .putDirection(Direction.NORTH, levelOne.getLevelRooms().get("vent"));
      return "You open the grate, somehow...";
    }

    return "That doesn't work here.";
  }

  public String breakCommand(String command) {
    String feedback;
    try {
      String input = command.split(" ")[1];
      try {
        RoomThing foundThing = player.getCurrentRoom().getContents().get(input);
        if (foundThing.getClass().equals(RoomThingTool.class)) {
          ((RoomThingTool) foundThing).setBroken(true);
          feedback = "You broke it!";
        } else {
          feedback = "Can't break that.";
        }
      } catch (Exception e) {
        feedback = "Can't break that.";
      }
    } catch (Exception e) {
      feedback = "What did you want to break?";
    }
    return feedback;
  }

  public String talkToCommand(String command) {
    String feedback;
    String input;
    if (player.getCurrentRoom().getContents().isEmpty()) {
      return "That's not a thing in this room that can talk to.";
    }
    try {
      input = command.split(" ")[1].toLowerCase();
      RoomThing thing = player.getCurrentRoom().getContents().get(input);
      feedback = thing.talkTo();
      if (thing.getName().equalsIgnoreCase("jimbo")) {
        try {
          levelOne
                  .getLevelRooms()
                  .get("workshop")
                  .putContents(new RoomThingTool("keycard", "A brilliant yellow flake of plastic, looks important", false, false, false));
          levelOne
                  .getLevelRooms()
                  .get("hallway")
                  .putDirection(Direction.EAST, levelOne.getLevelRooms().get("lockerRoom"));
          levelOne
                  .getLevelRooms()
                  .get("lockerRoom")
                  .putDirection(Direction.WEST, levelOne.getLevelRooms().get("hallway"));
        } catch (Exception e) {
          feedback = "Jimbo couldn't do something...";
        }
      }
    } catch (Exception e) {
      return "Who did you want to talk to?";
    }
    return feedback;
  }

  public String takeCommand(String command) {
    String feedback;
    if (player.getCurrentRoom().getContents().isEmpty()) {
      return "This room is empty.";
    }
    try {
      String input = command.split(" ")[1];
      RoomThing foundThing = player.getCurrentRoom().getContents().get(input);
      feedback = foundThing.takeThing(player.getInventory(), player.getCurrentRoom().getContents());
    } catch (Exception e) {
      feedback = "Can't pick that up.";
    }
    return feedback;
  }

  public String showMap(String command) {
    String feedback = "What do you want to do?";
    try {
      String input = command.split(" ")[1];
      if ("map".equals(input)) {
        this.getCurrentLevel().getBoardWindow().showBoard();
        feedback = "Map shown";
      }
    } catch (Exception e) {
      feedback = "What are you showing?";
    }
    return feedback;
  }

  public String hideMap(String command) {
    String feedback = "What do you want to do?";
    try {
      String input = command.split(" ")[1];
      if ("map".equals(input)) {
        this.getCurrentLevel().getBoardWindow().hideBoard();
        feedback = "Map hidden";
      }
    } catch (Exception e) {
      feedback = "What are you hiding?";
    }
    return feedback;
  }

  public String checkInventory() {
    String inventory = "";
    if (player.getInventory().isEmpty()) {
      return "Nothing in your inventory";
    }
    for (String thing : player.getInventory().keySet()) {
      inventory = inventory + thing + ", ";
    }
    return inventory;
  }
}
