package de.hauschild.dodge.java;

import java.lang.reflect.Field;

import playn.core.PlayN;
import playn.java.JavaPlatform;
import de.hauschild.dodge.core.Dodge;

public class DodgeJava {

  public static void main(final String[] args) {
    final String libraryPath = System.getProperty("java.library.path");
    if (!libraryPath.contains("natives")) {
      setLibraryPath("natives");
    }
    JavaPlatform.register();
    PlayN.run(new Dodge());
  }

  private static void setLibraryPath(final String libraryPath) {
    System.setProperty("java.library.path", libraryPath);
    try {
      final Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
      fieldSysPath.setAccessible(true);
      fieldSysPath.set(null, null);
    } catch (final Exception exception) {
      throw new RuntimeException(exception);
    }
  }

}
