package de.hauschild.dodge.core.welcome;

import java.util.ArrayList;
import java.util.List;

import playn.core.Canvas;
import playn.core.CanvasImage;
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

    public GameStateType getGameStateType() {
      return gameStateType;
    }

    public String getText() {
      return text;
    }

  }

  private class MenuEntryManager {

    private final List<MenuEntry> menuEntries = new ArrayList<MenuEntry>();
    private int pos = 1;

    public MenuEntryManager() {
      createDefaultEntries();
    }

    private void createDefaultEntries() {
      menuEntries.add(new MenuEntry("Start", GameStateType.GAME));
      menuEntries.add(new MenuEntry("Highscore", GameStateType.HIGHSCORE));
      menuEntries.add(new MenuEntry("End", GameStateType.END_GAME));
    }

    public List<MenuEntry> getEntryies() {
      return menuEntries;
    }

    public MenuEntry getSelected() {
      return menuEntries.get(pos - 1);
    }

    public void next() {
      pos++;
      if (pos > menuEntryManager.getEntryies().size()) {
        pos = 1;
      }
    }

    public void previouse() {
      pos--;
      if (pos < 1) {
        pos = menuEntryManager.getEntryies().size();
      }

    }
  }

  private static final int MENU_ENTRY_X_POS = (PlayN.graphics().width() / 2) + 100;
  private static final int MENU_ENTRY_TEXT_SIZE = 20;
  private GroupLayer rootLayer;
  private final MenuEntryManager menuEntryManager = new MenuEntryManager();

  private void createHighlightCanvas(final int indexToHighlight) {
    final CanvasImage rectImage = PlayN.graphics().createImage(PlayN.graphics().width(), PlayN.graphics().height());
    final Canvas canvas = rectImage.canvas();
    canvas.setFillColor(Color.argb(100, 255, 255, 0));
    canvas.fillRoundRect(rootLayer.get(indexToHighlight).tx() - 20, rootLayer.get(indexToHighlight).ty(), 140, 30, 15);
    rootLayer.add(PlayN.graphics().createImageLayer(rectImage));
  }

  private void createMenuEntriesUi() {
    int yPos = 50;
    for (final MenuEntry entry : menuEntryManager.getEntryies()) {
      yPos = yPos + 50;
      Utils.addMessageText(rootLayer, MENU_ENTRY_X_POS, yPos, entry.getText(), MENU_ENTRY_TEXT_SIZE, Color.rgb(255, 0, 0));
    }
  }

  @Override
  public GameStateType getType() {
    return GameStateType.WELCOME_MENU;
  }

  private void highlightMenuEntryPosition() {

    for (int i = 2; i <= 4; i++) {
      rootLayer.get(i).setTint(Color.argb(255, 255, 0, 0));
    }
    // TODO: text should ne yellow?
    // +2 to ignore background layer and "dodge" layer
    final int indexToHighlight = menuEntryManager.getEntryies().indexOf(menuEntryManager.getSelected()) + 2;
    // rootLayer.get(indexToHighlight).setTint(Color.argb(100, 255, 255, 0));
    createHighlightCanvas(indexToHighlight);
  }

  @Override
  public void init(final GameStateController gameStateController) {
    super.init(gameStateController);
    rootLayer = PlayN.graphics().rootLayer();
    Utils.addBackGround(rootLayer, Color.rgb(255, 255, 255));
    addDefaultDodgeText(rootLayer);
    createMenuEntriesUi();
    highlightMenuEntryPosition();
    PlayN.keyboard().setListener(new Listener() {

      @Override
      public void onKeyDown(final Event event) {
        removeHighlightCanvas();
        if (event.key().equals(Key.DOWN)) {
          menuEntryManager.next();
          highlightMenuEntryPosition();
        } else if (event.key().equals(Key.UP)) {
          menuEntryManager.previouse();
          highlightMenuEntryPosition();
        }
        if (event.key().equals(Key.ENTER)) {
          final int selectedIndex = menuEntryManager.getEntryies().indexOf(menuEntryManager.getSelected());
          gameStateController.navigateTo(menuEntryManager.getEntryies().get(selectedIndex).getGameStateType());
        }
      }

      @Override
      public void onKeyTyped(final TypedEvent event) {
        // NOP
      }

      @Override
      public void onKeyUp(final Event event) {
        // NOP
      }

      private void removeHighlightCanvas() {
        rootLayer.remove(rootLayer.get(rootLayer.size() - 1));
      }
    });
  }

  @Override
  public void paint(final float alpha) {
    // NOP
  }

  @Override
  public void update(final int delta) {
    // NOP
  }

}
