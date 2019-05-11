package machine_learning;

public interface Action_Definer {
  public int[] pos_actions (int length, int width);

  public double transpose (double val);

  public double[] next_state (int action);
}
