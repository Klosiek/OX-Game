package lab.dao;

import lab.engine.OXEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lab.model.Rozgrywka;
import lab.model.RozgrywkaDAO;
import lab.data.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RozgrywkaDAOImpl implements RozgrywkaDAO {
  private static final Logger logger = LoggerFactory.getLogger(RozgrywkaDAOImpl.class);

  @Override
  public int zapiszRozgrywke(Rozgrywka game) {
    int addedRows = 0;
    String query = "INSERT INTO game"
            + "(winner, player_O, player_X, game_time)"
            + "VALUES(?, ?, ?, ?)";
    try (Connection connect = DataSource.getConnection();
         PreparedStatement preparedStmt = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
      preparedStmt.setString(1, game.getWinner().toString());
      preparedStmt.setString(2, game.getPlayerO());
      preparedStmt.setString(3, game.getPlayerX());
      preparedStmt.setObject(4, game.getGameTime());
      if ((addedRows = preparedStmt.executeUpdate()) > 0) {
        try (ResultSet keys = preparedStmt.getGeneratedKeys()) {
          if (keys.next()) {
            game.setGameId(keys.getInt(1));
          }
        }
      }
    } catch (SQLException e) {
      logger.error("Error saveGame!", e);

    }

    return addedRows;
  }

  @Override
  public List<Rozgrywka> pobierzRozgrywki(Integer startId, Integer rowNumber) {
    List<Rozgrywka> gameList = new ArrayList<>();
    String query = "SELECT * FROM game ORDER BY game_time DESC"
            + (startId != null ? " OFFSET ?" : "")
            + (rowNumber != null ? " LIMIT ?" : "");
    try (Connection connect = DataSource.getConnection();
         PreparedStatement preparedStmt = connect.prepareStatement(query);) {
      if (startId != null) {
        preparedStmt.setInt(1, startId);
      }
      if (rowNumber != null) {
        preparedStmt.setInt(startId != null ? 2 : 1, rowNumber);
        try (ResultSet rs = preparedStmt.executeQuery()) {
          while (rs.next()) {
            gameList.add(new Rozgrywka(rs.getInt("game_id"),
                    OXEnum.fromString(rs.getString("winner")),
                    rs.getString("player_O"),
                    rs.getString("player_X"),
                    rs.getTimestamp("game_time").toLocalDateTime()));
          }
        }
      }
    } catch (SQLException e) {
      logger.error("Error getGames!", e);
    }
    return gameList;
  }

  @Override
  public void usunRozgrywki() {
    String query = "TRUNCATE TABLE game;";
    try (Connection connect = DataSource.getConnection();
         PreparedStatement preparedStmt = connect.prepareStatement(query)) {
      preparedStmt.execute();
    } catch (SQLException e) {
      logger.error("Error deleteGames!", e);
    }
  }
}
