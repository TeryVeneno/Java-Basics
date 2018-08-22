import java.util.*;
import utilities.*;
import machine_learning.Brain;
public class Battle {
  static Formatting format = new Formatting(".00", "#.");
  static Ran ran = new Ran();
  static Converter convert = new Converter(8);

  public static boolean speed_check(int ag, int a_ag) {
    boolean won;
    String s_vic = "";
    double max;
    boolean determine = false;
    if (ag >= a_ag) {
      max = ag + a_ag;
      won = ran.percent_chance(format.form((ag / max)), format.form((a_ag / max)));
      if (won == false) {
        s_vic = "AI attacks first.";
        determine = false;
      } else {
        s_vic = "Player attacks first.";
        determine = true;
      }
    } else if (a_ag >= ag) {
      max = ag + a_ag;
      won = ran.percent_chance(format.form((a_ag / max)), format.form((ag / max)));
      if (won == true) {
        s_vic = "AI attacks first.";
        determine = false;
      } else {
        s_vic = "Player attacks first.";
        determine = true;
      }
    }
    System.out.println(s_vic);
    return determine;
  }

  public static boolean speed_check2(int ag, int a_ag) {
    boolean won;
    double max;
    boolean determine = false;
    if (ag >= a_ag) {
      max = ag + a_ag;
      won = ran.percent_chance(format.form((ag / max)), format.form((a_ag / max)));
      if (won == false) {
        determine = false;
      } else {
        determine = true;
      }
    } else if (a_ag >= ag) {
      max = ag + a_ag;
      won = ran.percent_chance(format.form((a_ag / max)), format.form((ag / max)));
      if (won == true) {
        determine = false;
      } else {
        determine = true;
      }
    }
    return determine;
  }

  public static int attack2 (int h, int a, int d, int a_h, int a_a, int a_d, boolean determine) {
    double maxx;
    double s_ret;
    if (determine == true) {
      maxx = a_d * 0.53;
      if (a - maxx < 0) {
        a_h -= 0;
        s_ret = 0;
      } else {
        a_h -= a - maxx;
        s_ret = a - maxx;
      }
      return a_h;
    } else if (determine == false) {
      maxx = d * 0.53;
      if (a_a - maxx < 0) {
        h -= 0;
        s_ret = 0;
      } else {
        h -= a_a - maxx;
        s_ret = a_a - maxx;
      }
      return h;
    }
    return 1;
  }

  public static int attack (int h, int a, int d, int a_h, int a_a, int a_d, boolean determine) {
    double maxx;
    double s_ret;
    if (determine == true) {
      maxx = a_d * 0.53;
      if (a - maxx < 0) {
        a_h -= 0;
        s_ret = 0;
      } else {
        a_h -= a - maxx;
        s_ret = a - maxx;
      }
      System.out.println("\nPlayer attacks. Deals " + format.form(s_ret, 1) + " damage.");
      System.out.println("\nAI health = " + a_h + ".");
      return a_h;
    } else if (determine == false) {
      maxx = d * 0.53;
      if (a_a - maxx < 0) {
        h -= 0;
        s_ret = 0;
      } else {
        h -= a_a - maxx;
        s_ret = a_a - maxx;
      }
      System.out.println("\nAI attacks. Deals " + format.form(s_ret, 1) + " damage.");
      System.out.println("\nPlayer health = " + h + ".");
      return h;
    }
    return 1;
  }

  public static void main(String[] args) {
    Ran ran = new Ran();
    Scanner input = new Scanner(System.in);
    int rounds = 1;
    int health = 0;
    int h_holder;
    int attack = 0;
    int a_holder;
    int defense = 0;
    int d_holder;
    int agility = 0;
    int ag_holder;
    int ai_health = 0;
    int ai_attack = 0;
    int ai_defense = 0;
    int ai_agility = 0;
    int ai_health2 = 0;
    int ai_attack2 = 0;
    int ai_defense2 = 0;
    int ai_agility2 = 0;
    int start_health;
    int aistart_health;
    int aistart_health2;
    boolean determine;
    boolean victory = false;
    boolean victory_ai = false;
    boolean victory_ai2 = false;
    String choice;
    int cycles;
    double[] ai_stats = new double[4];
    double[] ai_stats2 = new double[32];
    double[] inputs = new double[1];
    double[] inputs2 = new double[8];
    double[] stats = new double[4];
    String[] stats2 = new String[4];
    inputs[0] = .100;
    inputs2[0] = 1;
    inputs2[1] = 0;
    inputs2[2] = 0;
    inputs2[3] = 1;
    inputs2[4] = 1;
    inputs2[5] = 0;
    inputs2[6] = 0;
    inputs2[7] = 0;
    double[] desired = new double[4];
    double[] desired2 = new double[32];
    double limit = 1;
    double limit2 = 100;
    double p_limit = 100;
    double ai_limit = 100;
    int ai_limit2 = 100;
    double holder;
    String s_holder;
    for (int d = 0; d < desired.length; d++) {
      holder = ran.d_ran(limit, 0);
      holder = format.form(holder);
      if (holder < 1) {
        desired[d] = holder;
      } else {
        desired[d] = 1;
      }
      limit -= desired[d];
    }
    for (int d = 1; d < 5; d++) {
      limit = 1;
      holder = ran.d_ran(limit, 0);
      holder = format.form(holder);
      holder *= 100;
      s_holder = convert.convert_db((int)holder);
      if (holder < 100) {
        if (d == 1) {
          for (int de = 0; de < 8; de++) {
            desired2[de] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
          }
        }
        if (d == 2) {
          for (int de = 0; de < 8; de++) {
            desired2[de + 8] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
          }
        }
        if (d == 3) {
          for (int de = 0; de < 8; de++) {
            desired2[de + 16] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
          }
        }
        if (d == 4) {
          for (int de = 0; de < 8; de++) {
            desired2[de + 24] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
          }
        }
        limit2 -= holder;
      } else {
          s_holder = convert.convert_db(100);
          if (holder < 100) {
            if (d == 1) {
              for (int de = 0; de < 8; de++) {
                desired2[de] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
              }
            }
            if (d == 2)
              for (int de = 0; de < 8; de++) {
                desired2[de + 8] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
              }
            }
            if (d == 3) {
              for (int de = 0; de < 8; de++) {
                desired2[de + 16] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
              }
            }
            if (d == 4) {
              for (int de = 0; de < 8; de++) {
                desired2[de + 24] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(de)));
              }
            }
        limit2 -= 100;
      }
    }
    Brain AI_1 = new Brain(5, 4, 1, 0.3, inputs, desired);
    Brain AI_2 = new Brain(5, 32, 0, 0.3, inputs2, desired2);
    System.out.println("Welcome to Battle Design.\n");
    System.out.println("To play first input the stats for your creature. There are only 4 stats: Health, Attack, Defense, and Agility.");
    System.out.println("You get 100 stat points to distribute across them all.\n");
    while (true) {
      System.out.print("\nWhich gamemode would you like to play: PvAi, PvP, or AivAi: ");
      choice = input.nextLine();
      if (choice.equals("PvP") || choice.equals("pvp")) {

      } else if (choice.equals("AivAi") || choice.equals("AIvAI") || choice.equals("aivai")) {
          System.out.print("This gamemode is purely to improve the ai. How many battles should they play?: ");
          cycles = input.nextInt();
          for (int c = 0; c < cycles; c++) {
            victory_ai = false;
            victory_ai2 = false;
            inputs[0] = .100;
            inputs2[0] = 1;
            inputs2[1] = 0;
            inputs2[2] = 0;
            inputs2[3] = 1;
            inputs2[4] = 1;
            inputs2[5] = 0;
            inputs2[6] = 0;
            inputs2[7] = 0;
            AI_1.think(10000);
            while (ai_limit > 0) {
            ai_stats = format.form(AI_1.respond().clone());
            for (int a = 0; a < 4; a++) {
              if (a == 0) {
                if (ai_limit > 0) {
                  h_holder = (int)ai_stats[a];
                  if (h_holder < ai_limit) {
                    ai_health += h_holder;
                    ai_limit -= h_holder;
                  } else if (h_holder >= ai_limit) {
                    if (ai_limit != 100) {
                      ai_health += ai_limit;
                      ai_limit -= h_holder;
                    } else {
                      ai_health = 100;
                      ai_limit -= h_holder;
                    }
                  }
                }
              } else if (a == 1) {
                if (ai_limit > 0) {
                  a_holder = (int)ai_stats[a];
                  if (a_holder < ai_limit) {
                    ai_attack += a_holder;
                    ai_limit -= a_holder;
                  } else if (a_holder >= ai_limit) {
                    if (ai_limit != 100) {
                      ai_attack += ai_limit;
                      ai_limit -= a_holder;
                    } else {
                      ai_attack = 100;
                      ai_limit -= a_holder;
                    }
                  }
                }
              } else if (a == 2) {
                if (ai_limit > 0) {
                  d_holder = (int)ai_stats[a];
                  if (d_holder < ai_limit) {
                    ai_defense += d_holder;
                    ai_limit -= d_holder;
                  } else if (d_holder >= ai_limit) {
                    if (ai_limit != 100) {
                      ai_defense += ai_limit;
                      ai_limit -= d_holder;
                    } else {
                      ai_defense = 100;
                      ai_limit -= d_holder;
                    }
                  }
                }
              } else if (a == 3) {
                if (ai_limit > 0) {
                  ag_holder = (int)ai_stats[a];
                  if (ag_holder < ai_limit) {
                    ai_agility += ag_holder;
                    ai_limit -= ag_holder;
                  } else if (ag_holder >= ai_limit) {
                    if (p_limit != 100) {
                      ai_agility += ai_limit;
                      ai_limit -= ag_holder;
                    } else {
                      ai_agility = 100;
                      ai_limit -= ag_holder;
                    }
                  }
                }
              }
            }
            if (ai_limit >= 0) {
              inputs[0] = ai_limit;
              AI_1.change_i(inputs);
            }
          }
          AI_2.think(10000, 1);
          while (ai_limit2 > 0) {
            ai_stats2 = AI_2.respond(1).clone();
            s_holder = "";
            for (int a = 0; a < 32; a++) {
              if (a < 8) {
                s_holder += (int)ai_stats2[a];
                if (a == 7) {
                  if (ai_limit2 > 0) {
                    h_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (h_holder < ai_limit2) {
                      ai_health2 += h_holder;
                      ai_limit2 -= h_holder;
                    } else if (h_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_health2 += ai_limit2;
                        ai_limit2 -= h_holder;
                      } else {
                        ai_health2 = 100;
                        ai_limit2 -= h_holder;
                      }
                    }
                  }
                }
              } else if (a < 16 && a > 7) {
                s_holder += (int)ai_stats2[a];
                if (a == 15) {
                  if (ai_limit2 > 0) {
                    a_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (a_holder < ai_limit2) {
                      ai_attack2 += a_holder;
                      ai_limit2 -= a_holder;
                    } else if (a_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_attack2 += ai_limit2;
                        ai_limit2 -= a_holder;
                      } else {
                        ai_attack2 = 100;
                        ai_limit2 -= a_holder;
                      }
                    }
                  }
                }
              } else if (a < 24 && a > 15) {
                s_holder += (int)ai_stats2[a];
                if (a == 23) {
                  if (ai_limit2 > 0) {
                    d_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (d_holder < ai_limit2) {
                      ai_defense2 += d_holder;
                      ai_limit2 -= d_holder;
                    } else if (d_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_defense2 += ai_limit2;
                        ai_limit2 -= d_holder;
                      } else {
                        ai_defense2 = 100;
                        ai_limit2 -= d_holder;
                      }
                    }
                  }
                }
              } else if (a < 32 && a > 23) {
                s_holder += (int)ai_stats2[a];
                if (a == 31) {
                  if (ai_limit2 > 0) {
                    ag_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (ag_holder < ai_limit2) {
                      ai_agility2 += ag_holder;
                      ai_limit2 -= ag_holder;
                    } else if (ag_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_agility += ai_limit2;
                        ai_limit2 -= ag_holder;
                      } else {
                        ai_agility2 = 100;
                        ai_limit2 -= ag_holder;
                      }
                    }
                  }
                }
              }
            }
            if (ai_limit2 >= 0) {
              s_holder = convert.convert_db(ai_limit2);
              for (int s = 0; s < 8; s++) {
                inputs2[s] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(s)));
              }
              AI_2.change_i(inputs2);
            }
          }
          while (victory_ai != true && victory_ai2 != true) {
            aistart_health = ai_health;
            aistart_health2 = ai_health2;
            determine = speed_check2(ai_agility, ai_agility2);
            if (determine == true) {
              ai_health2 = attack2(ai_health, ai_attack, ai_defense, ai_health2, ai_attack2, ai_defense2, determine);
              if (ai_health2 <= 0) {
                victory_ai = true;
                stats2[0] = convert.convert_db(ran.i_ran((ai_health + 5), (ai_health - 5)));
                stats2[1] = convert.convert_db(ran.i_ran((ai_attack + 5), (ai_attack - 5)));
                stats2[2] = convert.convert_db(ran.i_ran((ai_defense + 5), (ai_defense - 5)));
                stats2[3] = convert.convert_db(ran.i_ran((ai_agility + 5), (ai_agility - 5)));
                for (int d = 1; d < 5; d++) {
                  if (d == 1) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                  if (d == 2) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de + 8] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                  if (d == 3) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de + 16] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                  if (d == 4) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de + 24] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                }
                AI_2.change_d(desired2);
                ai_health2 = 0;
                ai_attack2 = 0;
                ai_defense2 = 0;
                ai_agility2 = 0;
                ai_limit2 = 100;
                ai_health = 0;
                ai_attack = 0;
                ai_defense = 0;
                ai_agility = 0;
                ai_limit = 100;
                continue;
              }
              determine = false;
              ai_health = attack2(ai_health, ai_attack, ai_defense, ai_health2, ai_attack2, ai_defense2, determine);
                if (ai_health <= 0) {
                victory_ai2 = true;
                stats[0] = ai_health2 / 100;
                stats[1] = ai_attack2 / 100;
                stats[2] = ai_defense2 / 100;
                stats[3] = ai_agility2 / 100;
                desired[0] = format.form(ran.d_ran((stats[0]+.05), (stats[0]-.05)));
                desired[1] = format.form(ran.d_ran((stats[1]+.05), (stats[1]-.05)));
                desired[2] = format.form(ran.d_ran((stats[2]+.05), (stats[2]-.05)));
                desired[3] = format.form(ran.d_ran((stats[3]+.05), (stats[3]-.05)));
                AI_1.change_d(desired);
                ai_health2 = 0;
                ai_attack2 = 0;
                ai_defense2 = 0;
                ai_agility2 = 0;
                ai_limit2 = 100;
                ai_health = 0;
                ai_attack = 0;
                ai_defense = 0;
                ai_agility = 0;
                ai_limit = 100;
                continue;
              }
            } else if (determine == false) {
              ai_health = attack2(ai_health, ai_attack, ai_defense, ai_health2, ai_attack2, ai_defense2, determine);
              if (ai_health <= 0) {
                victory_ai2 = true;
                stats[0] = ai_health2 / 100;
                stats[1] = ai_attack2 / 100;
                stats[2] = ai_defense2 / 100;
                stats[3] = ai_agility2 / 100;
                desired[0] = format.form(ran.d_ran((stats[0]+.05), (stats[0]-.05)));
                desired[1] = format.form(ran.d_ran((stats[1]+.05), (stats[1]-.05)));
                desired[2] = format.form(ran.d_ran((stats[2]+.05), (stats[2]-.05)));
                desired[3] = format.form(ran.d_ran((stats[3]+.05), (stats[3]-.05)));
                AI_1.change_d(desired);
                rounds++;
                ai_health2 = 0;
                ai_attack2 = 0;
                ai_defense2 = 0;
                ai_agility2 = 0;
                ai_limit2 = 100;
                ai_health = 0;
                ai_attack = 0;
                ai_defense = 0;
                ai_agility = 0;
                ai_limit = 100;
                continue;
              }
              determine = true;
              ai_health2 = attack2(ai_health, ai_attack, ai_defense, ai_health2, ai_attack2, ai_defense2, determine);
              if (victory_ai != true) {
                if (ai_health2 <= 0) {
                  victory_ai = true;
                  stats2[0] = convert.convert_db(ran.i_ran((ai_health + 5), (ai_health - 5)));
                  stats2[1] = convert.convert_db(ran.i_ran((ai_attack + 5), (ai_attack - 5)));
                  stats2[2] = convert.convert_db(ran.i_ran((ai_defense + 5), (ai_defense - 5)));
                  stats2[3] = convert.convert_db(ran.i_ran((ai_agility + 5), (ai_agility - 5)));
                  for (int d = 1; d < 5; d++) {
                    if (d == 1) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                    if (d == 2) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de + 8] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                    if (d == 3) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de + 16] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                    if (d == 4) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de + 24] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                  }
                  AI_2.change_d(desired2);
                  ai_health2 = 0;
                  ai_attack2 = 0;
                  ai_defense2 = 0;
                  ai_agility2 = 0;
                  ai_limit2 = 100;
                  ai_health = 0;
                  ai_attack = 0;
                  ai_defense = 0;
                  ai_agility = 0;
                  ai_limit = 100;
                }
              }
            }
            if (ai_health == aistart_health && ai_health2 == aistart_health2) {
              victory_ai = true;
              stats2[0] = convert.convert_db(ran.i_ran((ai_health + 5), (ai_health - 5)));
              stats2[1] = convert.convert_db(ran.i_ran((ai_attack + 5), (ai_attack - 5)));
              stats2[2] = convert.convert_db(ran.i_ran((ai_defense + 5), (ai_defense - 5)));
              stats2[3] = convert.convert_db(ran.i_ran((ai_agility + 5), (ai_agility - 5)));
              for (int d = 1; d < 5; d++) {
                if (d == 1) {
                  for (int de = 0; de < 8; de++) {
                    desired2[de] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                  }
                }
                if (d == 2) {
                  for (int de = 0; de < 8; de++) {
                    desired2[de + 8] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                  }
                }
                if (d == 3) {
                  for (int de = 0; de < 8; de++) {
                    desired2[de + 16] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                  }
                }
                if (d == 4) {
                  for (int de = 0; de < 8; de++) {
                    desired2[de + 24] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                  }
                }
              }
              AI_2.change_d(desired2);
            }
          }
          stats[0] = ai_health2 / 100;
          stats[1] = ai_attack2 / 100;
          stats[2] = ai_defense2 / 100;
          stats[3] = ai_agility2 / 100;
          desired[0] = format.form(ran.d_ran((stats[0]+.05), (stats[0]-.05)));
          desired[1] = format.form(ran.d_ran((stats[1]+.05), (stats[1]-.05)));
          desired[2] = format.form(ran.d_ran((stats[2]+.05), (stats[2]-.05)));
          desired[3] = format.form(ran.d_ran((stats[3]+.05), (stats[3]-.05)));
          AI_1.change_d(desired);
          ai_health2 = 0;
          ai_attack2 = 0;
          ai_defense2 = 0;
          ai_agility2 = 0;
          ai_limit2 = 100;
          ai_health = 0;
          ai_attack = 0;
          ai_defense = 0;
          ai_agility = 0;
          ai_limit = 100;
        }

      } else if (choice.equals("PvAi") || choice.equals("PvAI") || choice.equals("pvai")) {
        System.out.print("\nWhich AI would you like to play against? (1 or 2)");
        choice = input.nextLine();
        if (choice.equals("1")) {
          victory = false;
          victory_ai = false;
          inputs[0] = .100;
          System.out.println("\nThe round is " + rounds + ".");
          while (p_limit > 0) {
            if (p_limit > 0) {
              System.out.println("\nStat points: " + p_limit);
              System.out.print("\nEnter health: ");
              h_holder = input.nextInt();
              if (h_holder < p_limit) {
                health += h_holder;
                p_limit -= h_holder;
              } else if (h_holder >= p_limit) {
                if (p_limit != 100) {
                  health += p_limit;
                  p_limit -= h_holder;
                } else {
                  health = 100;
                  p_limit -= h_holder;
                }
              }
            }
            if (p_limit > 0) {
              System.out.print("\nEnter attack: ");
              a_holder = input.nextInt();
              if (a_holder < p_limit) {
                attack += a_holder;
                p_limit -= a_holder;
              } else if (a_holder >= p_limit) {
                if (p_limit != 100) {
                  attack += p_limit;
                  p_limit -= a_holder;
                } else {
                  attack = 100;
                  p_limit -= a_holder;
                }
              }
            }
            if (p_limit > 0) {
              System.out.print("\nEnter defense: ");
              d_holder = input.nextInt();
              if (d_holder < p_limit) {
                defense += d_holder;
                p_limit -= d_holder;
              } else if (d_holder >= p_limit) {
                if (p_limit != 100) {
                  defense += p_limit;
                  p_limit -= d_holder;
                } else {
                  defense = 100;
                  p_limit -= d_holder;
                }
              }
            }
            if (p_limit > 0) {
              System.out.print("\nEnter agility: ");
              ag_holder = input.nextInt();
              if (ag_holder < p_limit) {
                agility += ag_holder;
                p_limit -= ag_holder;
              } else if (ag_holder >= p_limit) {
                if (p_limit != 100) {
                  agility += p_limit;
                  p_limit -= ag_holder;
                } else {
                  agility = 100;
                  p_limit -= ag_holder;;
                }
              }
            }
          }
          System.out.println("\nPrepping AI...");
          AI_1.think(10000);
          while (ai_limit > 0) {
          ai_stats = format.form(AI_1.respond().clone());
          for (int a = 0; a < 4; a++) {
            if (a == 0) {
              if (ai_limit > 0) {
                h_holder = (int)ai_stats[a];
                if (h_holder < ai_limit) {
                  ai_health += h_holder;
                  ai_limit -= h_holder;
                } else if (h_holder >= ai_limit) {
                  if (ai_limit != 100) {
                    ai_health += ai_limit;
                    ai_limit -= h_holder;
                  } else {
                    ai_health = 100;
                    ai_limit -= h_holder;
                  }
                }
              }
            } else if (a == 1) {
              if (ai_limit > 0) {
                a_holder = (int)ai_stats[a];
                if (a_holder < ai_limit) {
                  ai_attack += a_holder;
                  ai_limit -= a_holder;
                } else if (a_holder >= ai_limit) {
                  if (ai_limit != 100) {
                    ai_attack += ai_limit;
                    ai_limit -= a_holder;
                  } else {
                    ai_attack = 100;
                    ai_limit -= a_holder;
                  }
                }
              }
            } else if (a == 2) {
              if (ai_limit > 0) {
                d_holder = (int)ai_stats[a];
                if (d_holder < ai_limit) {
                  ai_defense += d_holder;
                  ai_limit -= d_holder;
                } else if (d_holder >= ai_limit) {
                  if (ai_limit != 100) {
                    ai_defense += ai_limit;
                    ai_limit -= d_holder;
                  } else {
                    ai_defense = 100;
                    ai_limit -= d_holder;
                  }
                }
              }
            } else if (a == 3) {
              if (ai_limit > 0) {
                ag_holder = (int)ai_stats[a];
                if (ag_holder < ai_limit) {
                  ai_agility += ag_holder;
                  ai_limit -= ag_holder;
                } else if (ag_holder >= ai_limit) {
                  if (p_limit != 100) {
                    ai_agility += ai_limit;
                    ai_limit -= ag_holder;
                  } else {
                    ai_agility = 100;
                    ai_limit -= ag_holder;
                  }
                }
              }
            }
          }
          if (ai_limit >= 0) {
            inputs[0] = ai_limit;
            AI_1.change_i(inputs);
          }
        }
          while (victory != true && victory_ai != true) {
            start_health = health;
            aistart_health = ai_health;
            determine = speed_check(agility, ai_agility);
            if (determine == true) {
              ai_health = attack(health, attack, defense, ai_health, ai_attack, ai_defense, determine);
              if (ai_health <= 0) {
                System.out.println("\nGame over. Player wins. AI training begins.");
                victory = true;
                stats[0] = health / 100;
                stats[1] = attack / 100;
                stats[2] = defense / 100;
                stats[3] = agility / 100;
                desired[0] = format.form(ran.d_ran((stats[0]+.05), (stats[0]-.05)));
                desired[1] = format.form(ran.d_ran((stats[1]+.05), (stats[1]-.05)));
                desired[2] = format.form(ran.d_ran((stats[2]+.05), (stats[2]-.05)));
                desired[3] = format.form(ran.d_ran((stats[3]+.05), (stats[3]-.05)));
                AI_1.change_d(desired);
                rounds++;
                ai_health = 0;
                ai_attack = 0;
                ai_defense = 0;
                ai_agility = 0;
                health = 0;
                attack = 0;
                defense = 0;
                agility = 0;
                p_limit = 100;
                ai_limit = 100;
                continue;
              }
              determine = false;
              health = attack(health, attack, defense, ai_health, ai_attack, ai_defense, determine);
                if (health <= 0) {
                System.out.println("\nGame over. AI wins. Better luck next time Player.");
                victory_ai = true;
                rounds++;
                ai_health = 0;
                ai_attack = 0;
                ai_defense = 0;
                ai_agility = 0;
                health = 0;
                attack = 0;
                defense = 0;
                agility = 0;
                p_limit = 100;
                ai_limit = 100;
                continue;
              }
            } else if (determine == false) {
              health = attack(health, attack, defense, ai_health, ai_attack, ai_defense, determine);
              if (health <= 0) {
                System.out.println("\nGame over. AI wins. Better luck next time Player.");
                victory_ai = true;
                rounds++;
                ai_health = 0;
                ai_attack = 0;
                ai_defense = 0;
                ai_agility = 0;
                health = 0;
                attack = 0;
                defense = 0;
                agility = 0;
                p_limit = 100;
                ai_limit = 100;
                continue;
              }
              determine = true;
              ai_health = attack(health, attack, defense, ai_health, ai_attack, ai_defense, determine);
              if (victory_ai != true) {
                if (ai_health <= 0) {
                  System.out.println("\nGame over. Player wins. AI training begins.");
                  victory = true;
                  stats[0] = health / 100;
                  stats[1] = attack / 100;
                  stats[2] = defense / 100;
                  stats[3] = agility / 100;
                  desired[0] = format.form(ran.d_ran((stats[0]+.05), (stats[0]-.05)));
                  desired[1] = format.form(ran.d_ran((stats[1]+.05), (stats[1]-.05)));
                  desired[2] = format.form(ran.d_ran((stats[2]+.05), (stats[2]-.05)));
                  desired[3] = format.form(ran.d_ran((stats[3]+.05), (stats[3]-.05)));
                  AI_1.change_d(desired);
                  rounds++;
                }
              }
            }
            if (health == start_health && ai_health == aistart_health) {
              victory = true;
              System.out.println("It's a draw.");
              rounds++;
            }
          }
          ai_health = 0;
          ai_attack = 0;
          ai_defense = 0;
          ai_agility = 0;
          health = 0;
          attack = 0;
          defense = 0;
          agility = 0;
          p_limit = 100;
          ai_limit = 100;
        } else if (choice.equals("2")) {
          victory = false;
          victory_ai2 = false;
          inputs2[0] = 1;
          inputs2[1] = 0;
          inputs2[2] = 0;
          inputs2[3] = 1;
          inputs2[4] = 1;
          inputs2[5] = 0;
          inputs2[6] = 0;
          inputs2[7] = 0;
          System.out.println("\nThe round is " + rounds + ".");
          while (p_limit > 0) {
            if (p_limit > 0) {
              System.out.println("\nStat points: " + p_limit);
              System.out.print("\nEnter health: ");
              h_holder = input.nextInt();
              if (h_holder < p_limit) {
                health += h_holder;
                p_limit -= h_holder;
              } else if (h_holder >= p_limit) {
                if (p_limit != 100) {
                  health += p_limit;
                  p_limit -= h_holder;
                } else {
                  health = 100;
                  p_limit -= h_holder;
                }
              }
            }
            if (p_limit > 0) {
              System.out.print("\nEnter attack: ");
              a_holder = input.nextInt();
              if (a_holder < p_limit) {
                attack += a_holder;
                p_limit -= a_holder;
              } else if (a_holder >= p_limit) {
                if (p_limit != 100) {
                  attack += p_limit;
                  p_limit -= a_holder;
                } else {
                  attack = 100;
                  p_limit -= a_holder;
                }
              }
            }
            if (p_limit > 0) {
              System.out.print("\nEnter defense: ");
              d_holder = input.nextInt();
              if (d_holder < p_limit) {
                defense += d_holder;
                p_limit -= d_holder;
              } else if (d_holder >= p_limit) {
                if (p_limit != 100) {
                  defense += p_limit;
                  p_limit -= d_holder;
                } else {
                  defense = 100;
                  p_limit -= d_holder;
                }
              }
            }
            if (p_limit > 0) {
              System.out.print("\nEnter agility: ");
              ag_holder = input.nextInt();
              if (ag_holder < p_limit) {
                agility += ag_holder;
                p_limit -= ag_holder;
              } else if (ag_holder >= p_limit) {
                if (p_limit != 100) {
                  agility += p_limit;
                  p_limit -= ag_holder;
                } else {
                  agility = 100;
                  p_limit -= ag_holder;;
                }
              }
            }
          }
          System.out.println("\nPrepping AI...");
          AI_2.think(10000, 1);
          while (ai_limit2 > 0) {
            ai_stats2 = AI_2.respond(1).clone();
            s_holder = "";
            for (int a = 0; a < 32; a++) {
              if (a < 8) {
                s_holder += (int)ai_stats2[a];
                if (a == 7) {
                  if (ai_limit2 > 0) {
                    h_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (h_holder < ai_limit2) {
                      ai_health2 += h_holder;
                      ai_limit2 -= h_holder;
                    } else if (h_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_health2 += ai_limit2;
                        ai_limit2 -= h_holder;
                      } else {
                        ai_health2 = 100;
                        ai_limit2 -= h_holder;
                      }
                    }
                  }
                }
              } else if (a < 16 && a > 7) {
                s_holder += (int)ai_stats2[a];
                if (a == 15) {
                  if (ai_limit2 > 0) {
                    a_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (a_holder < ai_limit2) {
                      ai_attack2 += a_holder;
                      ai_limit2 -= a_holder;
                    } else if (a_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_attack2 += ai_limit2;
                        ai_limit2 -= a_holder;
                      } else {
                        ai_attack2 = 100;
                        ai_limit2 -= a_holder;
                      }
                    }
                  }
                }
              } else if (a < 24 && a > 15) {
                s_holder += (int)ai_stats2[a];
                if (a == 23) {
                  if (ai_limit2 > 0) {
                    d_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (d_holder < ai_limit2) {
                      ai_defense2 += d_holder;
                      ai_limit2 -= d_holder;
                    } else if (d_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_defense2 += ai_limit2;
                        ai_limit2 -= d_holder;
                      } else {
                        ai_defense2 = 100;
                        ai_limit2 -= d_holder;
                      }
                    }
                  }
                }
              } else if (a < 32 && a > 23) {
                s_holder += (int)ai_stats2[a];
                if (a == 31) {
                  if (ai_limit2 > 0) {
                    ag_holder = convert.convert_bd(s_holder);
                    s_holder = "";
                    if (ag_holder < ai_limit2) {
                      ai_agility2 += ag_holder;
                      ai_limit2 -= ag_holder;
                    } else if (ag_holder >= ai_limit2) {
                      if (ai_limit2 != 100) {
                        ai_agility += ai_limit2;
                        ai_limit2 -= ag_holder;
                      } else {
                        ai_agility2 = 100;
                        ai_limit2 -= ag_holder;
                      }
                    }
                  }
                }
              }
            }
            if (ai_limit2 >= 0) {
              s_holder = convert.convert_db(ai_limit2);
              for (int s = 0; s < 8; s++) {
                inputs2[s] = Integer.parseInt(s_holder.valueOf(s_holder.charAt(s)));
              }
              AI_2.change_i(inputs2);
            }
          }
          while (victory != true && victory_ai2 != true) {
            start_health = health;
            aistart_health2 = ai_health2;
            determine = speed_check(agility, ai_agility2);
            if (determine == true) {
              ai_health2 = attack(health, attack, defense, ai_health2, ai_attack2, ai_defense2, determine);
              if (ai_health2 <= 0) {
                System.out.println("\nGame over. Player wins. AI training begins.");
                victory = true;
                stats2[0] = convert.convert_db(ran.i_ran((health + 5), (health - 5)));
                stats2[1] = convert.convert_db(ran.i_ran((attack + 5), (attack - 5)));
                stats2[2] = convert.convert_db(ran.i_ran((defense + 5), (defense - 5)));
                stats2[3] = convert.convert_db(ran.i_ran((agility + 5), (agility - 5)));
                for (int d = 1; d < 5; d++) {
                  if (d == 1) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                  if (d == 2) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de + 8] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                  if (d == 3) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de + 16] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                  if (d == 4) {
                    for (int de = 0; de < 8; de++) {
                      desired2[de + 24] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                    }
                  }
                }
                AI_2.change_d(desired2);
                rounds++;
                ai_health2 = 0;
                ai_attack2 = 0;
                ai_defense2 = 0;
                ai_agility2 = 0;
                health = 0;
                attack = 0;
                defense = 0;
                agility = 0;
                p_limit = 100;
                ai_limit2 = 100;
                continue;
              }
              determine = false;
              health = attack(health, attack, defense, ai_health2, ai_attack2, ai_defense2, determine);
                if (health <= 0) {
                System.out.println("\nGame over. AI wins. Better luck next time Player.");
                victory_ai2 = true;
                rounds++;
                ai_health2 = 0;
                ai_attack2 = 0;
                ai_defense2 = 0;
                ai_agility2 = 0;
                health = 0;
                attack = 0;
                defense = 0;
                agility = 0;
                p_limit = 100;
                ai_limit2 = 100;
                continue;
              }
            } else if (determine == false) {
              health = attack(health, attack, defense, ai_health2, ai_attack2, ai_defense2, determine);
              if (health <= 0) {
                System.out.println("\nGame over. AI wins. Better luck next time Player.");
                victory_ai2 = true;
                rounds++;
                ai_health2 = 0;
                ai_attack2 = 0;
                ai_defense2 = 0;
                ai_agility2 = 0;
                health = 0;
                attack = 0;
                defense = 0;
                agility = 0;
                p_limit = 100;
                ai_limit2 = 100;
                continue;
              }
              determine = true;
              ai_health2 = attack(health, attack, defense, ai_health2, ai_attack2, ai_defense2, determine);
              if (victory_ai != true) {
                if (ai_health <= 0) {
                  System.out.println("\nGame over. Player wins. AI training begins.");
                  victory = true;
                  stats2[0] = convert.convert_db(ran.i_ran((health + 5), (health - 5)));
                  stats2[1] = convert.convert_db(ran.i_ran((attack + 5), (attack - 5)));
                  stats2[2] = convert.convert_db(ran.i_ran((defense + 5), (defense - 5)));
                  stats2[3] = convert.convert_db(ran.i_ran((agility + 5), (agility - 5)));
                  for (int d = 1; d < 5; d++) {
                    if (d == 1) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                    if (d == 2) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de + 8] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                    if (d == 3) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de + 16] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                    if (d == 4) {
                      for (int de = 0; de < 8; de++) {
                        desired2[de + 24] = Integer.parseInt(stats2[d-1].valueOf(stats2[d-1].charAt(de)));
                      }
                    }
                  }
                  AI_2.change_d(desired2);
                  rounds++;
                  ai_health2 = 0;
                  ai_attack2 = 0;
                  ai_defense2 = 0;
                  ai_agility2 = 0;
                  health = 0;
                  attack = 0;
                  defense = 0;
                  agility = 0;
                  p_limit = 100;
                  ai_limit2 = 100;
              }
            }
          }
          if (health == start_health && ai_health2 == aistart_health2) {
            victory = true;
            System.out.println("It's a draw.");
            rounds++;
          }
        }
        ai_health2 = 0;
        ai_attack2 = 0;
        ai_defense2 = 0;
        ai_agility2 = 0;
        health = 0;
        attack = 0;
        defense = 0;
        agility = 0;
        p_limit = 100;
        ai_limit2 = 100;
        }
      }
    }
  }
}
