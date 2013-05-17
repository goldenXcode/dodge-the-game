package de.hauschild.dodge.core.game;

import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.PlayN;
import de.hauschild.dodge.core.Utils;
import de.hauschild.dodge.core.highscore.Highscore;
import de.hauschild.dodge.core.state.AbstractGameState;
import de.hauschild.dodge.core.state.GameStateController;

public class GameGameState extends AbstractGameState {

  private static int enemyCount = 0;

  private Player player;
  private Enemies enemies;
  private int start;

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    final GroupLayer rootLayer = PlayN.graphics().rootLayer();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    player = new Player(rootLayer);
    enemies = new Enemies(++enemyCount, rootLayer);
    start = PlayN.tick();
  }

  @Override
  public void paint(final float alpha) {
    player.paint(alpha);
    enemies.paint(alpha);
  }

  @Override
  public void update(final int delta) {
    player.update(delta);
    enemies.update(delta);
    if (enemies.collides(player)) {
      Highscore.setCurrent((PlayN.tick() - start) * enemyCount);
      getGameStateController().nextState();
    }
  }

}
