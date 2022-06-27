import java.util.ArrayList;
import java.util.Arrays;

public class LkjPaletteList implements LkjPalette {
  private ArrayList<Integer> colors;
  private Integer currentColor;

  public LkjPaletteList(Integer... c) {
    this.colors = new ArrayList(Arrays.asList(c));
    this.currentColor = 0;
  }

  public Integer setColor(Integer i, Integer c) {
    return colors.set(i, c);
  }

  public ArrayList getColors() {
    return colors;
  }

  public Integer nextColor() {
    return colors.get(shiftCurrentColor());
  }

  public Integer getColor(Integer i) {
    return colors.get(i);
  }

  public Integer getColor() {
    return getColor(currentColor);
  }

  public Integer shiftCurrentColor() {
    if (currentColor >= colors.size()-1) {
      currentColor = 0;
      return colors.size()-1;
    } else {
      return currentColor++;
    }
  }

  public Integer[] addColors(Integer... c) {
    return c;
  }
}
