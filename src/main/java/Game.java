public class Game {
  private final Player player;

  private final Level levelOne;
  private final Level levelTwo;
  private final Level levelThree;
  private Level currentLevel;


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
      case "look":
        feedback = inspectCommand(command);
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
      case "cheat":
        feedback = cheatCommand(command);
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
      player.getInventory().get(firstInput).getName();
    } catch (Exception e) {
      return "That's not a thing in your inventory.";
    }
    try {
      secondInput = command.split(" ")[2];
    } catch (Exception e) {
      return "What do you want to use that on?";
    }
    try {
      player.getCurrentRoom().getContents().get(secondInput).getName();
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

  public String validateTalkCommand(String command) {
    String input;
    if (player.getCurrentRoom().getContents().isEmpty()) {
      return "That's not a thing in this room that can talk to.";
    }
    try {
      input = command.split(" ")[1];
    } catch (Exception e) {
      return "Who did you want to talk to?";
    }
    input = command.split(" ")[1].toLowerCase();
    try {
      String thing = player.getCurrentRoom().getContents().get(input).getName();
    } catch (Exception e) {
      return "That's not something in this room";
    }
    return "No errors";
  }

  public String talkToCommand(String command) {
    if (!validateTalkCommand(command).equals("No errors")) {
      return validateTalkCommand(command);
    }
    String feedback;
    String input = command.split(" ")[1].toLowerCase();
    RoomThing thing = player.getCurrentRoom().getContents().get(input);
    feedback = thing.talkTo();
    if (thing.getName().equalsIgnoreCase("jimbo")) {
      try {
        levelOne
                .getLevelRooms()
                .get("workshop")
                .putContents(new RoomThingTool("keycard", "A brilliant yellow flake of plastic, looks important"));
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
    return feedback;
  }

  public String validateTakeCommand(String command) {
    String input;
    if (player.getCurrentRoom().getContents().isEmpty()) {
      return "This room is empty.";
    }
    try {
      input = command.split(" ")[1];
    } catch (Exception e) {
      return "What do you want to take?";
    }
    input = command.split(" ")[1];
    try {
      player.getCurrentRoom().getContents().get(input).getName();
    } catch (Exception e) {
      return "Can't pick that up.";
    }
    return "No errors";
  }

  public String takeCommand(String command) {
    if (!validateTakeCommand(command).equals("No errors")) {
      return validateTakeCommand(command);
    } else {
      String input = command.split(" ")[1];
      RoomThing foundThing = player.getCurrentRoom().getContents().get(input);
      return foundThing.takeThing(player.getInventory(), player.getCurrentRoom().getContents());
    }
  }

  public String showMap(String command) {
    String input;
    try {
      input = command.split(" ")[1];
    } catch (Exception e) {
      return "Are you trying to show the map?";
    }
    input = command.split(" ")[1];
      if (input.equals("map")) {
        this.getCurrentLevel().getBoardWindow().showBoard();
        return "Map shown";
      } else {
        return "Pardon?";
      }
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
    if (player.getInventory().isEmpty()) {
      return "Nothing in your inventory";
    } else {
      String inventory = "You have these items in your inventory:";
      inventory += String.join(", ", player.getInventory().keySet());
      return inventory;
    }
  }

  public String validateCheatCommand(String command) {
    try {
      String input = command.split(" ")[1];
    }catch (Exception e) {
      return "Are you trying to cheat?";
    }
    return "No errors";
  }

  public String cheatCommand(String command) {
    if(!validateCheatCommand(command).equals("No errors")){
      return validateCheatCommand(command);
    } else {
      switch (command.split(" ")[1]){
        case "primus":
          return skipLevelOne();
        default:
          return "Cheat failed, ya cheater.";
      }
    }
  }

  public String skipLevelOne() {
    player.setCurrentRoom(levelOne.getLevelRooms().get("office"));
    player.getInventory().put("keycard", new RoomThingTool("keycard", "A brilliant yellow flake of plastic, looks important"));
    return "Level One Skipped, use keycard on grate to escape.";
  }

}
