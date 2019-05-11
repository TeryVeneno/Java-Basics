package machine_learning.networks;
import machine_learning.neurons.Neuron;
import java.util.*;
import utilities.*;

public class Network {
  private ArrayList<ArrayList<Neuron>> neurons;
  private ArrayList<Double> last_inputs;
  private int o_ns;
  private int ns;
  private int lys;
  private int limit;
  private Random rand = new Random(System.currentTimeMillis());
  private int ite;


  public Network (int layers, int neuron, int o_neurons, int input_amount, double learn_rate, double eagerness, int memory_size, int lim) {
    ArrayList<Integer> connect = new ArrayList<Integer>(neuron);
    o_ns = o_neurons;
    ns = neuron;
    lys = layers;
    limit = lim;
    neurons =  new ArrayList<ArrayList<Neuron>>(layers);
    for (int s = 0; s < layers; s++) {
      neurons.add(new ArrayList<Neuron>(neuron));
    }
    for (int l = 0; l < lys-1; l++) {
      for (int n = 0; n < neuron; n++) {
        connect.add(l+1*neuron+n);
      }
      for (int n = 0; n < neuron; n++) {
        neurons.get(l).add(new Neuron(input_amount, connect, memory_size, learn_rate, eagerness));
      }
      neurons.get(l).trimToSize();
    }
    for (int n = 0; n < o_ns; n++) {
      neurons.get(lys-1).add(new Neuron(input_amount, connect, memory_size, learn_rate, eagerness));
    }
    neurons.get(lys-1).trimToSize();
  }

  public void improve (double[] desire, double reward) {
    double error = 0;
    double total_error = 0;
    for (int d = 0; d < desire.length; d++) {
      error = desire[d] - neurons.get(lys-1).get(d).ret_output();
      total_error += Math.pow(error, 2);
      neurons.get(lys-1).get(d).train(reward, error);
    }
    for (int l = 0; l < neurons.size()-1; l++) {
      for (int n = 0; n < neurons.get(l).size(); n++) {
        neurons.get(l).get(n).train(reward, total_error);
      }
    }
  }

  public ArrayList<Double> respond (ArrayList<Double> inputs) {
    ArrayList<Double> ret = new ArrayList<Double>();
    last_inputs = new ArrayList<Double>(inputs);
    if (ite > limit) {
      ArrayList<Double> feed_forward = new ArrayList<Double>();
      ArrayList<Double> last = new ArrayList<Double>();
      for (int l = 1; l < lys-1; l++) {
        for (int n = 0; n < ns; n++) {
          feed_forward.clear();
          for (int s = 0; s < ns; s++) {
            if (neurons.get(l-1).get(s).check_connections(l*ns+n)) {
              if (l-1 == 0) {
                feed_forward.add(neurons.get(l-1).get(s).respond(inputs));
                feed_forward.add(neurons.get(l-1).get(s).send_memory());
              } else {
                feed_forward.add(neurons.get(l-1).get(s).respond(last));
                feed_forward.add(neurons.get(l-1).get(s).send_memory());
              }
            }
          }
          last.clear();
          last.addAll(feed_forward);
        }
      }
      for (int n = 0; n < o_ns; n++) {
        ret.add(neurons.get(lys-1).get(n).respond(inputs));
      }
    } else {
      for (int s = 0; s < neurons.get(lys-1).size(); s++) {
        ret.add(rand.nextDouble());
      }
      ite++;
    }
    return ret;
  }

  public void think (int cycles, double[] desire, double reward) {
    for (int t = 0; t < cycles; t++) {
      respond(last_inputs);
      improve(desire, reward);
    }
  }

  public void print_all_mem () {
    for (int l = 0; l < lys; l++) {
      for (int n = 0; n < neurons.get(l).size(); n++) {
        System.out.println(Arrays.toString(neurons.get(l).get(n).ret_memory()));
      }
    }
  }
}
