package de.hauschild.dodge.android;

import playn.android.GameActivity;
import playn.core.PlayN;
import de.hauschild.dodge.core.Dodge;

public class DodgeActivity extends GameActivity {

  @Override
  public void main() {
    PlayN.run(new Dodge());
  }

}
