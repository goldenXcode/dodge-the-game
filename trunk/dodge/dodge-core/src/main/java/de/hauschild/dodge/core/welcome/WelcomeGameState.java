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

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    final GroupLayer rootLayer = PlayN.graphics().rootLayer();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    Utils.addMessageText(rootLayer, 100, 100, "dodge", 60, Color.rgb(255, 0, 0));
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
        getGameStateController().nextState();
      }
    });
  }

  @Override
  public void paint(final float alpha) {
  }

  @Override
  public void update(final int delta) {
  }

}
