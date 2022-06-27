import processing.core.*;
import static processing.core.PApplet.sqrt;
import java.util.regex.*;

public class LkjDraw {
  protected final PApplet applet;
  /**
   * Window width on the start. It initializes in the constructor.
   * @see #LkjDraw(PApplet)
   * */
  protected final float startWidth;
  /**
   * Window height on the start. It initializes in the constructor.
   * @see #LkjDraw(PApplet)
   * */
  protected final float startHeight;
  /**
   * Coefficient which is based on ratio of the current size to the start size.
   * @see #preDraw()
   * @see #sp(float)
   * */
  protected float currentSizeScale;
  
  public float startWidth() {
    return startWidth;
  }
  
  public float startHeight() {
    return startHeight;
  }

  protected int backColor;
  protected PImage backImage;
  public int BACKGROUND_COLOR_NULL = 1000;
  
  public void setBackColor(int bColor) {
    this.backColor = bColor;
    this.backImage = null;
  }
  
  public void setBackImage(PImage bImage) {
    this.backImage = bImage;
    this.backColor = BACKGROUND_COLOR_NULL;
  }
  
  public int getBackColor() {
    return backColor;
  }
  
  public int getColor(int maxh, int maxs, int maxb, int h, int s, int b) {
    applet.colorMode(applet.HSB, maxh, maxs, maxb);
    if (h <= 0) {
      h = (int) (applet.hue(backColor) - h)%maxb;
    }
    if (s <= 0) {
      s = (int) (applet.saturation(backColor) - s)%maxs;
    }
    if (b <= 0) {
      b = (int) (applet.brightness(backColor) - b)%maxb;
    }
    return applet.color(h, s, b);
  }
  
  public int getColor(int h, int s, int b) {
    return getColor(360, 100, 100, h, s, b);
  }
  
  public int getColorH(int maxh, int h) {
    return getColor(maxh, 100, 100, h, 0, 0);
  }
  
  public int getColorH(int h) {
    return getColorH(360, h);
  }
  
  public int getColorS(int maxs, int s) {
    return getColor(360, maxs, 100, 0, s, 0);
  }
  
  public int getColorS(int s) {
    return getColorS(100, s);
  }
  
  public int getColorB(int maxb, int b) {
    return getColor(360, 100, maxb, 0, 0, b);
  }
  
  public int getColorB(int b) {
    return getColorB(100, b);
  }

  private LkjDraw(PApplet applet) {
    this.applet = applet;
    startWidth = applet.width;
    startHeight = applet.height;
    currentSizeScale = 1f;
    applet.registerMethod("pre", this);
  }

  public LkjDraw(PApplet applet, int backColor) {
    this(applet);
    this.backColor = backColor;
  }
  
  public LkjDraw(PApplet applet, PImage backImage) {
    this(applet);
    this.backImage = backImage;
  }

  /**
   * Method that's called just after beginDraw(), meaning that it can affect drawing. <br>
   * <a href="https://github.com/processing/processing/wiki/Library-Basics">https://github.com/processing/processing/wiki/Library-Basics</a>.
   * */
  public void pre() {
    preDraw();
    if (backColor != BACKGROUND_COLOR_NULL) {
      applet.background(backColor);
    } else if (backImage != null) {
      applet.background(backImage);
    }
  }

  /**
   * Do some actions before drawing a new frame.
   * */
  void preDraw() {
    // Set size scale as harmonic mean of width and height scales
    currentSizeScale = harmonicMean(applet.width/startWidth, applet.height/startHeight);
  }

  public float rootMeanSquare(float x1, float x2) {
    return sqrt((x1*x1 + x2*x2)/2);
  }

  public float harmonicMean(float x1, float x2) {
    return 2/((1/x1) + (1/x2));
  }

  /**
   * Function to calculate width (units of pixels) from percents.
   * */
  public float xpp(float percent) {
    // print("xpp(" + percent + "): ");
    percent = percent * (applet.width / 100f);
    // println(percent + "; " + ((percent >= 0) ? percent : (width + percent)) + ";\n");
    return (percent >= 0) ? percent : (applet.width + percent);
  }

  /**
   * Function to calculate height (units of pixels) from percents.
   * */
  public float ypp(float percent) {
    // print("ypp(" + percent + "): ");
    percent = percent * (applet.height / 100f);
    // println(percent + "; " + ((percent >= 0) ? percent : (height + percent)) + ";\n");
    return (percent >= 0) ? percent : (applet.height + percent);
  }

  /**
   * Calculate width (units of pixels) based on sp-size.
   * @see #sp(float)
   * */
  public float xsp(float sp) {
    sp = sp(sp);
    return (sp >= 0) ? sp : applet.width + sp;
  }

  /**
   * Calculate height (units of pixels) based on sp-size.
   * @see #sp(float)
   * */
  public float ysp(float sp) {
    sp = sp(sp);
    return (sp >= 0) ? sp : applet.height + sp;
  }

  /**
   * Sp-size.
   * Calculate size (units of pixels) based on {@link #currentSizeScale}.
   * @return units of pixels calculated from the sp-size and currentSizeScale.
   * */
  public float sp(float sp) {
    return sp * currentSizeScale;
  }

  /**
   * Sp-size.
   * Calculate size (units of pixels) based on {@link #currentSizeScale}.
   * @param minSize if the size based on currentSizeScale less then minSize, function returns minSize.
   * @return units of pixels calculated from the sp-size and currentSizeScale <b>or</b> minSize if calculated value less then minSize.
   * */
  public float sp(float sp, float minSize) {
    float size = sp * currentSizeScale;
    return Math.max(size, minSize);
  }
  
  public final String SCALE_SIZE = "sp";
  public final String SCALE_WIDTH = "xsp";
  public final String SCALE_HEIGHT = "ysp";
  public final String PERCENT_WIDTH = "xpp";
  public final String PERCENT_HEIGHT = "ypp";
  public final String PIXEL_SIZE = "px";

  public float getSize(String s) {
    Pattern pattern = Pattern.compile("("+SCALE_WIDTH+"|"+SCALE_HEIGHT+"|"+PIXEL_SIZE+"|"+SCALE_SIZE+"|"+PERCENT_WIDTH+"|"+PERCENT_HEIGHT+"|)$");
    Matcher matcher = pattern.matcher(s);
    if (matcher.find()) {
      String u = s.substring(matcher.start());
      float size = Float.parseFloat(s.substring(0, matcher.start()));
      if (u.equals(PIXEL_SIZE)) {
        return size;
      } else if (u.equals(PERCENT_WIDTH)) {
        return xpp(size);
      } else if (u.equals(PERCENT_HEIGHT)) {
        return ypp(size);
      } else if (u.equals(SCALE_SIZE)) {
        return sp(size);
      } else if (u.equals(SCALE_WIDTH)) {
        return xsp(size);
      } else if (u.equals(SCALE_HEIGHT)) {
        return ysp(size);
      }
    }
    return 0f;
  }
}
