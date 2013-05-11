package de.hauschild.dodge.core.state;

public abstract class AbstractGameState implements GameState {

  private GameStateController gameStateController;

  @Override
  public void init(final GameStateController gameStateController) {
    this.gameStateController = gameStateController;
  }

  protected GameStateController getGameStateController() {
    return gameStateController;
  }

}
