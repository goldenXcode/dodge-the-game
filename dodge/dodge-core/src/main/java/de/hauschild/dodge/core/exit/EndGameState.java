package de.hauschild.dodge.core.exit;

import de.hauschild.dodge.core.state.AbstractGameState;
import de.hauschild.dodge.core.state.GameStateController;
import de.hauschild.dodge.core.state.GameStateType;

public class EndGameState extends AbstractGameState {

  @Override
  public GameStateType getType() {
    return GameStateType.END_GAME;
  }

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    System.exit(0);

  }

  @Override
  public void paint(final float alpha) {

  }

  @Override
  public void update(final int delta) {

  }

}
