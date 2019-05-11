package logic_gates.gates;

public class XOR {
  public int output (int in1, int in2) {
    int ret = 1;
    if ((in1 == 1 && in2 == 1) || (in1 == 0 && in2 == 0)) {
      ret = 0;
    }
    return ret;
  }
}
