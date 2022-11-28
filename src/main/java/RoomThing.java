import java.util.HashMap;

abstract public class RoomThing {

  public String name;

  public String description;

  public RoomThing(String name, String description) {
    this.name = name;
    this.description = description;
  }

  abstract public String takeThing(HashMap<String, RoomThingTool> inventory, HashMap<String, RoomThing> contents);

  abstract public String talkTo();

  public String getName() {
    return name;
  }

  public String inspect() {
    return description;
  }
}
