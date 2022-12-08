import java.util.Arrays;
import java.util.function.Predicate;

public class TakeableChecker {
  public static boolean isTakeable(RoomThing roomThing) {
    return Arrays.stream(roomThing.getClass().getInterfaces()).anyMatch(Predicate.isEqual(Takeable.class));
  }
}
