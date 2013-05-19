package de.hauschild.dodge.core.welcome;

import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.PlayN;
import de.hauschild.dodge.core.Utils;
import de.hauschild.dodge.core.state.AbstractGameState;
import de.hauschild.dodge.core.state.GameStateController;
import de.hauschild.dodge.core.state.GameStateType;

public class WelccomeMenuGameState extends AbstractGameState {

  private static final int MENU_ENTRY_X_POS = (PlayN.graphics().width() / 2) + 100;
  private static final int MENU_ENTRY_TEXT_SIZE = 20;
  private GroupLayer rootLayer;

  @Override
  public GameStateType getType() {
    return GameStateType.WELCOME_MENU;
  }

  @Override
  public void init(GameStateController gameStateController) {
    super.init(gameStateController);
    rootLayer = PlayN.graphics().rootLayer();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    addDefaultDodgeText(rootLayer);
    Utils.addMessageText(rootLayer, MENU_ENTRY_X_POS, 100, "Start", MENU_ENTRY_TEXT_SIZE, Color.rgb(0, 0, 0));
    Utils.addMessageText(rootLayer, MENU_ENTRY_X_POS, 150, "Highscore", MENU_ENTRY_TEXT_SIZE, Color.rgb(0, 0, 0));
    Utils.addMessageText(rootLayer, MENU_ENTRY_X_POS, 200, "End", MENU_ENTRY_TEXT_SIZE, Color.rgb(0, 0, 0));
  }

  @Override
  public void paint(float alpha) {
    // TODO Auto-generated method stub
  }

  @Override
  public void update(int delta) {
    // TODO Auto-generated method stub
  }

}
