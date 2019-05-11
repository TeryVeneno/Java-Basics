package logic_gates.circuits;

public class Eight_Bit_Number {

  int[] vals = new int[8];

  public Eight_Bit_Number (int[] values) {
      vals = values.clone();
  }

  public int get_digit (int pos) {
    return vals[pos];
  }

  public int[] get_array_number () {
    return vals;
  }
}
