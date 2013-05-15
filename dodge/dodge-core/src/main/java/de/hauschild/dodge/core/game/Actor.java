package de.hauschild.dodge.core.game;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.util.Callback;

public abstract class Actor {

  private final ImageLayer layer;
  private Point position;
  private Point velocity;
  private Point size;

  public Actor(final String imagePath, final GroupLayer rootLayer, final Point position, final Point velocity) {
    final Image image = PlayN.assets().getImage(imagePath);
    layer = PlayN.graphics().createImageLayer(image);
    this.position = position;
    this.velocity = velocity;
    image.addCallback(new Callback<Image>() {

      @Override
      public void onFailure(final Throwable error) {
        PlayN.log().error("Error loading image!", error);
      }

      @Override
      public void onSuccess(final Image image) {
        size = new Point(image.width(), image.height());
        layer.setOrigin(image.width() / 2f, image.height() / 2f);
        layer.setTranslation(position.getX(), position.getY());
        rootLayer.add(layer);
      }

    });
  }

  public boolean collides(final Actor other) {
    final Point distance = getPosition().distance(other.getPosition());
    if (getSize() == null || other.getSize() == null) {
      return false;
    }
    final Point minDistance = new Point(getSize().getX() / 2f + other.getSize().getX() / 2f, getSize().getY() / 2f
        + other.getSize().getY() / 2f);
    return distance.getX() <= minDistance.getX() && distance.getY() <= minDistance.getY();
  }

  public void paint(final float alpha) {
    layer.setTranslation(position.getX(), position.getY());
  }

  public void update(final int delta) {
    position = position.add(velocity);
    bounce();
  }

  protected Point getPosition() {
    return position;
  }

  protected Point getSize() {
    return size;
  }

  protected Point getVelocity() {
    return velocity;
  }

  protected void setVelocity(final Point velocity) {
    this.velocity = velocity;
  }

  private void bounce() {
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
  }

  private float bounce(final float value, final float max, final float velocity) {
    if (value < 0f || value > max) {
      return -velocity;
    }
    return 0f;
  }

}
