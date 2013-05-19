package de.hauschild.dodge.core.welcome;

import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.PlayN;
import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;
import de.hauschild.dodge.core.Utils;
import de.hauschild.dodge.core.state.AbstractGameState;
import de.hauschild.dodge.core.state.GameStateController;

public class WelcomeGameState extends AbstractGameState {

  private static int DODGE_TEXT_SCALE = 60;
  private GroupLayer rootLayer;
  private boolean goToNextState;
  private boolean startFadeOut;
  private float angle = 0;
  private float scale = 1;
  private float smoothAngle;
  private float smoothScale;

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    rootLayer = PlayN.graphics().rootLayer();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    Utils.addMessageText(rootLayer, 200, 200, "dodge", DODGE_TEXT_SCALE, Color.rgb(255, 0, 0));
    Utils.addMessageText(rootLayer, 500, 460, "@2013 Klaus Hauschild", 12, Color.rgb(0, 0, 0));
    PlayN.pointer().setListener(new Listener() {

      @Override
      public void onPointerCancel(final Event event) {
      }

      @Override
      public void onPointerDrag(final Event event) {
      }

      @Override
      public void onPointerEnd(final Event event) {

      }

      @Override
      public void onPointerStart(final Event event) {
        startFadeOut = true;
      }
    });
  }

  @Override
  public void paint(final float alpha) {
    // TODO: get better x value from text to set middle
    rootLayer.get(1).setOrigin(70, DODGE_TEXT_SCALE / 2);
    rootLayer.get(1).setRotation(smoothAngle);

    if (startFadeOut && smoothScale > 1) {
      rootLayer.get(1).setScale((smoothScale));
    }
  }

  @Override
  public void update(final int delta) {
    angle++;
    smoothAngle = angle / delta;

    if (startFadeOut) {
      scale++;
      smoothScale = scale / (delta * .75f);

      if (scale == 150) {
        goToNextState = true;
      }
    }

    if (goToNextState) {
      getGameStateController().nextState();
    }
  }
}
