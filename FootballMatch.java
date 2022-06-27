import processing.core.*;
import processing.data.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

public class FootballMatch {
  private PApplet app;
  private int number;
  private Date date;
  private Team leftTeam, rightTeam;
  private Table[] tables;
  private Table matches;
  private ArrayList<String> moments;
  private int activeMoment = 100;
  
  public JSONObject saveM() {
    JSONObject jso = new JSONObject();
    JSONArray jam = new JSONArray();
    for (int i=0; i < moments.size(); i++) {
      jam.setString(i, moments.get(i));
    }
    jso.setInt("number", number)  
        .setString("date", String.valueOf(date.getTime()))
        .setJSONObject("leftTeam", leftTeam.saveM())
        .setJSONObject("rightTeam", rightTeam.saveM())
        .setJSONArray("moments", jam);
    return jso;
  }

  public static enum Role {
    A, P, Z
  }

  public FootballMatch(PApplet app, int n, int lnum, String lname, int rnum, String rname) {
    this.app = app;
    nextMatch(n, lnum, lname, rnum, rname);
  }

  public FootballMatch(PApplet app, int n, TableRow lt, TableRow rt) {
    this.app = app;
    nextMatch(n, lt, rt);
  }

  public FootballMatch(PApplet app, int n, Table[] tables, Table matches) {
    this.app = app;
    this.tables = tables;
    this.matches = matches;
    nextMatch(n);
  }
  

  //private void nextMatch(int n) {
  //  this.number = n;
  //  this.date = new Date();
  //  int lfans = (int) (Math.random()*89 + 1);
  //  int rfans = 100 - lfans;
  //  leftTeam = new Team(lfans);
  //  rightTeam = new Team(rfans);
  //}

  public void nextMatch(int n, int lnum, String lname, int rnum, String rname) {
    nextMatch(n);
    leftTeam.setTeam(lnum, lname);
    rightTeam.setTeam(rnum, rname);
  }

  public void nextMatch(int n, TableRow lt, TableRow rt) {
    leftTeam.setTeam(lt);
    rightTeam.setTeam(rt);
    nextMatch(n);
  }

  public void nextMatch(int lnum, String lname, int rnum, String rname) {
    nextMatch(number+1, lnum, lname, rnum, rname);
  }

  public void nextMatch(TableRow lt, TableRow rt) {
    nextMatch(number+1, lt, rt);
  }

  public void nextMatch(int n) {
    this.number = n;
    JSONObject jso = app.loadJSONObject("data/matches/" + n + ".json");
    if (jso != null) {
      this.date = new Date(jso.getLong("date"));

      int mn = 11;
      this.moments = new ArrayList<String>(Arrays.asList(jso.getJSONArray("moments").getStringArray()));

      rightTeam = new Team(jso.getJSONObject("rightTeam"));
      leftTeam = new Team(jso.getJSONObject("leftTeam"));

      return;
    }
    this.date = new Date();
    int mn = 11;
    this.moments = new ArrayList<String>(mn);
    for (int i=0; i < mn; i++) {
      moments.add("");
    }

    int lfans = (int) (Math.random()*89 + 1);
    int rfans = 100 - lfans;
    leftTeam = new Team(lfans);
    rightTeam = new Team(rfans);

    TableRow m = matches.getRow(number);
    int lt = m.getInt(1);
    int rt = m.getInt(2);
    int ltti = lt/100;
    int rtti = rt/100;
    Table ltt = tables[ltti];
    Table rtt = tables[rtti];
    TableRow ltr = ltt.getRow(lt%100-1);
    TableRow rtr = rtt.getRow(rt%100-1);

    leftTeam.setTeam(ltr);
    rightTeam.setTeam(rtr);
  }

  public void nextMatch(Table[] tables, Table matches) {
    this.number++;
    TableRow m = matches.getRow(number);
    int lt = m.getInt(1);
    int rt = m.getInt(2);
    int ltti = lt/100;
    int rtti = rt/100;
    Table ltt = tables[ltti];
    Table rtt = tables[rtti];
    TableRow ltr = ltt.getRow(lt%100-1);
    TableRow rtr = rtt.getRow(rt%100-1);
    nextMatch(number, ltr, rtr);
  }

  public void prevMatch() {
    if (number > 0) {
      nextMatch(number-1);
    }
  }

  public ArrayList<String> getMoments() {
    return moments;
  }

  public void setActiveMoment(int i) {
    this.activeMoment = i;
  }

  public int getActiveMoment() {
    return activeMoment;
  }

  public void addToActiveMoment(char c) {
    moments.set(activeMoment, moments.get(activeMoment) + c);
  }

  public void backspaceToActiveMoment() {
    if (moments.get(activeMoment).length() > 0) {
      moments.set(activeMoment, moments.get(activeMoment).substring(0, moments.get(activeMoment).length()-1));
    }
  }

  public int getNum() {
    return number;
  }

  public Date getDate() {
    return date;
  }

  public String getFormattedDate() {
    return (new SimpleDateFormat("dd.MM.yy")).format(date);
  }

  public Team getLeft() {
    return this.leftTeam;
  }

  public Team getRight() {
    return this.rightTeam;
  }

  public String score() {
    return getLeft().getScore() + " : " + getRight().getScore();
  }

  public int leftScoreAdd(int g) {
    return getLeft().scoreAdd(g);
  }

  public int rightScoreAdd(int g) {
    return getRight().scoreAdd(g);
  }

  public int leftScoreAdd() {
    return leftScoreAdd(1);
  }

  public int rightScoreAdd() {
    return rightScoreAdd(1);
  }


  static class Team {
    class Player {
      int num;
      Role role;
      public Player(int n, Role r) {
        num = n;
        role = r;
      }

      public Player(JSONObject jso) {
        num = jso.getInt("num");
        role = Role.valueOf(jso.getString("role"));
      }

      public JSONObject saveM() {
        return new JSONObject().setInt("num", num).setString("role", role.toString());
      }
    }

    // Team number score and fans (number of thousands)
    private int number, score=0, fans;
    private String name;
    private ArrayList<Player> players = new ArrayList(10);

    public JSONObject saveM() {
      JSONObject jso = new JSONObject();
      JSONArray jap = new JSONArray();
      for (int i=0; i < players.size(); i++) {
        jap.setJSONObject(i, players.get(i).saveM());
      }
      jso.setInt("number", number)
          .setInt("score", score)
          .setInt("fans", fans)
          .setString("name", name)
          .setJSONArray("players", jap);
      return jso;
    }

    public Team(int num, String name, int fans) {
      setTeam(num, name, fans);
    }

    public Team(TableRow tr, int fans) {
      setTeam(tr.getInt(0), tr.getString(1), fans);
    }

    public Team(TableRow tr) {
      setTeam(tr, fans);
    }

    public Team(int fans) {
      setTeam(this.number, this.name, fans);
    }

    public Team(int num, String name) {
      setTeam(num, name, this.fans);
    }

    public Team(JSONObject jso) {
      number = jso.getInt("number");
      name = jso.getString("name");
      fans = jso.getInt("fans");
      score = jso.getInt("score");
      JSONArray jsa = jso.getJSONArray("players");
      for (int i = 0; i < 11; i++) {
        players.add(new Player(jsa.getJSONObject(i)));
      }
    }

    public void setTeam(int num, String name, int fans) {
      this.number = num;
      this.name = name;
      this.fans = fans;
      ArrayList<Integer> pnum = new ArrayList<Integer>(98);
      for (int i=0; i < 98; i++) {
        pnum.add(i+2);
      }
      Collections.shuffle(pnum);
      for (int i=0; i < 10; i++) {
        players.add(new Player(pnum.get(i), (i < 4 ? Role.A : (i < 7 ? Role.P : Role.Z))));
      }
    }

    public void setTeam(TableRow tr, int fans) {
      setTeam(tr.getInt(0), tr.getString(1), fans);
    }

    public void setTeam(int num, String name) {
      setTeam(num, name, this.fans);
    }

    public void setTeam(TableRow tr) {
      setTeam(tr.getInt(0), tr.getString(1));
    }

    public int getNum() {
      return number;
    }

    public String getName() {
      return name;
    }

    public int getScore() {
      return score;
    }

    public int getFans() {
      return fans;
    }

    public ArrayList<Player> getPlayers() {
      return players;
    }

    public Player getPlayer(int i) {
      return players.get(i);
    }

    public String formatPlayerNum(int i) {
      return String.valueOf(players.get(i).num);
    }

    public String formatPlayerRole(int i) {
      switch (players.get(i).role) {
      case A:
        return "А";

      case P:
        return "П";

      case Z:
        return "З";
      }
      return "Err";
    }

    public void setPlayerRole(int i, Role r) {
      players.get(i).role = r;
    }

    public void switchPlayerRole(int i) {
      switch (players.get(i).role) {
      case A:
        players.get(i).role = Role.P;
        break;
      case P:
        players.get(i).role = Role.Z;
        break;
      case Z:
        players.get(i).role = Role.A;
        break;
      }
    }

    public void setFans(int f) {
      this.fans = f;
    }

    public int scoreAdd(int g) {
      return score += g;
    }
  }
}
