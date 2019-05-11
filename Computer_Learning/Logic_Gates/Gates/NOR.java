package logic_gates.gates;

public class NOR {
  public int output (int in1, int in2) {
    int ret = 0;
    if (in1 == 0 && in2 == 0) {
      ret = 1;
    }
    return ret;
  }
}
