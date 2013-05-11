package de.hauschild.dodge.core.highscore;

public class Highscore {

  private static int best = 0;
  private static int current = 0;

  public static int getBest() {
    return best;
  }

  public static int getCurrent() {
    return current;
  }

  public static void setCurrent(final int current) {
    Highscore.current = current;
    if (Highscore.current > Highscore.best) {
      Highscore.best = current;
    }
  }

}
