package de.hauschild.dodge.core.state;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.hauschild.dodge.core.game.GameGameState;
import de.hauschild.dodge.core.highscore.HighscoreGameState;
import de.hauschild.dodge.core.welcome.WelccomeMenuGameState;
import de.hauschild.dodge.core.welcome.WelcomeGameState;

public class GameStateController {

  private final List<GameState> gameStates = Arrays.<GameState> asList(new WelcomeGameState(),
      new WelccomeMenuGameState(), new GameGameState(),
      new HighscoreGameState());
  private int currentState = -1;

  public void init() {
    nextState();
  }

  public void navigateTo(final GameStateType gameStateTypeToNavigate) {
    // find gameState
    GameState nextGameState = Iterables.find(gameStates, new Predicate<GameState>() {

      @Override
      public boolean apply(GameState arg0) {
        return arg0.getType().equals(gameStateTypeToNavigate);
      }
    });
    // find previous index and navigate to next
    currentState = gameStates.indexOf(nextGameState) - 1;
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
