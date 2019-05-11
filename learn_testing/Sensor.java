package machine_learning;
import java.util.*;

public class Sensor {
  ArrayList<ArrayList<Double>> inputs = new ArrayList<ArrayList<Double>>();
  public static final int ARRAY_SENSOR = 0;
  public static final int PIXEL_SENSOR = 1;
  public static final int SINGLE_SENSOR = 2;
  private int type;
  private int multiplication_factor;
  private Action_Definer definer;

  public Sensor (int type, int multiplication_factor, Action_Definer definer) {
    this.type = type;
    this.multiplication_factor = multiplication_factor;
    this.definer = definer;
  }

  public void receive (double[][] input) {
    inputs.clear();
    for (int i = 0; i < input.length; i++) {
      inputs.add(new ArrayList<Double>());
      for (int s = 0; s < input.length; s++) {
        inputs.get(i).add(input[i][s]);
      }
    }
    inputs.trimToSize();
    for (int s = 0; s < inputs.size(); s++) {
      inputs.get(s).trimToSize();
    }
  }

  public void receive (double[] input) {
    inputs.clear();
    inputs.add(new ArrayList<Double>());
    for (int s = 0; s < input.length; s++) {
      inputs.get(0).add(input[s]);
    }
    inputs.get(0).trimToSize();
  }

  public int send_data () {
    double[] info = new double[inputs.size()];
    double data = 0;
    if (type == 0) {
      for (int s = 0; s < inputs.size(); s++) {
        for (int i = 0; i < inputs.get(s).size(); i++) {
          info[s] = info[s] + inputs.get(s).get(i)*((i*s)+s);
        }
      }
      for (int s = 0; s < info.length; s++) {
        data += info[s];
      }
      data /= info.length;
      data = definer.transpose(data)*multiplication_factor;
    } else if (type == 2) {
      for (int s = 0; s < inputs.get(0).size(); s++) {
        data += inputs.get(0).get(s);
      }
      data /= inputs.get(0).size();
      data = definer.transpose(data)*multiplication_factor;
    }
    return (int)data;
  }
}
