package de.hauschild.dodge.core;

import playn.core.Game;
import de.hauschild.dodge.core.state.GameStateController;

public class Dodge extends Game.Default {

  private final GameStateController gameStateController = new GameStateController();

  public Dodge() {
    super(25);
  }

  @Override
  public void init() {
    gameStateController.init();
  }

  @Override
  public void paint(final float alpha) {
    gameStateController.paint(alpha);
  }

  @Override
  public void update(final int delta) {
    gameStateController.update(delta);
  }

}
