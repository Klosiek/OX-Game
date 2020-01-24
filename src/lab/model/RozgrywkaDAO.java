package lab.model;

import java.util.List;

public interface RozgrywkaDAO {
  int zapiszRozgrywke(Rozgrywka game);
  List<Rozgrywka> pobierzRozgrywki  (Integer startId, Integer rowNumber);
  void usunRozgrywki();
}
