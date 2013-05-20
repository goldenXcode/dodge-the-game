package de.hauschild.dodge.core.highscore;

import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.PlayN;
import playn.core.Pointer.Event;
import playn.core.Pointer.Listener;
import de.hauschild.dodge.core.Utils;
import de.hauschild.dodge.core.state.AbstractGameState;
import de.hauschild.dodge.core.state.GameStateController;
import de.hauschild.dodge.core.state.GameStateType;

public class HighscoreGameState extends AbstractGameState {

  private static final int GRAY = Color.rgb(75, 75, 75);

  @Override
  public GameStateType getType() {
    return GameStateType.HIGHSCORE;
  }

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    final GroupLayer rootLayer = PlayN.graphics().rootLayer();
    rootLayer.clear();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    Utils.addMessageText(rootLayer, 100, 60, "End!", 20, GRAY);
    Utils.addMessageText(rootLayer, 100, 160, "Current score:", 20, GRAY);
    Utils.addMessageText(rootLayer, 300, 160, String.valueOf(Highscore.getCurrent()), 20, GRAY);
    Utils.addMessageText(rootLayer, 100, 260, "Best score:", 20, GRAY);
    Utils.addMessageText(rootLayer, 300, 260, String.valueOf(Highscore.getBest()), 20, GRAY);
    if (Highscore.getCurrent() == Highscore.getBest()) {
      Utils.addMessageText(rootLayer, 100, 360, "new Highscore", 30, Color.rgb(75, 175, 75));
    }
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
        getGameStateController().navigateTo(GameStateType.WELCOME_MENU);
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
