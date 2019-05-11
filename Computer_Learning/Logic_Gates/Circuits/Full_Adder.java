package logic_gates.circuits;

import logic_gates.gates.*;

public class Full_Adder extends Adder {
  Half_Adder adder1 = new Half_Adder();
  Half_Adder adder2 = new Half_Adder();
  OR or = new OR();

  public int output_sum (int a, int b, int c) {
    return adder2.output_sum(adder1.output_sum(a, b, c), c, c);
  }

  public int output_carry (int a, int b) {
    return or.output(adder1.output_carry(a, b), adder2.output_carry(a, b));
  }
}
