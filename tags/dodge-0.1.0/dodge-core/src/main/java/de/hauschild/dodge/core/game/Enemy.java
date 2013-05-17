package de.hauschild.dodge.core.game;

import playn.core.GroupLayer;
import playn.core.PlayN;

public class Enemy extends Actor {

  public Enemy(final GroupLayer rootLayer) {
    super("images/enemy.png", rootLayer, new Point(PlayN.random() * PlayN.graphics().width(), PlayN.random()
        * PlayN.graphics().height()), new Point(PlayN.random() - 0.5f, PlayN.random() - 0.5f).normalized().multiply(5f));
  }

}
