package de.hauschild.dodge.core.game;

import playn.core.GroupLayer;
import playn.core.PlayN;

public class Enemy extends Actor {

  public Enemy(final GroupLayer rootLayer) {
    super("images/enemy.png", rootLayer, new Point(PlayN.random() * PlayN.graphics().width(), PlayN.random()
        * PlayN.graphics().height()), new Point(PlayN.random() - 0.5f, PlayN.random() - 0.5f).normalized().multiply(5f));
  }

  @Override
  public void update(final int delta) {
    final float x = bounce(getPosition().getX(), PlayN.graphics().width(), getVelocity().getX());
    final float y = bounce(getPosition().getY(), PlayN.graphics().height(), getVelocity().getY());
    if (x != 0f || y != 0f) {
      if (y == 0f) {
        setVelocity(new Point(x, getVelocity().getY()));
      } else if (x == 0f) {
        setVelocity(new Point(getVelocity().getX(), y));
      } else {
        setVelocity(new Point(x, y));
      }
    }
    super.update(delta);
  }

  private float bounce(final float value, final float max, final float velocity) {
    if (value < 0f || value > max) {
      return -velocity;
    }
    return 0f;
  }

}
