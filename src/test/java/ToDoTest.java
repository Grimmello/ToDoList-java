import org.junit.*;
import static org.junit.Assert.*;

public class ToDoTest {
  @Test
  public void ToDoItem_instantiatesCorrectly_true() {
    ToDo myTask = new ToDo("Mow the lawn");
    assertEquals(true, myTask instanceof ToDo);
  }
  @Test
  public void ToDoItem_createsDescription_String() {
    ToDo myTask = new ToDo("Mow the lawn");
    assertEquals("Mow the lawn", myTask.getDescription());
  }
}
