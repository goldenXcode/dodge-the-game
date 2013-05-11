package de.hauschild.dodge.core.game;

import java.util.ArrayList;
import java.util.List;

import playn.core.GroupLayer;

public class Enemies {

  private final List<Enemy> enemies = new ArrayList<Enemy>();

  public Enemies(final int enemyCount, final GroupLayer rootLayer) {
    for (int i = 0; i < enemyCount; i++) {
      enemies.add(new Enemy(rootLayer));
    }
  }

  public boolean collides(final Player player) {
    for (final Enemy enemy : enemies) {
      if (enemy.collides(player)) {
        return true;
      }
    }
    return false;
  }

  public void paint(final float alpha) {
    for (final Enemy enemy : enemies) {
      enemy.paint(alpha);
    }
  }

  public void update(final int delta) {
    for (final Enemy enemy : enemies) {
      enemy.update(delta);
    }
  }

}
