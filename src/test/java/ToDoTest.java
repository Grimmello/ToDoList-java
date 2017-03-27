import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;


public class ToDoTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
  }

  @After
  public void clearEverything(){
    try(Connnection con = DB.sql2o;.open()) {
      String deleteTasksQuery = "DELTE FROM tasks *;";
      Strind deleteCategoriesQuery = "DELETE FROM categories *;";
      con.createQuery(deleteTasksQuery).executeUpdate();
      con.createQuery(deleteCategoriesQuery).executeUpdate();
    }
  }

  @Test
  public void ToDoItem_instantiatesCorrectly_true() {
    ToDo myToDo = new ToDo("Mow the lawn");
    assertEquals(true, myToDo instanceof ToDo);
  }
  @Test
  public void ToDoItem_createsDescription_String() {
    ToDo myToDo = new ToDo("Mow the lawn");
    assertEquals("Mow the lawn", myToDo.getDescription());
  }
  @Test
  public void isCompleted_isFalseAfterInstantiation_false() {
    ToDo myToDo = new ToDo("Mow the lawn");
    assertEquals(false, myToDo.isCompleted());
  }
  // test for date and timestamp
  @Test
  public void getCreatedAt_instantiatesWithCurrentTime_today() {
    ToDo myToDo = new ToDo("Mow the lawn");
    assertEquals(LocalDateTime.now().getDayOfWeek(), myToDo.getCreatedAt().getDayOfWeek());
  }

  @Test
  public void all_returnsAllInstancesOfToDo_true() {
    ToDo firstToDo = new ToDo("Mow the lawn");
    firstToDo.save();
    ToDo secondToDo = new ToDo("Buy groceries");
    secondToDo.save();
    assertEquals(true, ToDo.all().contains(firstToDo));
    assertEquals(true, ToDo.all().contains(secondToDo));
  }
  // clear arraylist
  // @Test
  // public void clear_emptiesAllToDosFromArrayList_0() {
  //   ToDo myToDo = new ToDo("Mow the lawn");
  //   ToDo.clearList();
  //   assertEquals(ToDo.all().size(), 0);
  // }
  // test if assigning id is working
  @Test
  public void getId_ToDosInstantiateWithAnID() {
    ToDo myToDo = new ToDo("Mow the lawn");
    myToDo.save();
    assertTrue(myToDo.getId() > 0);
  }
  //
  // @Test
  // public void find_returnsToDoWithSameId_secondToDo() {
  //   ToDo firstToDo = new ToDo("Mow the lawn");
  //   ToDo secondToDo = new ToDo("Buy groceries");
  //   assertEquals(ToDo.find(secondToDo.getId()), secondToDo);
  // }

  @Test
  public void equals_returnsTrueIfDescriptionsAreTheSame() {
    ToDo firstToDo = new ToDo("Mow the lawn");
    ToDo secondToDo = new ToDo("Mow the lawn");
    assertTrue(firstToDo.equals(secondToDo));
  }



  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    ToDo myToDo = new ToDo("Mow the Lawn");
    myToDo.save();
    assertTrue(ToDo.all().get(0).equals(myToDo));
  }

  @Test
  public void save_assignsIdToObject() {
    ToDo myToDo = new ToDo("Mow the lawn");
    myToDo.save();
    ToDo savedToDo = ToDo.all().get(0);
    assertEquals(myToDo.getId(), savedToDo.getId());
  }

  @Test
  public void find_returnsTaskWithSameId_secondTask() {
    ToDo firstToDo = new ToDo("Mow the lawn");
    firstToDo.save();
    ToDo secondToDo = new ToDo("Buy groceries");
    secondToDo.save();
    assertEquals(ToDo.find(secondToDo.getId()), secondToDo);
  }

}
