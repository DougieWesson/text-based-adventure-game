import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Room {

  private final String name;

  private final String description;

  private HashMap<Direction, Room> directions;

  private HashMap<String, RoomThing> contents;
  private int row;

  private int column;

  private int levelWidth;

  private int levelHeight;

  public Room(String name, String description, int levelWidth, int levelHeight) {
    this.name = name;
    this.description = description;
    this.directions = new HashMap<>();
    this.contents = new HashMap<>();
    this.levelWidth = levelWidth;
    this.levelHeight = levelHeight;
  }

  public Room(String name, String description, int levelWidth, int levelHeight, int row, int column) {
    this.name = name;
    this.description = description;
    this.row = row;
    this.column = column;
    this.directions = new HashMap<>();
    this.contents = new HashMap<>();
    this.levelWidth = levelWidth;
    this.levelHeight = levelHeight;
  }

  public String getName() {
    return name;
  }

  public void putDirection(Direction direction, Room room) {
    if (direction == Direction.EAST) {
      if (this.column + 1 >= levelWidth) {
        throw new ArrayIndexOutOfBoundsException();
      }
      room.setRow(this.row);
      room.setColumn(this.column + 1);
    } else if (direction == Direction.SOUTH) {
      if (this.row + 1 >= levelHeight) {
        throw new ArrayIndexOutOfBoundsException();
      }
      room.setRow(this.row + 1);
      room.setColumn(this.column);
    } else if (direction == Direction.WEST) {
      if ((this.column - 1) < 0) {
        throw new ArrayIndexOutOfBoundsException();
      }
      room.setRow(this.row);
      room.setColumn(this.column - 1);
    } else {
      if (this.row - 1 < 0) {
        throw new ArrayIndexOutOfBoundsException();
      }
      room.setRow(this.row - 1);
      room.setColumn(this.column);
    }
    this.directions.put(direction, room);
  }
  public String getDescription() {
    return description;
  }

  public HashMap<Direction, Room> getDirections() {
    return directions;
  }

  public HashMap<String, RoomThing> getContents() {
    return contents;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getLevelWidth() {
    return levelWidth;
  }

  public void setLevelWidth(int levelWidth) {
    this.levelWidth = levelWidth;
  }

  public int getLevelHeight() {
    return levelHeight;
  }

  public void setLevelHeight(int levelHeight) {
    this.levelHeight = levelHeight;
  }

  public void setContents(HashMap<String, RoomThing> contents) {
    this.contents = contents;
  }

  public void addContents(RoomThing thing) {
    this.contents.put(thing.name.toLowerCase(), thing);
  }

  public void putContents(RoomThing thing) {
    this.contents.put(thing.name.toLowerCase(), thing);
  }

  public String listContents() {
    if (contents.size() < 1) {
      return "There is nothing of note in this room";
    } else {
      String contentsList = "you look around the room, you see the things below: \n";
      contentsList += String.join(", ", contents.keySet());
      return contentsList;
    }
  }
}
