package machine_learning;
import java.util.*;
import utilities.*;

public class Neuron {
  ArrayList<Gene> genes = new ArrayList<Gene>();

  public Neuron (Gene g) {
    genes.add(g.clone());
  }

  public Neuron (ArrayList<Gene> g) {
    genes = g.clone();
  }

  public double output (double[] in) {
    double input = 0;
    for (int g = 0; g < genes.size(); g++) {
      for (int w = 0; w < in.length; w++) {
        if (genes.get(g).usable()) {
          input += genes.get(g).weights.get(w) * in[w];
        }
      }
    }
    return 1.0 / (1.0 + Math.exp((-1 * input)));
  }

  public ArrayList<Gene> get_genes () {
    return genes;
  }

  publi voidc add_gene (Gene g) {
    genes.add(g.clone());
  }

  public void disable_g (int index) {
    genes.get(index).disable();
  }

  public void enable_g (int index) {
    genes.get(index).enable();
  }

  public Gene get_gene (int index) {
    return genes.get(index);
  }

  public void change_gene (int index, Gene g) {
    genes.get(index) = g.clone();
  }

  public void randomize_w () {
    Ran ran = new Ran();
    for (int g = 0; g < genes.size(); g++) {
      for (int w = 0; w < in.length; w++) {
        if (genes.get(g).usable()) {
          genes.get(g).weights.get(w) = ran.d_ran(genes.get(g).weights.get(w)+0.03,genes.get(g).weights.get(w)-0.03);
        }
      }
    }
  }
}
