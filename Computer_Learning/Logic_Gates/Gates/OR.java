package logic_gates.gates;

public class OR {
  public int output (int in1, int in2) {
    int ret = 0;
    if (in1 == 1 || in2 == 1) {
      ret = 1;
    }
    return ret;
  }
}
