package lab.engine;

public enum OXEnum {
  O("O"),
  X("X"),
  EMPTY("");

  private String str;

  private OXEnum(String value) {
    this.str = value;
  }

  @Override
  public String toString() {
    return str;
  }

  public static OXEnum fromString(String value) {
    if (value == null || value.isEmpty()) {
      return EMPTY;
    } else if (O.str.equalsIgnoreCase(value)) {
      return O;
    } else if (X.str.equalsIgnoreCase(value)) {
      return X;
    }
    return null;
  }
}
