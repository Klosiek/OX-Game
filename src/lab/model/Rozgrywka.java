package lab.model;

import lab.engine.OXEnum;

import java.time.LocalDateTime;

public class Rozgrywka {
  private Integer gameId;
  private OXEnum winner;
  private String playerX;
  private String playerO;
  private LocalDateTime gameTime;

  public Rozgrywka() {
  }

  public Rozgrywka(Integer gameId, OXEnum winner, String playerX, String playerO, LocalDateTime gameTime) {
    this.gameId = gameId;
    this.winner = winner;
    this.playerX = playerX;
    this.playerO = playerO;
    this.gameTime = gameTime;
  }

  public Rozgrywka(OXEnum winner, String playerX, String playerO, LocalDateTime gameTime) {
    this.winner = winner;
    this.playerX = playerX;
    this.playerO = playerO;
    this.gameTime = gameTime;
  }

  public Integer getGameId() {
    return gameId;
  }

  public void setGameId(Integer gameId) {
    this.gameId = gameId;
  }

  public OXEnum getWinner() {
    return winner;
  }

  public void setWinner(OXEnum winner) {
    this.winner = winner;
  }

  public String getPlayerX() {
    return playerX;
  }

  public void setPlayerX(String playerX) {
    this.playerX = playerX;
  }

  public String getPlayerO() {
    return playerO;
  }

  public void setPlayerO(String playerO) {
    this.playerO = playerO;
  }

  public LocalDateTime getGameTime() {
    return gameTime;
  }

  public void setGameTime(LocalDateTime gameTime) {
    this.gameTime = gameTime;
  }
}
