package de.hauschild.dodge.core.welcome;

import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.Keyboard;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;
import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;
import de.hauschild.dodge.core.Utils;
import de.hauschild.dodge.core.state.AbstractGameState;
import de.hauschild.dodge.core.state.GameStateController;
import de.hauschild.dodge.core.state.GameStateType;

public class WelcomeGameState extends AbstractGameState {

  private GroupLayer rootLayer;
  private boolean goToNextState;
  private boolean startFadeOut;
  private float angle;
  private float scale;
  private float smoothAngle;
  private float smoothScale;

  @Override
  public GameStateType getType() {
    return GameStateType.WELCOME;
  }

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);

    // init params
    angle = 0;
    scale = 1;

    rootLayer = PlayN.graphics().rootLayer();
    rootLayer.clear();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    addDefaultDodgeText(rootLayer);
    Utils.addMessageText(rootLayer, 380, 400, "Press any key or click...", 20, Color.rgb(255, 0, 0));
    Utils.addMessageText(rootLayer, 500, 460, "@2013 Klaus Hauschild", 12, Color.rgb(0, 0, 0));
    PlayN.keyboard().setListener(new Keyboard.Listener() {

      @Override
      public void onKeyDown(final playn.core.Keyboard.Event event) {
        startFadeOut = true;
      }

      @Override
      public void onKeyTyped(final TypedEvent event) {

      }

      @Override
      public void onKeyUp(final playn.core.Keyboard.Event event) {

      }
    });

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
    rootLayer.get(1).setOrigin(70, DODGE_TEXT_SIZE / 2);
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
