package de.hauschild.dodge.java;

import java.io.File;
import java.lang.reflect.Field;

public abstract class JavaUtils {

  public static void setupNatives() {
    final File nativesPath = searchNativesPath();
    setLibraryPath(nativesPath);
  }

  private static File searchNativesPath() {
    return searchNativesPath(new File("."));
  }

  private static File searchNativesPath(final File file) {
    for (final File child : file.listFiles()) {
      if ("natives".equals(child.getName())) {
        return child;
      }
      if (child.isDirectory()) {
        final File nativesPath = searchNativesPath(child);
        if (nativesPath != null) {
          return nativesPath;
        }
      }
    }
    return null;
  }

  private static void setLibraryPath(final File nativesPath) {
    System.setProperty("java.library.path", nativesPath.getAbsolutePath());
    try {
      final Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
      fieldSysPath.setAccessible(true);
      fieldSysPath.set(null, null);
    } catch (final Exception exception) {
      throw new RuntimeException(exception);
    }
  }

}
