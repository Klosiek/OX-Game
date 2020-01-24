package lab.engine;

import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OXGameImpl implements OXGame {
  private static final Logger LOGGER = LoggerFactory.getLogger(OXGameImpl.class);
  private OXEnum actualPlayer = OXEnum.O;

  @Override
  public void setField(Button btn) {
    btn.setText(actualPlayer.toString());
    if (actualPlayer == OXEnum.O) {
      actualPlayer = OXEnum.X;
    } else {
      actualPlayer = OXEnum.O;
    }
  }

  @Override
  public OXEnum getWinner(List<Button> buttons) {
    LOGGER.info("Sprawdzam plansze w poszukiwaniu zwyciezcy!");
    OXEnum rows = checkRows(buttons);
    OXEnum columns = checkColumns(buttons);
    OXEnum cross = checkCross(buttons);
    if (rows != OXEnum.EMPTY) {
      return rows;
    } else if (columns != OXEnum.EMPTY) {
      return columns;
    } else if (cross != OXEnum.EMPTY) {
      return cross;
    }
    return OXEnum.EMPTY;
  }

  public OXEnum checkColumns(List<Button> buttons) {
    for (int i = 0; i <= 2; i++) {
      if (buttons.get(i).getText() == "X" && buttons.get(i + 3).getText() == "X" && buttons.get(i + 6).getText() == "X") {
        return OXEnum.X;
      }
      if (buttons.get(i).getText() == "O" && buttons.get(i + 3).getText() == "O" && buttons.get(i + 6).getText() == "O") {
        return OXEnum.O;
      }
    }

    return OXEnum.EMPTY;
  }

  public OXEnum checkRows(List<Button> buttons) {
    for (int i = 0; i <= 6; i += 3) {
      if (buttons.get(i).getText() == "X" && buttons.get(i + 1).getText() == "X" && buttons.get(i + 2).getText() == "X") {
        return OXEnum.X;
      }
      if (buttons.get(i).getText() == "O" && buttons.get(i + 1).getText() == "O" && buttons.get(i + 2).getText() == "O") {
        return OXEnum.O;
      }
    }
    return OXEnum.EMPTY;
  }

  public OXEnum checkCross(List<Button> buttons) {
    if (buttons.get(0).getText() == "X" && buttons.get(4).getText() == "X" && buttons.get(8).getText() == "X") {
      return OXEnum.X;
    }
    if (buttons.get(0).getText() == "O" && buttons.get(4).getText() == "O" && buttons.get(8).getText() == "O") {
      return OXEnum.O;
    }
    if (buttons.get(2).getText() == "X" && buttons.get(4).getText() == "X" && buttons.get(6).getText() == "X") {
      return OXEnum.X;
    }
    if (buttons.get(2).getText() == "O" && buttons.get(4).getText() == "O" && buttons.get(6).getText() == "O") {
      return OXEnum.O;
    }
    return OXEnum.EMPTY;
  }
}
