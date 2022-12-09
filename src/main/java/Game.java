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
    return player.getCurrentRoom().equals(levelOne.getEndingRoom());
  }

  public boolean hasFinishedLevelTwo() {
    return player.getCurrentRoom().equals(levelTwo.getEndingRoom());
  }

  public boolean hasFinishedGame() {
    return player.getCurrentRoom().equals(levelThree.getEndingRoom());
  }

  public String runCommand(String command) {
    String feedback;
    if (command.isEmpty()) {
      feedback = "Enter a command";
    } else {
      currentLevel.getBoardWindow().getTextArea().append("\n > " + command);
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
        case "dance":
          feedback = "You do a funky little dance, but no one is impressed.";
          break;
        case "help":
          feedback = helpCommand();
          break;
        case "cheat":
          feedback = cheatCommand(command);
          break;
        case "quit":
          feedback = "Goodbye";
          System.exit(0);
          break;
        default:
          feedback = "I don't understand";
      }
      if (hasFinishedLevelOne()) {
        this.levelOne.getBoardWindow().hideBoard();
        this.levelTwo.getBoardWindow().showBoard();
        this.getPlayer().setCurrentRoom(levelTwo.getStartingRoom());
        this.setCurrentLevel(levelTwo);
        getCurrentLevel().getBoardWindow().getInputField().addKeyListener(new UserInput(this));
        this.getPlayer().setLevel(levelTwo);
        this.getCurrentLevel().getBoardWindow().getBoard().setCell(this.player.getCurrentRoom().getRow(), this.player.getCurrentRoom().getColumn(), CellType.CURRENT_ROOM);
        this.levelTwo.getBoardWindow().repaint();
        feedback = levelOne.getSuccessMessage();
      }
      if (hasFinishedLevelTwo()) {
        this.levelTwo.getBoardWindow().hideBoard();
        this.levelThree.getBoardWindow().showBoard();
        this.getPlayer().setCurrentRoom(levelThree.getStartingRoom());
        this.setCurrentLevel(levelThree);
        getCurrentLevel().getBoardWindow().getInputField().addKeyListener(new UserInput(this));
        this.getPlayer().setLevel(levelThree);
        this.getCurrentLevel().getBoardWindow().getBoard().setCell(this.player.getCurrentRoom().getRow(), this.player.getCurrentRoom().getColumn(), CellType.CURRENT_ROOM);
        this.levelThree.getBoardWindow().repaint();
        feedback = levelTwo.getSuccessMessage();
      }
      if (hasFinishedGame()) {
        feedback = levelThree.getSuccessMessage()
                + "\n Do you want to quit?"
        ;
      }
    }
    currentLevel.getBoardWindow().getTextArea().append("\n" + feedback);
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
        case "up":
          feedback = player.move(Direction.NORTH);
          break;
        case "east":
        case "right":
          feedback = player.move(Direction.EAST);
          break;
        case "south":
        case "down":
          feedback = player.move(Direction.SOUTH);
          break;
        case "west":
        case "left":
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

  public String validateInspectCommand(String command) {
    String nameOfThing;
    try {
      nameOfThing = command.split(" ")[1];
    } catch (Exception e) {
      return "What did you want to inspect?";
    }
    if (!nameOfThing.equals("room")) {
      try {
        player.getCurrentRoom().getContents().get(nameOfThing).getName();
        return "No errors";
      } catch (Exception e) {
        return "That's not something you can inspect.";
      }
    } else {
      return "No errors";
    }
  }

  public String inspectCommand(String command) {
    if (!validateInspectCommand(command).equals("No errors")) {
      return validateInspectCommand(command);
    }
    String nameOfThing = command.split(" ")[1];
    if (nameOfThing.equals("room")) {
      return "You are in the " + player.getCurrentRoom().getName() + ". " + player.getCurrentRoom().listContents() +
              "\n You can go: " + player.getCurrentRoom().getDirections().keySet();
    } else {
      RoomThing foundThing = player.getCurrentRoom().getContents().get(nameOfThing);

      if (foundThing.getName().equals("fridge")) {
        levelTwo
                .getLevelRooms()
                .get("kitchen")
                .addContents(new Tool("code", "Storage code, super secret, don't share! \nThe code is super long and complex, better keep this for reference."));
        levelTwo
                .getLevelRooms()
                .get("kitchen")
                .addContents(new Tool("packet", "A frozen meal packet, inedible in it's current state."));
      }

      if (foundThing.getName().equals("box")) {
        levelTwo
                .getLevelRooms()
                .get("storage")
                .addContents(new Tool("lockbox", "A small lockbox, still shiny."));

      }

      if (foundThing.getName().equals("sofa") && player.getCurrentRoom().getName().equals("foyer")) {
        levelThree
                .getLevelRooms()
                .get("foyer")
                .addContents(new Tool("key", "A decorative and sleek key."));
      }

      if (foundThing.getName().equals("desk") && player.getCurrentRoom().getName().equals("first mate's room")) {
        levelThree
                .getLevelRooms()
                .get("first mate's room")
                .addContents(new Tool("passcode", "The passcode for the fuel room, apparently."));
      }

      if (foundThing.getName().equals("notes") && player.getCurrentRoom().getName().equals("captain's room")) {
        levelThree
                .getLevelRooms()
                .get("captain's room")
                .addContents(new Tool("combination", "Seems to be a flight initialisation combination, for a personal escape pod."));
      }

      return foundThing.inspect();
    }
  }

  public String validateUseCommand(String command) {
    if (player.getInventory().isEmpty()) return "You don't have anything to use.";
    if (command.split(" ").length < 2) return "What do you want to use?";
    String firstInput = command.split(" ")[1];
    if (!player.getInventory().containsKey(firstInput)) return "That's not a thing in your inventory.";
    if (command.split(" ").length < 3) return "What do you want to use that on?";
    String secondInput = command.split(" ")[2];
    if (!player.getCurrentRoom().getContents().containsKey(secondInput)) return "That's not a thing in this room.";
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

    if (firstThing.equals("screwdriver") && secondThing.equals("vent") && roomName.equals("office")) {
      levelOne
              .getLevelRooms()
              .get("office")
              .putDirection(Direction.NORTH, levelOne.getLevelRooms().get("vent"));
      return "You unscrew the cover of the vent, making just enough space for you to crawl through.";
    }

    if (firstThing.equals("packet") && secondThing.equals("heater") && roomName.equals("kitchen")) {
      player.getInventory().put("meal", new Tool("meal", "A hot meal, functional but tasty enough."));
      player.getInventory().remove("packet");
      return "You pop the packet into the heater, wait the requisite 2 minutes and retrieve the now surprisingly tasty looking meal";
    }

    if (firstThing.equals("meal") && secondThing.equals("annie") && roomName.equals("bunks")) {
      player.getInventory().put("key", new Tool("key", "A small key, shiny and sleek."));
      player.getInventory().remove("meal");
      return "The child hungrily snatches the meal from you, and after snarfing about half of it, hands over a small key in return.";
    }

    if (firstThing.equals("code") && secondThing.equals("keypad") && roomName.equals("accessway")) {
      player.getInventory().remove("code");
      levelTwo
              .getLevelRooms()
              .get("accessway")
              .putDirection(Direction.NORTH, levelTwo.getLevelRooms().get("storage"));
      levelTwo
              .getLevelRooms()
              .get("storage")
              .putDirection(Direction.SOUTH, levelTwo.getLevelRooms().get("accessway"));
      return "You open the door to the storage room, and a musty scent drifts out.";
    }

    if (firstThing.equals("key") && secondThing.equals("lockbox") && roomName.equals("storage")) {
      player.getInventory().remove("key");
      levelTwo
              .getLevelRooms()
              .get("storage")
              .addContents(new Tool("keycard", "An old but functional keycard."));
      return "The lock on the lockbox clicks open, revealing an old but valid keycard.";
    }

    if (firstThing.equals("keycard") && secondThing.equals("scanner") && roomName.equals("accessway")) {
      player.getInventory().remove("keycard");
      levelTwo
              .getLevelRooms()
              .get("accessway")
              .putDirection(Direction.EAST, levelTwo.getLevelRooms().get("stairway"));
      return "You open the stairway down to the flight deck, although the faulty scanner eats the keycard.";
    }

    if (firstThing.equals("key") && secondThing.equals("padlock") && roomName.equals("foyer")) {
      player.getInventory().remove("key");
      levelThree
              .getLevelRooms()
              .get("foyer")
              .putDirection(Direction.NORTH, levelThree.getLevelRooms().get("captain's room"));
      levelThree
              .getLevelRooms()
              .get("captain's room")
              .putDirection(Direction.SOUTH, levelThree.getLevelRooms().get("foyer"));
      levelThree
              .getLevelRooms()
              .get("foyer")
              .getContents()
              .remove("padlock");
      return "You open the padlock, unlocking the door to the North.";
    }

    if (firstThing.equals("passcode") && secondThing.equals("keypad") && roomName.equals("corridortwo")) {
      player.getInventory().remove("passcode");
      levelThree
              .getLevelRooms()
              .get("corridorTwo")
              .putDirection(Direction.SOUTH, levelThree.getLevelRooms().get("fuel room"));
      levelThree
              .getLevelRooms()
              .get("fuel room")
              .putDirection(Direction.NORTH, levelThree.getLevelRooms().get("corridorTwo"));
      return "You open the door to the South.";
    }

    if (firstThing.equals("canister") && secondThing.equals("pod") && roomName.equals("hanger")) {
      player.getInventory().remove("canister");
      levelThree
              .getLevelRooms()
              .get("hanger")
              .getContents()
              .remove("pod");
      levelThree
              .getLevelRooms()
              .get("hanger")
              .addContents(new Decoration("partialpod", "A partially fuelled escape pod."));
      levelThree
              .getLevelRooms()
              .get("fuel room")
              .addContents(new Tool("canister", "Another fuel canister, should finish the job."));
      return "The pod seems partially fuelled, but not enough to get you to the planet's surface.";
    }

    if (firstThing.equals("canister") && secondThing.equals("partialpod") && roomName.equals("hanger")) {
      player.getInventory().remove("canister");
      levelThree
              .getLevelRooms()
              .get("hanger")
              .getContents()
              .remove("partialpod");
      levelThree
              .getLevelRooms()
              .get("hanger")
              .addContents(new Decoration("escape pod", "A fully fuelled escape pod on the North wall."));
      return "You fully fuel the escape pod, time to activate it and get off this wreck.";
    }

    if (firstThing.equals("combination") && secondThing.equals("screen") && roomName.equals("hanger") && levelThree.getLevelRooms().get("hanger").getContents().containsKey("escape pod")) {
      player.getInventory().remove("combination");
      levelThree
              .getLevelRooms()
              .get("hanger")
              .putDirection(Direction.NORTH, levelThree.getLevelRooms().get("escape pod"));
      return "The escape pod whirs to life, and is ready for takeoff!";
    }

    return "That doesn't work here.";
  }

  public String validateTalkCommand(String command) {
    if (player.getCurrentRoom().getContents().isEmpty()) return "This is an empty room...";
    if (command.split(" ").length < 2) return "Who did you want to talk to?";
    String input = command.split(" ")[1].toLowerCase();
    if (!player.getCurrentRoom().getContents().containsKey(input)) return "That's not something in this room";
    RoomThing foundItem = player.getCurrentRoom().getContents().get(input);
    return SpeakableChecker.isSpeakable(foundItem) ? "No errors" : "It doesn't talk back.";
  }

  public String talkToCommand(String command) {
    if (!validateTalkCommand(command).equals("No errors")) {
      return validateTalkCommand(command);
    }
    String feedback;
    String input = command.split(" ")[1].toLowerCase();
    Character thing = (Character) player.getCurrentRoom().getContents().get(input);
    feedback = thing.talkTo();
    if (thing.getName().equalsIgnoreCase("jimbo")) {
      try {
        levelOne
                .getLevelRooms()
                .get("workshop")
                .addContents(new Tool("screwdriver", "A universal screwdriver, this thing could open just about anything, as long as it was screwed shut at least."));
        levelOne
                .getLevelRooms()
                .get("hallway")
                .putDirection(Direction.EAST, levelOne.getLevelRooms().get("locker room"));
        levelOne
                .getLevelRooms()
                .get("locker room")
                .putDirection(Direction.WEST, levelOne.getLevelRooms().get("hallway"));
      } catch (Exception e) {
        feedback = "Jimbo couldn't do something...";
      }
    }
    return feedback;
  }

  public String validateTakeCommand(String command) {
    String input;
    if (player.getCurrentRoom().getContents().isEmpty()) return "This room is empty.";
    if (command.split(" ").length < 2) return "What do you want to take?";
    input = command.split(" ")[1];
    if (!player.getCurrentRoom().getContents().containsKey(input)) return "That's not something in this room";
    RoomThing foundItem = player.getCurrentRoom().getContents().get(input);
    return TakeableChecker.isTakeable(foundItem) ? "No errors" : "You can't take that.";
  }

  public String takeCommand(String command) {
    if (!validateTakeCommand(command).equals("No errors")) {
      return validateTakeCommand(command);
    } else {
      String input = command.split(" ")[1];
      Tool foundThing = (Tool) player.getCurrentRoom().getContents().get(input);
      return foundThing.takeThing(player.getInventory(), player.getCurrentRoom().getContents());
    }
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

  public String helpCommand() {
    return Descriptions.HELP;
  }

  public String validateCheatCommand(String command) {
    if (command.split(" ").length < 2) return "Are you trying to cheat?";
    return "No errors";
  }

  public String cheatCommand(String command) {
    if (!validateCheatCommand(command).equals("No errors")) {
      return validateCheatCommand(command);
    } else {
      switch (command.split(" ")[1]) {
        case "primus":
          return skipLevelOne();
        case "doubletrouble":
          return skipLevelTwo();
        case "triplethreat":
          return skipLevelThree();
        default:
          return "Cheat failed, ya cheater.";
      }
    }
  }

  public String skipLevelOne() {
    player.setCurrentRoom(levelOne.getEndingRoom());
    return "You have skipped level one.";
  }

  public String skipLevelTwo() {
    player.setCurrentRoom(levelTwo.getEndingRoom());
    return "You have skipped level two.";
  }

  public String skipLevelThree() {
    player.setCurrentRoom(levelThree.getEndingRoom());
    return "You have skipped level three.";
  }

}
