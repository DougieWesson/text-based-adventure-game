import java.util.HashMap;

public class RoomThingDecoration extends RoomThing {
  public RoomThingDecoration(String name, String description) {
    super(name, description);
  }

  @Override
  public String takeThing(HashMap<String, RoomThingTool> inventory, HashMap<String, RoomThing> contents) {
    return "You can't take that?";
  }

  @Override
  public String talkTo() {
    return "It doesn't talk back...";
  }
}
