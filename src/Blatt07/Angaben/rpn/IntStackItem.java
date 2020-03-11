package rpn;

/**
 * Represents one item of the <code>IntStack</code>
 */
public class IntStackItem {

  private int value;
  private IntStackItem prev;

  /**
   * Constructor.
   * 
   * @param previousItem The predecessor in the stack.
   * @param i            The value of this item.
   */
  public IntStackItem(IntStackItem previousItem, int i) {
    prev = previousItem;
    value = i;
  }

  /**
   * @return the value
   */
  public int getValue() {
    return value;
  }

  /**
   * @return the prev
   */
  public IntStackItem getPrev() {
    return prev;
  }
}
