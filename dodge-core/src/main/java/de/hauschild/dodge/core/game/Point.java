package de.hauschild.dodge.core.game;

public class Point {

  private static final float EPSILON = 0.1f;

  public static final Point ZERO = new Point(0f, 0f);

  private final float x;
  private final float y;

  public Point(final float x, final float y) {
    this.x = x;
    this.y = y;
  }

  public Point add(final Point other) {
    return new Point(x + other.getX(), y + other.getY());
  }

  public Point distance(final Point other) {
    final Point distance = subtract(other);
    return new Point(Math.abs(distance.getX()), Math.abs(distance.getY()));
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Point)) {
      return false;
    }
    final Point rhs = (Point) obj;
    final Point distance = distance(rhs);
    return distance.getX() < EPSILON && distance.getX() < EPSILON;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  @Override
  public int hashCode() {
    return (int) (27 * getX() + 31 * getY());
  }

  public float length() {
    return (float) Math.sqrt(lengthSquared());
  }

  public float lengthSquared() {
    return x * x + y * y;
  }

  public Point multiply(final float scalar) {
    return new Point(x *scalar, y *scalar);
  }

  public Point normalized() {
    final float length = length();
    return multiply(1f / length);
  }

  public Point subtract(final Point other) {
    return new Point(x - other.getX(), y - other.getY());
  }

  @Override
  public String toString() {
    return String.format("(%.2f, %.2f)", getX(), getY());
  }

}
