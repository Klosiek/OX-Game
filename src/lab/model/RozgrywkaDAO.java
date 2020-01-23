package lab.model;

import java.util.List;

public interface GameDAO {
  int saveGame(Game game);
  List<Game> getGames(Integer startId, Integer rowNumber);
  void deleteGames();
}
