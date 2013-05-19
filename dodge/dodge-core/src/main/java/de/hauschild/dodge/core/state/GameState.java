package de.hauschild.dodge.core.state;

public interface GameState {

  GameStateType getType();

  void init(GameStateController gameStateController);

  void paint(final float alpha);

  void update(final int delta);
}
