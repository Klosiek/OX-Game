package lab.engine;

import lab.dao.RozgrywkaDAOImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lab.model.Rozgrywka;
import lab.model.RozgrywkaDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
  private static final Logger logger = LoggerFactory.getLogger(Controller.class);
  @FXML
  public Button clear;



  @FXML
  private GridPane btn_grid;

  @FXML
  private Button button_0;

  @FXML
  private Button button_1;

  @FXML
  private Button button_2;

  @FXML
  private Button button_3;

  @FXML
  private Button button_4;

  @FXML
  private Button button_5;

  @FXML
  private Button button_6;

  @FXML
  private Button button_7;

  @FXML
  private Button button_8;

  @FXML
  private TableView table;

  @FXML
  private TableColumn column0;

  @FXML
  private TableColumn column1;

  @FXML
  private TableColumn column2;

  @FXML
  private TableColumn column3;

  @FXML
  private TableColumn column4;

  @FXML
  private Button start_button;

  @FXML
  private Button finish_button;

  @FXML
  private TextField player_1;

  @FXML
  private TextField player_2;



  private OXGameImpl engine = new OXGameImpl();
  private ObservableList<Rozgrywka> rozgrywki;
  private ExecutorService wykonawca;
  private RozgrywkaDAO rozgrywkaDAO;


  public Controller() {
  }

  private void checkIfGameIsFinished(List<Button> buttons) {
    OXEnum winner = engine.getWinner(buttons);
    if (winner != OXEnum.EMPTY) {
      finishGame(buttons);
      String winnerName = winner.toString().equals("O") ? player_1.getText() : player_2.getText();
      logger.info("Gra rozegrana. Zwycięzca: " + winnerName);
      wykonawca.execute(() -> {
        logger.info("Zapisywanie danych...");
        rozgrywkaDAO.saveGame(new Rozgrywka(winner, player_1.getText(), player_2.getText(), LocalDateTime.now()));
      });
      wykonawca.execute(() -> {
        logger.info("Pobieranie danych...");
        List<Rozgrywka> rows = rozgrywkaDAO.pobierzRozgrywki(0, 4);
        if (rows != null) {
          Platform.runLater(() -> {
            rozgrywki.addAll(rows);
          });
        }
      });
    }
  }

  private void finishGame(List<Button> buttons) {
    for (Button btn : buttons
    ) {
      btn.setDisable(true);
    }
    start_button.setDisable(false);
    start_button.setText("Play again!");
    player_1.setDisable(false);
    player_2.setDisable(false);
    finish_button.setDisable(true);
    logger.info("Gra zakonczona!");
  }




  @FXML
  private void initialize() {
    rozgrywkaDAO = new RozgrywkaDAOImpl();

    wykonawca = Executors.newFixedThreadPool(1);

    rozgrywki = FXCollections.observableArrayList();
    wykonawca.execute(() -> {
      logger.info("Pobieranie historii rozgrywek z bazy danych ...");
      List<Rozgrywka> rows = rozgrywkaDAO.pobierzRozgrywki(0, 4);
      if (rows != null) {
        Platform.runLater(() -> {
          rozgrywki.addAll(rows);
        });
      }
    });
    table.setItems(rozgrywki);

    List<Button> buttons = new ArrayList<>(Arrays.asList(button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8));
    column0.setCellValueFactory(new PropertyValueFactory<Rozgrywka, Integer>("gameId"));
    column1.setCellValueFactory(new PropertyValueFactory<Rozgrywka, String>("winner"));
    column2.setCellValueFactory(new PropertyValueFactory<Rozgrywka, String>("playerO"));
    column3.setCellValueFactory(new PropertyValueFactory<Rozgrywka, String>("playerX"));
    column4.setCellValueFactory(new PropertyValueFactory<Rozgrywka, String>("gameTime"));
    finish_button.setDisable(true);

    for (Button btn : buttons
    ) {
      btn.setText(OXEnum.EMPTY.toString());
      btn.setOnAction((event) -> {
        btn.setDisable(true);
        engine.setField(btn);
        checkIfGameIsFinished(buttons);
        logger.info("Ruch wykonany!");
      });
    }

    clear.setOnAction((event) -> {
      rozgrywkaDAO.deleteGames();
    });

    start_button.setOnAction((event) -> {
      start_button.setDisable(true);
      finish_button.setDisable(false);
      player_1.setDisable(true);
      player_2.setDisable(true);
      btn_grid.setDisable(false);
      for (Button btn : buttons
      ) {
        btn.setDisable(false);
        btn.setText(OXEnum.EMPTY.toString());
      }
      logger.info("Gra rozpoczeta!");
    });

    finish_button.setOnAction((event) -> {
      finishGame(buttons);
    });
  }

}
