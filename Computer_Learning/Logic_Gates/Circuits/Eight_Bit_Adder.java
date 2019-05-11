package logic_gates.circuits;

public class Eight_Bit_Adder {
  Adder[] adders = new Adder[8];

  public Eight_Bit_Adder () {
    adders[0] = new Half_Adder ();
    for (int s = 1; s < 8; s++) {
      adder[s] = new Full_Adder ();
    }
  }

  public Eight_Bit_Number add_number (Eight_Bit_Number n1, Eight_Bit_Number n2) {
    int[] ret = new int[8];
    ret[0] = adders[0].output_sum(n1.get_digit(0), n1.get_digit(0), 1);
    for (int s = 1; s < 8; s++) {
      ret[s] = adders[s].output_sum(adders[s].output_sum(n1.get_digit(s), ))
    }
  }


}
