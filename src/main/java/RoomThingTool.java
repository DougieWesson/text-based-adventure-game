import java.util.HashMap;

public class RoomThingTool extends RoomThing {

  public RoomThingTool(String name, String description) {
    super(name, description);
  }

  @Override
  public String takeThing(HashMap<String, RoomThingTool> inventory, HashMap<String, RoomThing> contents) {
    inventory.put(this.getName(), this);
    contents.remove(this.name);
    return "You pick up the " + this.name;
  }

  @Override
  public String talkTo() {
    return "It doesn't talk back...";
  }
}
