import java.util.Arrays;
import java.util.function.Predicate;

public class SpeakableChecker {

  public static boolean isSpeakable(RoomThing roomThing) {
    return Arrays.stream(roomThing.getClass().getInterfaces()).anyMatch(Predicate.isEqual(Speakable.class));
  }
}
