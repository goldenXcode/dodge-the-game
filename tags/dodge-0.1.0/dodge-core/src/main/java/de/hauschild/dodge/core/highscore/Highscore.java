package de.hauschild.dodge.core.highscore;

import playn.core.PlayN;

public class Highscore {

  private static int best = 0;
  private static int current = 0;

  static {
    final String highscore = PlayN.storage().getItem("highscore");
    if (highscore != null) {
      best = Integer.valueOf(highscore);
    }
  }

  public static int getBest() {
    return best;
  }

  public static int getCurrent() {
    return current;
  }

  public static void setCurrent(final int current) {
    Highscore.current = current;
    if (Highscore.current > best) {
      best = current;
      PlayN.storage().setItem("highscore", Integer.toString(best));
    }
  }

}
