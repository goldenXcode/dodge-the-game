package de.hauschild.dodge.core.state;

import java.util.Arrays;
import java.util.List;

import de.hauschild.dodge.core.game.GameGameState;
import de.hauschild.dodge.core.highscore.HighscoreGameState;
import de.hauschild.dodge.core.welcome.WelcomeGameState;

public class GameStateController {

  private final List<GameState> gameStates = Arrays.<GameState> asList(new WelcomeGameState(), new GameGameState(),
      new HighscoreGameState());
  private int currentState = -1;

  public void init() {
    nextState();
  }

  public void nextState() {
    currentState++;
    if (currentState == gameStates.size()) {
      currentState = 0;
    }
    gameStates.get(currentState).init(this);
  }

  public void paint(final float alpha) {
    gameStates.get(currentState).paint(alpha);
  }

  public void update(final int delta) {
    gameStates.get(currentState).update(delta);
  }

}
