package lab.engine;

import javafx.scene.control.Button;

import java.util.List;

public interface Engine {
  void setField(Button btn);

  OXEnum getWinner(List<Button> buttons);

  OXEnum checkRows(List<Button> buttons);

  OXEnum checkColumns(List<Button> buttons);

  OXEnum checkCross(List<Button> buttons);
}
