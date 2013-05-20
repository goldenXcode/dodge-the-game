package de.hauschild.dodge.core.welcome;

import java.util.ArrayList;
import java.util.List;

import playn.core.Color;
import playn.core.GroupLayer;
import playn.core.Key;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;
import de.hauschild.dodge.core.Utils;
import de.hauschild.dodge.core.state.AbstractGameState;
import de.hauschild.dodge.core.state.GameStateController;
import de.hauschild.dodge.core.state.GameStateType;

public class WelccomeMenuGameState extends AbstractGameState {

  private class MenuEntry {

    private final String text;
    private final GameStateType gameStateType;

    public MenuEntry(final String text, final GameStateType gameStateType) {
      this.text = text;
      this.gameStateType = gameStateType;
    }

    public GameStateType getGameStateType(){
      return gameStateType;
    }

    public String getText() {
      return text;
    }

  }

  private class MenuEntryManager {

    private final List<MenuEntry> menuEntries = new ArrayList<MenuEntry>();

    public MenuEntryManager() {
      createDefaultEntries();
    }

    private void createDefaultEntries() {
      menuEntries.add(new MenuEntry("Start", GameStateType.GAME));
      menuEntries.add(new MenuEntry("Highscore", GameStateType.HIGHSCORE));
      menuEntries.add(new MenuEntry("End", GameStateType.WELCOME));
    }

    public List<MenuEntry> getEntryies() {
      return menuEntries;
    }
  }

  private static final int MENU_ENTRY_X_POS = (PlayN.graphics().width() / 2) + 100;
  private static final int MENU_ENTRY_TEXT_SIZE = 20;
  private GroupLayer rootLayer;
  private MenuEntryManager menuEntryManager;
  private int pos = 1;

  @Override
  public GameStateType getType() {
    return GameStateType.WELCOME_MENU;
  }

  protected void highLightMenuEntryPosition(final int pos) {
    //TODO: text should ne yellow?
    rootLayer.get(pos + 1).setTint(Color.argb(100, 255, 255, 0));
    rootLayer.get(pos).setTint(Color.argb(255, 255, 0, 0));
  }

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    rootLayer = PlayN.graphics().rootLayer();
    rootLayer.clear();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    addDefaultDodgeText(rootLayer);

    menuEntryManager = new MenuEntryManager();
    int yPos = 50;
    for (MenuEntry entry : menuEntryManager.getEntryies()) {
      yPos = yPos + 50;
      Utils
      .addMessageText(rootLayer, MENU_ENTRY_X_POS, yPos, entry.getText(), MENU_ENTRY_TEXT_SIZE,
          Color.rgb(255, 0, 0));
    }
    PlayN.keyboard().setListener(new Listener() {


      @Override
      public void onKeyDown(Event event) {
        // TODO: logic for position to MenuEntryManager, e.g. nextEntry, previousEntry
        if (event.key().equals(Key.DOWN)) {
          pos++;
          if (pos > menuEntryManager.getEntryies().size()) {
            pos = 1;
          }
        } else if (event.key().equals(Key.UP)) {
          pos--;
          if (pos < 1) {
            pos = menuEntryManager.getEntryies().size();
          }
        }
        if (event.key().equals(Key.ENTER)) {
          gameStateController.navigateTo(menuEntryManager.getEntryies().get(pos - 1).getGameStateType());
        }
        System.out.println("pos " + pos);
      }

      @Override
      public void onKeyTyped(TypedEvent event) {
        // NOP
      }

      @Override
      public void onKeyUp(Event event) {
        // NOP
      }
    });
  }

  @Override
  public void paint(float alpha) {
    highLightMenuEntryPosition(pos);
  }

  @Override
  public void update(int delta) {
    // TODO Auto-generated method stub
  }

}
