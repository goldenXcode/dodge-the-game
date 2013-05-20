package de.hauschild.dodge.core.state;

import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.PlayN;
import de.hauschild.dodge.core.Utils;

public abstract class AbstractGameState implements GameState {

  private GameStateController gameStateController;
  protected static int DODGE_TEXT_SIZE = 60;

  public void addDefaultDodgeText(final GroupLayer rootLayer) {
    Utils.addMessageText(rootLayer, 100, 100, "dodge", DODGE_TEXT_SIZE, Color.rgb(255, 0, 0));
  }

  protected GameStateController getGameStateController() {
    return gameStateController;
  }

  @Override
  public void init(final GameStateController gameStateController) {
    this.gameStateController = gameStateController;
    PlayN.graphics().rootLayer().clear();
  }
}
