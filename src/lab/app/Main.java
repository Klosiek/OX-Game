package lab.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  @Override
  public void start(Stage primaryStage) throws Exception {
    logger.info("Uruchamianie programu ...");
    logger.info("Wersja aplikacji {}", "1.0");
    logger.warn("Uaktualnij aplikację. Najnowsza dostępna wersja: {}", 2.0);
    Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
    primaryStage.setTitle("OX-Game");
    primaryStage.setScene(new Scene(root, 458, 588));
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
