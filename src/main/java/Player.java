import java.util.ArrayList;
import java.util.HashMap;

public class Player {

  public static final String WRONG_WAY_MESSAGE = "you slam into a wall, physically you're fine, but emotionally you may never recover.";
  private Room currentRoom;
  private Level level;
  private HashMap<String, RoomThingTool> inventory;

  public Player(Room currentRoom, Level level) {
    this.inventory = new HashMap<>();
    System.out.println(setCurrentRoom(currentRoom));
    this.level = level;
    this.level.getBoardWindow().getBoard().setCell(currentRoom.getRow(), currentRoom.getColumn(), CellType.CURRENT_ROOM);
    this.level.getBoardWindow().repaint();
  }

  public String setCurrentRoom(Room newRoom) {
    this.currentRoom = newRoom;
    return "You are in the " + currentRoom.getName() + ".\n" + currentRoom.getDescription();
  }

  public String move(Direction direction) {
    String feedback = "";
    Room nextRoom = currentRoom.getDirections().get(direction);
    if (nextRoom != null) {
      if (this.level.getStartingRoom().equals(this.currentRoom)) {
        this.level.getBoardWindow().getBoard().setCell(currentRoom.getRow(), currentRoom.getColumn(), CellType.START);
      } else {
        this.level.getBoardWindow().getBoard().setCell(currentRoom.getRow(), currentRoom.getColumn(), CellType.VISITED);
      }
      feedback = "You have moved " + direction + "\n";
      feedback += setCurrentRoom(nextRoom);
      this.level.getBoardWindow().getBoard().setCell(currentRoom.getRow(), currentRoom.getColumn(), CellType.CURRENT_ROOM);
    } else {
      feedback = WRONG_WAY_MESSAGE;
    }
    this.level.getBoardWindow().repaint();
    return feedback;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }


  public Room getCurrentRoom() {
    return currentRoom;
  }

  public HashMap<String, RoomThingTool> getInventory() {
    return inventory;
  }
}
