import java.util.HashMap;

public class RoomThingTool extends RoomThing {

  private Boolean broken;
  private Boolean used;

  private Boolean acquired;


  public RoomThingTool(String name, String description, Boolean broken, Boolean used, Boolean acquired) {
    super(name, description);
    this.broken = broken;
    this.used = used;
    this.acquired = acquired;
  }

  @Override
  public String takeThing(HashMap<String, RoomThingTool> inventory, HashMap<String, RoomThing> contents) {
    inventory.put(this.getName(), this);
    contents.remove(this);
    return "You pick up the " + this.name;
  }

  @Override
  public String talkTo() {
    return "It doesn't talk back...";
  }

  public Boolean getBroken() {
    return broken;
  }

  public void setBroken(Boolean broken) {
    if (!this.broken) {
      this.broken = broken;
      description += "\n it is broken";
    }
  }

  public Boolean getUsed() {
    return used;
  }

  public void setUsed(Boolean used) {
    this.used = used;
  }

  public Boolean getAcquired() {
    return acquired;
  }

  public void setAcquired(Boolean acquired) {
    this.acquired = acquired;
  }
}
