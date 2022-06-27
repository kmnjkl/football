public interface LkjPalette {
  Integer getColor(Integer i);
  Integer getColor();
  Integer nextColor();
  Integer setColor(Integer i, Integer c);
  Integer[] addColors(Integer... c);
}
