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

  private GroupLayer rootLayer;
  private boolean goToNextState;
  private boolean startFadeOut;
  private int angle = 0;
  private int scale = 0;

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    rootLayer = PlayN.graphics().rootLayer();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    Utils.addMessageText(rootLayer, 200, 200, "dodge", 60, Color.rgb(255, 0, 0));
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

  }

  @Override
  public void update(final int delta) {
    angle++;
    final float smoothRotateAngel = (float) (angle * -(delta * .25));
    rootLayer.get(1).setRotation(smoothRotateAngel);
    if (smoothRotateAngel > 360) {
      angle = 0;
    }

    if (startFadeOut) {
      scale++;
      rootLayer.get(1).setScale((float) (scale / -(delta * .75)));
      if (scale == 100) {
        goToNextState = true;
      }
    }

    if (goToNextState) {
      getGameStateController().nextState();
    }
  }
}
