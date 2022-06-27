import java.util.LinkedHashMap;
import java.util.Map;

public class LkjPaletteMap implements LkjPalette {
  private LinkedHashMap<String, Integer> colors;
  private Integer currentColorI;
  
  public LkjPaletteMap(Map<String, Integer> colors) {
    this.colors = new LinkedHashMap(colors);
    this.currentColorI = 0;
  }
  
  public Integer getColor(String ck) {
    return colors.get(ck);
  }
  
  public Integer getColor(Integer i) {
    return colors.values().toArray(new Integer[0])[i];
  }
  
  public Integer getColor() {
    return colors.values().toArray(new Integer[0])[currentColorI];
  }
  
  public Integer nextColor() {
    return colors.values().toArray(new Integer[0])[shiftCurrentColor()];
  }
  
  public Integer shiftCurrentColor() {
    if (currentColorI >= colors.size()-1) {
      currentColorI = 0;
      return colors.size()-1;
    } else {
      return currentColorI++;
    }
  }
  
  public Integer setColor(Integer i, Integer c) {
    return colors.values().toArray(new Integer[0])[i] = c;
  }
  
  public Integer setColor(String k, Integer c) {
    return colors.replace(k, c);
  }
  
  public Integer[] addColors(Integer... c) {
    for (Integer i=0; i < c.length; i++) {
      String newKey = String.valueOf(c[i]);
      while (colors.containsKey(newKey)) {
        newKey += "c";
      }
      colors.put(newKey, c[i]);
    }
    return c;
  }
  
  public Map<? extends String,? extends Integer> addColors(Map<? extends String,? extends Integer> m) {
    colors.putAll(m);
    return m;
  }
}
