package machine_learning;
import utilities.*;
import java.util.*;

public class Network {
  ArrayList<ArrayList<Neuron>> neurons = new ArrayList<ArrayList<Neuron>>();
  ArrayList<ArrayList<Double>> feed = new ArrayList<ArrayList<Double>>(); ;

  public Network (int o_neurons, int s_neurons) {
    Gene gs;
    for (int s = 0; s < s_neurons; s++) {
      for (int g = 0; g < o_neurons; g++) {
        if (g == 0) {
          gs = new Gene(s_neurons+g,)
          neurons.get(1).get(s) = new Neuron();
        } else {
          gs = new Gene(s_neurons+g,)
        }
      }
    }
  }

  public Gene create_gene () {

  }
}
