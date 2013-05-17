package de.hauschild.dodge.java;

import playn.java.JavaPlatform;
import de.hauschild.dodge.core.Dodge;

public class DodgeJava {

  public static void main(final String[] args) {
    JavaUtils.setupNatives();
    final JavaPlatform javaPlatform = JavaPlatform.register();
    javaPlatform.setTitle("dodge");
    javaPlatform.run(new Dodge());
  }

}
