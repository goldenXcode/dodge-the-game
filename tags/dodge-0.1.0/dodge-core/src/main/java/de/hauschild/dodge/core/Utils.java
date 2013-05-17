package de.hauschild.dodge.core;

import playn.core.CanvasImage;
import playn.core.Font;
import playn.core.GroupLayer;
import playn.core.Layer;
import playn.core.PlayN;
import playn.core.SurfaceImage;
import playn.core.TextFormat;
import playn.core.TextLayout;

public abstract class Utils {

  public static void addBackGround(final GroupLayer layer, final int color) {
    final SurfaceImage background = PlayN.graphics().createSurface(PlayN.graphics().width(), PlayN.graphics().height());
    background.surface().setFillColor(color);
    background.surface().fillRect(0, 0, background.width(), background.height());
    layer.add(PlayN.graphics().createImageLayer(background));
  }

  public static void addMessageText(final GroupLayer layer, final float x, final float y, final String text,
      final int fontSize, final Integer fontColor) {
    final Layer messageText = createMessageText(text, fontSize, fontColor);
    messageText.setTranslation(x, y);
    layer.add(messageText);
  }

  public static Layer createMessageText(final String text, final int fontSize, final Integer fontColor) {
    final Font font = PlayN.graphics().createFont("Comic Sans MS", Font.Style.BOLD, fontSize);
    final TextLayout layout = PlayN.graphics().layoutText(text, new TextFormat().withFont(font).withWrapWidth(400));
    final Layer textLayer = createTextLayer(layout, fontColor);
    return textLayer;
  }

  static Layer createTextLayer(final TextLayout layout, final Integer fontColor) {
    final CanvasImage image = PlayN.graphics().createImage((int) Math.ceil(layout.width()),
        (int) Math.ceil(layout.height()));
    if (fontColor != null) {
      image.canvas().setFillColor(fontColor);
    }
    image.canvas().fillText(layout, 0, 0);
    return PlayN.graphics().createImageLayer(image);
  }

}
