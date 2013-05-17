package de.hauschild.dodge.core.game;

import playn.core.GroupLayer;
import playn.core.PlayN;
import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;

public class Player extends Actor {

  private static final float MAX_VELOCITY = 3f;
  private static final float ACCELERATION = 0.5f;
  private static final float BRAKING = .9f;

  public Player(final GroupLayer rootLayer) {
    super("images/player.png", rootLayer, new Point(PlayN.graphics().width() / 2, PlayN.graphics().height() / 2),
        Point.ZERO);
    updatePointer(getPosition());
    PlayN.pointer().setListener(new Listener() {

      @Override
      public void onPointerCancel(final Event event) {
      }

      @Override
      public void onPointerDrag(final Event event) {
        updatePointer(new Point((int) event.x(), (int) event.y()));
      }

      @Override
      public void onPointerEnd(final Event event) {
        updatePointer(new Point((int) event.x(), (int) event.y()));
      }

      @Override
      public void onPointerStart(final Event event) {
        updatePointer(new Point((int) event.x(), (int) event.y()));
      }

    });
  }

  @Override
  public void update(final int delta) {
    super.update(delta);
  }

  protected void updatePointer(final Point pointer) {
    final Point direction = pointer.subtract(getPosition());
    final Point distanceToZero = direction.distance(Point.ZERO);
    final float x = calculateValocity(distanceToZero.getX(), direction.getX(), getVelocity().getX());
    final float y = calculateValocity(distanceToZero.getY(), direction.getY(), getVelocity().getY());
    setVelocity(getVelocity().add(new Point(x, y)).multiply(BRAKING));
  }

  private float calculateValocity(final float distanceToZero, final float direction, final float velocity) {
    if (distanceToZero > 1f && velocity < MAX_VELOCITY) {
      if (direction > 0f) {
        return ACCELERATION;
      } else if (direction < 0f) {
        return -ACCELERATION;
      }
    }
    return 0f;
  }

}
