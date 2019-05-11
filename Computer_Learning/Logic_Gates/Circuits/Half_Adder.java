package logic_gates.circuits;

import logic_gates.gates.*;

public class Half_Adder extends Adder {
  AND and = new AND();
  XOR xor = new XOR();

  public int output_sum (int a, int b, int c) {
    return xor.output(a, b);
  }

  public int output_carry (int a, int b) {
    return and.output(a, b);
  }
}
