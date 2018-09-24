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

  public double output (ArrayList<Double> in) {
    double input = 0;
    for (int g = 0; g < genes.size(); g++) {
      if (genes.get(g).usable()) {
        input += genes.get(g).weight * in[g];
      }
    }
    return 1.0 / (1.0 + Math.exp((-1 * input)));
  }

  public ArrayList<Gene> get_genes () {
    return genes;
  }

  publi void add_gene (Gene g) {
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
      if (genes.get(g).usable()) {
        genes.get(g).weight = ran.d_ran(genes.get(g).weight+0.03,genes.get(g).weight-0.03);
      }
    }
  }

  public void set_w (double val, int gene_i, int weight_i) {
    genes.get(gene_i).weights.get(weight_i) = val;
  }

  public double get_w (int gene_i, int weight_i) {
    return genes.get(gene_i).weights.get(weight_i);
  }
}
