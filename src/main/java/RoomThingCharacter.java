import java.util.HashMap;

public class RoomThingCharacter extends RoomThing {

  private String speech;

  public RoomThingCharacter(String name, String description, String speech) {
    super(name, description);
    this.speech = speech;
  }

  @Override
  public String takeThing(HashMap<String, RoomThingTool> inventory, HashMap<String, RoomThing> contents) {
    return "They aren't going in your pocket...";
  }

  @Override
  public String talkTo() {
    return speech;
  }
}
