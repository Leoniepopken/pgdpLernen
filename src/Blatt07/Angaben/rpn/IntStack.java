package rpn;

/**
 * Stack implementation for integer values.
 */
public class IntStack {

  private IntStackItem last = null;

  /**
   * Adds an integer value to the stack.
   * 
   * @param i The new value.
   */
  public void push(int i) {
    IntStackItem newItem = new IntStackItem(last, i);
    last = newItem;
  }

  /**
   * Returns the top stack entry without popping it from stack
   * 
   * @return
   */
  public int peek() {
    return this.last.getValue();
  }

  /**
   * Returns and removes the top value of the stack.
   * 
   * @return The top value.
   */
  public int pop() {
    if (isEmpty()) {
      System.out.println("Error: Unable to pop from an empty stack! (Returns -1)");
      return -1;
    }
    int result = last.getValue();
    last = last.getPrev();
    return result;
  }

  /**
   * Checks whether this stack is empty.
   * 
   * @return True if it is empty, false otherwise.
   */
  public boolean isEmpty() {
    return (last == null);
  }
}
