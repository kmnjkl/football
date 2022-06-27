import java.util.UUID;
//import uibooster.*;

//UiBooster booster;
String tableName = "football";

LkjDrawStandard ld;

Table[] tables;
Table matches;

FootballMatch match;

void setup() {
  size(1188, 840);
  ld = new LkjDrawStandard(this, #FCFCFC);
  surface.setResizable(true);
  
  //booster = new UiBooster();
  //tableName = booster.showTextInputDialog("Table name:");
  
  tables = new Table[10];
  for (int i = 0; i < 10; i++) {
    tables[i] = loadTable(tableName + " - " + ((100*i)+1) + "-" + (100*(i+1)) + ".csv");
  }
  matches = loadTable(tableName + " - matches.csv");

  //TableRow m1 = matches.getRow(0);
  //int lt = m1.getInt(1);
  //int rt = m1.getInt(2);
  //int ltti = lt/100;
  //int rtti = rt/100;
  //Table ltt = tables[ltti];
  //Table rtt = tables[rtti];
  //TableRow ltr = ltt.getRow(lt%100-1);
  //TableRow rtr = rtt.getRow(rt%100-1);
  //match = new FootballMatch(0, ltr, rtr);
  //match.nextMatch(12, "12", 345, "345");
  //match.leftGoal();
  //booster.showInfoDialog(match.getLeft().getNum() + match.getLeft().getName() + " - " + match.getRight().getNum() + match.getRight().getName());
  
  match = new FootballMatch(this, 0, tables, matches);
}

final int Y_SPACE = 45;
final int Y_START = 50;

final int X_START = 40;

final int X_OFFSET = -150;

void draw() {
  textSize(28);
  
  rectMode(CORNER);
  // Draw a vertical line in the center of the window
  ld.rect(ld.NO_STROKE, #000000, 593 + X_OFFSET, 0, 2, 5);
  ld.rect(ld.NO_STROKE, #000000, 593 + X_OFFSET, 50, 2, 340);
  ld.rect(ld.NO_STROKE, #000000, 593 + X_OFFSET, 450, 2, 390);
  
  // Match number and date
  // Rectangle
  rectMode(CENTER);
  ld.rect(#000000, ld.NO_FILL, 594 + X_OFFSET, 22.5 + 5, 200, 45, 7);
  // Number
  textAlign(CENTER, CENTER);
  ld.text(String.valueOf(match.getNum()+1) + " | " + match.getFormattedDate(), 594 + X_OFFSET, 20 + 5, 200, 40);
  
  // Score
  strokeWeight(2);
  rectMode(CENTER);
  // Draw a rectangle for the score in the center
  ld.rect(#000000, ld.NO_FILL, 594 + X_OFFSET, 420, 90, 60, 5);
  rectMode(CORNER);
  // Left +
  if (mouseX > 594 + X_OFFSET - 45 && mouseX < 594 + X_OFFSET
      && mouseY > 420 - 30 && mouseY < 420) {
      ld.rect(ld.NO_STROKE, #E0E0E0, 594 + X_OFFSET - 45, 420 - 30, 45, 30);
  } 
  // Left -
  if (mouseX > 594 + X_OFFSET - 45 && mouseX < 594 + X_OFFSET
      && mouseY > 420 && mouseY < 420 + 30) {
      ld.rect(ld.NO_STROKE, #E0E0E0, 594 + X_OFFSET - 45, 420, 45, 30);
  } 
  // Right +
  if (mouseX > 594 + X_OFFSET && mouseX < 594 + X_OFFSET + 45
      && mouseY > 420 - 30 && mouseY < 420) {
      ld.rect(ld.NO_STROKE, #E0E0E0, 594 + X_OFFSET, 420 - 30, 45, 30);
  } 
  // Right -
  if (mouseX > 594 + X_OFFSET && mouseX < 594 + X_OFFSET + 45
      && mouseY > 420 && mouseY < 420 + 30) {
      ld.rect(ld.NO_STROKE, #E0E0E0, 594 + X_OFFSET, 420, 45, 30);
  }
  fill(#000000);
  stroke(#000000);
  // Print the score in the rectangle
  textAlign(CENTER, CENTER);
  ld.text(match.score(), 594 + X_OFFSET, 420);
  
  
  // Print teams information
  // Left team
  // Name
  rectMode(CORNER);
  //ld.rect(X_START, Y_START, 593-2*X_START, 40);    // Supportive
  textAlign(LEFT);
  ld.text(match.getLeft().getName(), X_START, Y_START, 593-2*X_START + (X_OFFSET/2), 40);
  // Number
  ld.rect(#000000, ld.NO_FILL, X_START, Y_START+40+Y_SPACE, 80, 45, 4);
  textAlign(CENTER, CENTER);
  ld.text(color(20), String.valueOf(match.getLeft().getNum()), X_START, Y_START+40+Y_SPACE, 80, 40);
  noFill();
  
  // Right team
  // Name
  //ld.rect(-(593-X_START), Y_START, 593-2*X_START, 40);   // Supportive
  textAlign(RIGHT);
  ld.text(match.getRight().getName(), -(593-X_START) + X_OFFSET, Y_START, 593-2*X_START+ (X_OFFSET/2), 40);
  // Number
  ld.rect(-(80+X_START) + X_OFFSET - 50, Y_START+40+Y_SPACE, 80, 45, 4);
  textAlign(CENTER, CENTER);
  ld.text(color(20), String.valueOf(match.getRight().getNum()), -(80+X_START) + X_OFFSET - 50, Y_START+40+Y_SPACE, 80, 40);
  noFill();
  
  // Print fans information
  // Left
  rectMode(CORNER);
  textAlign(LEFT, TOP);
  ld.text(String.valueOf(match.getLeft().getFans()) + " Т", X_START+10, Y_START+40+Y_SPACE+45+Y_SPACE);
  // Right
  textAlign(RIGHT, TOP);
  ld.text(String.valueOf(match.getRight().getFans()) + " Т", -(X_START+10) + X_OFFSET - 50, Y_START+40+Y_SPACE+45+Y_SPACE);
  
  // Print players
  // Left
  rectMode(CORNER);
  float ls = 594-40-X_START-60-100 + X_OFFSET;
  float ts = -(495+Y_START);
  float w = 100;
  float h = 45;
  ld.rect(ls, ts, 100, 495);    // Supportive
  textAlign(RIGHT, CENTER);
  ld.text("1", ls, ts, w/2, h);
  ld.text(LEFT, CENTER, #000000, "В", ls + (w/2) + 17, ts, w/2 - 17, h);
  for (int i=0; i < 10; i++) {
    if (mouseX > ld.xsp(ls-5) && mouseX < ld.xsp(ls + w + 5)
        && mouseY > ld.ysp(ts + h*(i+1) + 1) && mouseY < ld.ysp(ts + h*(i+1) + h - 1)) {
      strokeWeight(3);
      ld.rect(#FAB8B8, #EFEFEF, ls, ts + h*(i+1), w, h);
    }
    
    textAlign(RIGHT, CENTER);
    ld.text(#000000, match.getLeft().formatPlayerNum(i), ls, ts + h*(i+1), w/2, h);
    textAlign(LEFT, CENTER);
    ld.text(#000000, match.getLeft().formatPlayerRole(i), ls+(w/2)+17, ts + h*(i+1), w/2 - 17, h);
  }
  // Right
  ls = -(594-40-X_START-60) + X_OFFSET;
  ld.rect(#000000, ld.NO_FILL, ls, -(495+Y_START), 100, 495);    // Supportive
  textAlign(RIGHT, CENTER);
  ld.text("1", ls, ts, w/2, h);
  ld.text(LEFT, CENTER, #000000, "В", ls + (w/2) + 17, ts, w/2 - 17, h);
  for (int i=0; i < 10; i++) {
    if (mouseX > ld.xsp(ls-5) && mouseX < ld.xsp(ls + w + 5)
        && mouseY > ld.ysp(ts + h*(i+1) + 1) && mouseY < ld.ysp(ts + h*(i+1) + h - 1)) {
      strokeWeight(3);
      ld.rect(#FAB8B8, #EFEFEF, ls, ts + h*(i+1), w, h);
    }
    
    textAlign(RIGHT, CENTER);
    ld.text(#000000, match.getRight().formatPlayerNum(i), ls, ts + h*(i+1), w/2, h);
    textAlign(LEFT, CENTER);
    ld.text(#000000, match.getRight().formatPlayerRole(i), ls+(w/2)+17, ts + h*(i+1), w/2 - 17, h);
  }
  
  // Moments
  float ml = ls + w + 50;
  float mt = ts;
  float mh = 40;
  float mw = 420;
  rectMode(CORNER);
  ld.rect(#000000, ld.NO_FILL, ml, mt, mw + 10, mh*12);
  //ld.rect(#111111, ld.NO_FILL, ml+5, mt + 5, mw, mh);
  strokeWeight(1.5);
  textSize(23);
  for (int i=0; i < match.getMoments().size(); i++) {
    //System.out.println("11");
    int mfc = ld.NO_FILL;
    if (match.getActiveMoment() == i) mfc = #E0E0E0;
    ld.rect(#444444, mfc, ml+5, mt + 5 + i*(mh+3), mw, mh);
    ld.text(#000000, match.getMoments().get(i), ml+5+5, mt + 5 + i*(mh+3), mw, mh);
  }
  strokeWeight(3);
  textSize(28);
  
}

void mouseClicked(MouseEvent e) {
  // Score
  // Left +
  if (e.getX() > 594 + X_OFFSET - 45 && e.getX() < 594 + X_OFFSET
      && e.getY() > 420 - 30 && e.getY() < 420) {
      match.leftScoreAdd();
  } 
  // Left -
  if (e.getX() > 594 + X_OFFSET - 45 && e.getX() < 594 + X_OFFSET
      && e.getY() > 420 && e.getY() < 420 + 30) {
      match.leftScoreAdd(-1);
  } 
  // Right +
  if (e.getX() > 594 + X_OFFSET && e.getX() < 594 + X_OFFSET + 45
      && e.getY() > 420 - 30 && e.getY() < 420) {
      match.rightScoreAdd();
  } 
  // Right -
  if (e.getX() > 594 + X_OFFSET && e.getX() < 594 + X_OFFSET + 45
      && e.getY() > 420 && e.getY() < 420 + 30) {
      match.rightScoreAdd(-1);
  } 
  //ld.rect(#000000, ld.NO_FILL, 594 + X_OFFSET, 420, 90, 60, 5);
  
  // ==
  float ls = 594-40-X_START-60-100 + X_OFFSET;
  float ts = -(495+Y_START);
  float w = 100;
  float h = 45;
  
  // Left
  for (int i=0; i < 10; i++) {
    if (e.getX() > ld.xsp(ls-5) && e.getX() < ld.xsp(ls + w + 5)
        && e.getY() > ld.ysp(ts + h*(i+1) + 1) && e.getY() < ld.ysp(ts + h*(i+1) + h - 1)) {
      strokeWeight(3);
      match.getLeft().switchPlayerRole(i);
      strokeWeight(1);
    }
  }
  
  // Right
  ls = -(594-40-X_START-60) + X_OFFSET;
  for (int i=0; i < 10; i++) {
    if (e.getX() > ld.xsp(ls-5) && e.getX() < ld.xsp(ls + w + 5)
        && e.getY() > ld.ysp(ts + h*(i+1) + 1) && e.getY() < ld.ysp(ts + h*(i+1) + h - 1)) {
      strokeWeight(3);
      ld.rect(#FAB8B8, #EFEFEF, ls, ts + h*(i+1), w, h);
      match.getRight().switchPlayerRole(i);
      strokeWeight(1);
    }
  }
  
  // Moments
  float ml = ls + w + 50;
  float mt = ts;
  float mh = 40+3;
  float mw = 420;
  //System.out.println(e.getX() + " " + e.getY() + " " + ml + " " + mt + " " + mw + " " + mh);
  for (int i=0; i < match.getMoments().size(); i++) {
    if (e.getX() > ld.xsp(ml) && e.getX() < ld.xsp(ml+mw)
        && e.getY() > ld.ysp(mt+i*mh) && e.getY() < ld.ysp(mt+(i+1)*mh)) {
      match.setActiveMoment(i);
      //System.out.println(match.getActiveMoment());
    }
  }
}

void keyPressed() {
  if (match.getActiveMoment() < match.getMoments().size()) {
    if (key == BACKSPACE) {
      match.backspaceToActiveMoment();
    } else if (keyCode != DOWN) {
      match.addToActiveMoment(key);
    }
  }
  
  if (key == CODED && keyCode == RIGHT) {
    match.nextMatch(tables, matches);
  }
  if (key == CODED && keyCode == LEFT) {
    match.prevMatch();
  }
  
  if (keyCode == DOWN) {
    println("down");
    saveJSONObject(match.saveM(), "data/matches/" + match.getNum() + ".json");
    print("saved");
  }
  
  //if (keyCode == UP) {
  //  println("UP: "+loadJSONObject("data/matches/1.json"));
  //}

}
