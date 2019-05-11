package logic_gates.gates;

public class NOT {
  public int output (int in) {
    int ret = 1;
    if (in == 1) {
      ret = 0;
    }
    return ret;
  }
}
