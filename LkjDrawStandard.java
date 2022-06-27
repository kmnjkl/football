import processing.core.*; //<>//
import java.util.HashMap;

class LkjDrawStandard extends LkjDraw {
  private HashMap<String, LkjPalette> palettes;
  
  public LkjDrawStandard(PApplet applet, int bColor, LkjPalette p) {
    this(applet, bColor);
  }

  public LkjDrawStandard(PApplet applet, PImage bImage, LkjPalette p) {
    this(applet, bImage);
  }
  
  public LkjDrawStandard(PApplet applet, int bColor) {
    super(applet, bColor);
  }

  public LkjDrawStandard(PApplet applet, PImage bImage) {
    super(applet, bImage);
  }

  public static int NO_STROKE = 1;
  public static int NO_FILL = 2;
  public static int NO_CHANGE = 3;

  public void setColors(int stroke, int fill) {
    if (stroke == NO_STROKE) {
      applet.noStroke();
    } else if (stroke <= 0) {
      applet.stroke(stroke);
    }
    if (fill == NO_FILL) {
      applet.noFill();
    } else if (fill <= 0) {
      applet.fill(fill);
    }
  }

  public void setStroke(int stroke) {
    if (stroke == NO_STROKE) {
      applet.noStroke();
    } else if (stroke <= 0) {
      applet.stroke(stroke);
    }
  }

  public void setFill(int fill) {
    if (fill == NO_FILL) {
      applet.noFill();
    } else if (fill <= 0) {
      applet.fill(fill);
    }
  }


  //  /=====  ARC  =====\
  /**
   * Draws an arc to the screen. 
   * Arcs are drawn along the outer edge of an ellipse defined by the a, b, c, and d parameters.
   * The origin of the arc's ellipse may be changed with the ellipseMode() function. 
   * Use the start and stop parameters to specify the angles (in radians) 
   * at which to draw the arc. The start/stop values must be in clockwise order.
   *
   * There are three ways to draw an arc; 
   * the rendering technique used is defined by the optional seventh parameter. 
   * The three options, depicted in the above examples, are PIE, OPEN, and CHORD. 
   * The default mode is the OPEN stroke with a PIE fill.
   */
  public void larc(float x, float y, float w, float h, float start, float stop, int mode) {
    applet.arc(x, y, w, h, start, stop, mode);
  }
  public void larc(float x, float y, float w, float h, float start, float stop) {
    applet.arc(x, y, w, h, start, stop);
  }

  public void arc(float x, float y, float w, float h, float start, float stop, int mode) {
    larc(xsp(x), ysp(y), sp(w), sp(h), start, stop, mode);
  }
  public void arc(String x, String y, String w, String h, float start, float stop, int mode) {
    larc(getSize(x), getSize(y), getSize(w), getSize(h), start, stop, mode);
  }
  public void arc(float x, float y, float w, float h, float start, float stop) {
    larc(xsp(x), ysp(y), sp(w), sp(h), start, stop);
  }
  public void arc(String x, String y, String w, String h, float start, float stop) {
    larc(getSize(x), getSize(y), getSize(w), getSize(h), start, stop);
  }

  public void arc(int stroke, int fill, float x, float y, float w, float h, float start, float stop, int mode) {
    setColors(stroke, fill);
    larc(xsp(x), ysp(y), sp(w), sp(h), start, stop, mode);
  }
  public void arc(int stroke, int fill, String x, String y, String w, String h, float start, float stop, int mode) {
    setColors(stroke, fill);
    applet.fill(fill);
    larc(getSize(x), getSize(y), getSize(w), getSize(h), start, stop, mode);
  }
  public void arc(int stroke, int fill, float x, float y, float w, float h, float start, float stop) {
    setColors(stroke, fill);
    larc(xsp(x), ysp(y), sp(w), sp(h), start, stop);
  }
  public void arc(int stroke, int fill, String x, String y, String w, String h, float start, float stop) {
    setColors(stroke, fill);
    larc(getSize(x), getSize(y), getSize(w), getSize(h), start, stop);
  }
  //  \=====  ARC  =====/


  //  /===== CIRCLE =====\
  /**
   * Draws a circle to the screen. By default, the first two parameters set the location of the center, and the third sets the shape's width and height. The origin may be changed with the ellipseMode() function.
   */
  public void lcircle(float x, float y, float extent) {
    applet.circle(x, y, extent);
  }

  public void circle(float x, float y, float extent) {
    lcircle(xsp(x), ysp(y), sp(extent));
  }
  public void circle(String x, String y, String extent) {
    lcircle(getSize(x), getSize(y), getSize(extent));
  }

  public void circle(int stroke, int fill, float x, float y, float extent) {
    setColors(stroke, fill);
    lcircle(xsp(x), ysp(y), sp(extent));
  }
  public void circle(int stroke, int fill, String x, String y, String extent) {
    setColors(stroke, fill);
    lcircle(getSize(x), getSize(y), getSize(extent));
  }
  //  \===== CIRCLE =====/


  //  /===== ELLIPSE =====\
  /**
   * Draws an ellipse (oval) to the screen. 
   * An ellipse with equal width and height is a circle. 
   * By default, the first two parameters set the location, 
   * and the third and fourth parameters set the shape's width and height. 
   * The origin may be changed with the ellipseMode() function.
   */
  public void lellipse(float x, float y, float w, float h) {
    applet.ellipse(x, y, w, h);
  }

  public void ellipse(float x, float y, float w, float h) {
    lellipse(xsp(x), ysp(y), sp(w), sp(h));
  }
  public void ellipse(String x, String y, String w, String h) {
    lellipse(getSize(x), getSize(y), getSize(w), getSize(h));
  }

  public void ellipse(int stroke, int fill, float x, float y, float w, float h) {
    setColors(stroke, fill);
    lellipse(xsp(x), ysp(y), sp(w), sp(h));
  }
  public void ellipse(int stroke, int fill, String x, String y, String w, String h) {
    setColors(stroke, fill);
    lellipse(getSize(x), getSize(y), getSize(w), getSize(h));
  }
  //  \===== ELLIPSE =====/


  //  /===== LINE =====\
  /**
   * Draws a line (a direct path between two points) to the screen. 
   * The version of line() with four parameters draws the line in 2D. 
   * To int a line, use the stroke() function. 
   * A line cannot be filled, therefore the fill() function will not affect the int of a line. 
   * 2D lines are drawn with a width of one pixel by default, 
   * but this can be changed with the strokeWeight() function. 
   * The version with six parameters allows the line to be placed anywhere within XYZ space. 
   * Drawing this shape in 3D with the z parameter requires the P3D parameter 
   * in combination with size() as shown in the above example.
   */
  public void lline(float x1, float y1, float x2, float y2) {
    applet.line(x1, y1, x2, y2);
  }
  public void lline(float x1, float y1, float z1, float x2, float y2, float z2) {
    applet.line(x1, y1, z1, x2, y2, z2);
  }

  public void line(float x1, float y1, float x2, float y2) {
    lline(xsp(x1), ysp(y1), xsp(x2), ysp(y2));
  }
  public void line(float x1, float y1, float z1, float x2, float y2, float z2) {
    lline(xsp(x1), ysp(y1), sp(z1), xsp(x2), ysp(y2), sp(z2));
  }
  public void line(String x1, String  y1, String  x2, String  y2) {
    lline(getSize(x1), getSize(y1), getSize(x2), getSize(y2));
  }
  public void line(String x1, String  y1, String z1, String  x2, String  y2, String z2) {
    lline(getSize(x1), getSize(y1), getSize(z1), getSize(x2), getSize(y2), getSize(z2));
  }

  public void line(int stroke, int fill, float x1, float y1, float x2, float y2) {
    setColors(stroke, fill);
    lline(xsp(x1), ysp(y1), xsp(x2), ysp(y2));
  }
  public void line(int stroke, int fill, float x1, float y1, float z1, float x2, float y2, float z2) {
    setColors(stroke, fill);
    lline(xsp(x1), ysp(y1), sp(z1), xsp(x2), ysp(y2), sp(z2));
  }
  public void line(int stroke, int fill, String x1, String  y1, String  x2, String  y2) {
    setColors(stroke, fill);
    lline(getSize(x1), getSize(y1), getSize(x2), getSize(y2));
  }
  public void line(int stroke, int fill, String x1, String  y1, String z1, String  x2, String  y2, String z2) {
    setColors(stroke, fill);
    lline(getSize(x1), getSize(y1), getSize(z1), getSize(x2), getSize(y2), getSize(z2));
  }
  //  \===== LINE =====/


  //  /===== POINT =====\
  /**
   * Draws a point, a coordinate in space at the dimension of one pixel. The first parameter is the horizontal value for the point, the second value is the vertical value for the point, and the optional third value is the depth value. Drawing this shape in 3D with the z parameter requires the P3D parameter in combination with size() as shown in the above example.
   * 
   * Use stroke() to set the int of a point().
   * 
   * Point appears round with the default strokeCap(ROUND) and square with strokeCap(PROJECT). Points are invisible with strokeCap(SQUARE) (no cap).
   * 
   * Using point() with strokeWeight(1) or smaller may draw nothing to the screen, depending on the graphics settings of the computer. Workarounds include setting the pixel using set() or drawing the point using either circle() or square().
   */
  public void lpoint(float x, float y) {
    applet.point(x, y);
  }
  public void lpoint(float x, float y, float z) {
    applet.point(x, y, z);
  }

  public void point(float x, float y) {
    lpoint(xsp(x), ysp(y));
  }
  public void point(float x, float y, float z) {
    lpoint(xsp(x), ysp(y), sp(z));
  }
  public void point(String x, String y) {
    lpoint(getSize(x), getSize(y));
  }
  public void point(String x, String y, String z) {
    lpoint(getSize(x), getSize(y), getSize(z));
  }

  public void point(int stroke, int fill, float x, float y) {
    setColors(stroke, fill);
    lpoint(xsp(x), ysp(y));
  }
  public void point(int stroke, int fill, float x, float y, float z) {
    setColors(stroke, fill);
    lpoint(xsp(x), ysp(y), sp(z));
  }
  public void point(int stroke, int fill, String x, String y) {
    setColors(stroke, fill);
    lpoint(getSize(x), getSize(y));
  }
  public void point(int stroke, int fill, String x, String y, String z) {
    setColors(stroke, fill);
    lpoint(getSize(x), getSize(y), getSize(z));
  }
  //  \===== POINT =====/


  //  /===== QUAD =====\
  /**
   * A quad is a quadrilateral, a four sided polygon. It is similar to a rectangle, but the angles between its edges are not constrained to ninety degrees. The first pair of parameters (x1,y1) sets the first vertex and the subsequent pairs should proceed clockwise or counter-clockwise around the defined shape. 
   */
  public void lquad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
    applet.quad(x1, y1, x2, y2, x3, y3, x4, y4);
  }

  public void quad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
    lquad(xsp(x1), ysp(y1), xsp(x2), ysp(y2), xsp(x3), ysp(y3), xsp(x4), ysp(y4));
  }
  public void quad(String x1, String y1, String x2, String y2, String x3, String y3, String x4, String y4) {
    lquad(getSize(x1), getSize(y1), getSize(x2), getSize(y2), getSize(x3), getSize(y3), getSize(x4), getSize(y4));
  }

  public void quad(int stroke, int fill, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
    setColors(stroke, fill);
    lquad(xsp(x1), ysp(y1), xsp(x2), ysp(y2), xsp(x3), ysp(y3), xsp(x4), ysp(y4));
  }
  public void quad(int stroke, int fill, String x1, String y1, String x2, String y2, String x3, String y3, String x4, String y4) {
    setColors(stroke, fill);
    lquad(getSize(x1), getSize(y1), getSize(x2), getSize(y2), getSize(x3), getSize(y3), getSize(x4), getSize(y4));
  }
  //  \===== QUAD =====/


  //  /===== RECT =====\
  /**
   * Draws a rectangle to the screen. A rectangle is a four-sided shape with every angle at ninety degrees. By default, the first two parameters set the location of the upper-left corner, the third sets the width, and the fourth sets the height. The way these parameters are interpreted, however, may be changed with the rectMode() function.
   * 
   * To draw a rounded rectangle, add a fifth parameter, which is used as the radius value for all four corners.
   * 
   * To use a different radius value for each corner, include eight parameters. When using eight parameters, the latter four set the radius of the arc at each corner separately, starting with the top-left corner and moving clockwise around the rectangle.
   */
  public void lrect(float x, float y, float w, float h) {
    applet.rect(x, y, w, h);
  }
  public void lrect(float x, float y, float w, float h, float r) {
    applet.rect(x, y, w, h, r);
  }
  public void lrect(float x, float y, float w, float h, float tl, float tr, float br, float bl) {
    applet.rect(x, y, w, h, tl, tr, br, bl);
  }
  public void lrect(int stroke, int fill, float x, float y, float w, float h) {
    setColors(stroke, fill);
    applet.rect(x, y, w, h);
  }
  public void lrect(int stroke, int fill, float x, float y, float w, float h, float r) {
    setColors(stroke, fill);
    applet.rect(x, y, w, h, r);
  }
  public void lrect(int stroke, int fill, float x, float y, float w, float h, float tl, float tr, float br, float bl) {
    setColors(stroke, fill);
    applet.rect(x, y, w, h, tl, tr, br, bl);
  }

  public void rect(float x, float y, float w, float h) {
    lrect(xsp(x), ysp(y), sp(w), sp(h));
  }
  public void rect(float x, float y, float w, float h, float r) {
    lrect(xsp(x), ysp(y), sp(w), sp(h), sp(r));
  }
  public void rect(float x, float y, float w, float h, float tl, float tr, float br, float bl) {
    lrect(xsp(x), ysp(y), sp(w), sp(h), sp(tl), sp(tr), sp(br), sp(bl));
  }
  public void rect(String x, String y, String w, String h) {
    lrect(getSize(x), getSize(y), getSize(w), getSize(h));
  }
  public void rect(String x, String y, String w, String h, String r) {
    lrect(getSize(x), getSize(y), getSize(w), getSize(h), getSize(r));
  }
  public void rect(String x, String y, String w, String h, String tl, String tr, String br, String bl) {
    lrect(getSize(x), getSize(y), getSize(w), getSize(h), getSize(tl), getSize(tr), getSize(br), getSize(bl));
  }

  public void rect(int stroke, int fill, float x, float y, float w, float h) {
    lrect(stroke, fill, xsp(x), ysp(y), sp(w), sp(h));
  }
  public void rect(int stroke, int fill, float x, float y, float w, float h, float r) {
    lrect(stroke, fill, xsp(x), ysp(y), sp(w), sp(h), sp(r));
  }
  public void rect(int stroke, int fill, float x, float y, float w, float h, float tl, float tr, float br, float bl) {
    lrect(stroke, fill, xsp(x), ysp(y), sp(w), sp(h), sp(tl), sp(tr), sp(br), sp(bl));
  }
  public void rect(int stroke, int fill, String x, String y, String w, String h) {
    lrect(stroke, fill, getSize(x), getSize(y), getSize(w), getSize(h));
  }
  public void rect(int stroke, int fill, String x, String y, String w, String h, String r) {
    lrect(stroke, fill, getSize(x), getSize(y), getSize(w), getSize(h), getSize(r));
  }
  public void rect(int stroke, int fill, String x, String y, String w, String h, String tl, String tr, String br, String bl) {
    lrect(stroke, fill, getSize(x), getSize(y), getSize(w), getSize(h), getSize(tl), getSize(tr), getSize(br), getSize(bl));
  }
  //  \===== RECT =====/


  //  /===== SQUARE =====\
  /**
   * Draws a square to the screen. 
   * A square is a four-sided shape with every 
   * angle at ninety degrees and each side is the same length. 
   * By default, the first two parameters set the location of the upper-left corner, 
   * the third sets the width and height. 
   * The way these parameters are interpreted, however, 
   * may be changed with the rectMode() function.
   */
  public void lsquare(float x, float y, float extent) {
    applet.square(x, y, extent);
  }

  public void square(float x, float y, float extent) {
    lsquare(xsp(x), ysp(y), sp(extent));
  }
  public void square(String x, String y, String extent) {
    lsquare(getSize(x), getSize(y), getSize(extent));
  }

  public void square(int stroke, int fill, float x, float y, float extent) {
    setColors(stroke, fill);
    lsquare(xsp(x), ysp(y), sp(extent));
  }
  public void square(int stroke, int fill, String x, String y, String extent) {
    setColors(stroke, fill);
    lsquare(getSize(x), getSize(y), getSize(extent));
  }
  //  \===== SQUARE =====/


  //  /===== TRIANGLE =====\
  /**
   * A triangle is a plane created by connecting three points. The first two arguments specify the first point, the middle two arguments specify the second point, and the last two arguments specify the third point.
   */
  public void ltriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
    applet.triangle(x1, y1, x2, y2, x3, y3);
  }

  public void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
    ltriangle(xsp(x1), ysp(y1), xsp(x2), ysp(y2), xsp(x3), ysp(y3));
  }
  public void triangle(String x1, String y1, String x2, String y2, String x3, String y3) {
    ltriangle(getSize(x1), getSize(y1), getSize(x2), getSize(y2), getSize(x3), getSize(y3));
  }

  public void triangle(int stroke, int fill, float x1, float y1, float x2, float y2, float x3, float y3) {
    setColors(stroke, fill);
    ltriangle(xsp(x1), ysp(y1), xsp(x2), ysp(y2), xsp(x3), ysp(y3));
  }
  public void triangle(int stroke, int fill, String x1, String y1, String x2, String y2, String x3, String y3) {
    setColors(stroke, fill);
    ltriangle(getSize(x1), getSize(y1), getSize(x2), getSize(y2), getSize(x3), getSize(y3));
  }
  //  \===== TRIANGLE =====/


  //  /===== TEXT =====\
  public void ltext(String str, float x, float y) {
    applet.text(str, x, y);
  }
  public void ltext(int fill, String str, float x, float y) {
    setColors(NO_CHANGE, fill);
    applet.text(str, x, y);
  }
  public void ltext(String str, float x1, float y1, float x2, float y2) {
    applet.text(str, x1, y1, x2, y2);
  }
  public void ltext(int fill, String str, float x1, float y1, float x2, float y2) {
    setColors(NO_CHANGE, fill);
    applet.text(str, x1, y1, x2, y2);
  }

  // Simple text
  public void text(String str, float x, float y) {
    ltext(str, xsp(x), ysp(y));
  }
  public void text(String str, String x, String y) {
    ltext(str, getSize(x), getSize(y));
  }
  public void text(String str, float x1, float y1, float x2, float y2) {
    ltext(str, xsp(x1), ysp(y1), xsp(x2), ysp(y2));
  }
  public void text(String str, String x1, String y1, String x2, String y2) {
    ltext(str, getSize(x1), getSize(y1), getSize(x2), getSize(y2));
  }

  // Set fill for text
  public void text(int fill, String str, float x, float y) {
    ltext(fill, str, xsp(x), ysp(y));
  }
  public void text(int fill, String str, String x, String y) {
    ltext(fill, str, getSize(x), getSize(y));
  }
  public void text(int fill, String str, float x1, float y1, float x2, float y2) {
    ltext(fill, str, xsp(x1), ysp(y1), xsp(x2), ysp(y2));
  }
  public void text(int fill, String str, String x1, String y1, String x2, String y2) {
    ltext(fill, str, getSize(x1), getSize(y1), getSize(x2), getSize(y2));
  }
  
  // Set align and fill
  public void text(int alignX, int alignY, int fill, String str, float x, float y) {
    applet.textAlign(alignX, alignY);
    ltext(fill, str, xsp(x), ysp(y));
  }
  public void text(int alignX, int alignY, int fill, String str, String x, String y) {
    applet.textAlign(alignX, alignY);
    ltext(fill, str, getSize(x), getSize(y));
  }
  public void text(int alignX, int alignY, int fill, String str, float x1, float y1, float x2, float y2) {
    applet.textAlign(alignX, alignY);
    ltext(fill, str, xsp(x1), ysp(y1), xsp(x2), ysp(y2));
  }
  public void text(int alignX, int alignY, int fill, String str, String x1, String y1, String x2, String y2) {
    applet.textAlign(alignX, alignY);
    ltext(fill, str, getSize(x1), getSize(y1), getSize(x2), getSize(y2));
  }
  //  \===== TEXT =====/


  //  /===== GRID =====\
  public void lgrid(float x1, float y1, float x2, float y2, int cols, int rows, int stroke, LkjPalette palette) {
    setColors(stroke, palette.getColor());
    float cwidth = (x2-x1)/cols;
    float cheight = (y2-y1)/rows;
    //int n = 0;
    float x = x1;
    float y = y1;
    applet.rectMode(applet.CORNER);
    for (int c=0; c < cols; c++) {
      for (int r=0; r < rows; r++) {
        lrect(stroke, palette.getColor(), x, y, cwidth, cheight);
        //n++;
        //applet.textAlign(applet.CENTER, applet.CENTER);
        //ltext(applet.color(0), String.valueOf(n), x + cwidth/2, y + cheight/2);
        y += cheight;
      }
      y = y1;
      x += cwidth;
      setFill(palette.getColor());
    }
  }

  public void grid(float x1, float y1, float x2, float y2, int cols, int rows, int stroke, LkjPalette palette) {
    lgrid(xsp(x1), ysp(y1), xsp(x2), ysp(y2), cols, rows, stroke, palette);
  }
  //  \===== GRID =====/
}
