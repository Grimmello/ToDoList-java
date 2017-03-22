import java.util.*;

public class Category {
  private String mName;
  private static List<Category> instances = new ArrayList<Category>();
  private int mId;
  private List<ToDo> mTasks;

  public Category(String name) {
    mName = name;
    instances.add(this);
    mId = instances.size();
    mTasks = new ArrayList<ToDo>();
  }

  public String getName() {
    return mName;
  }

  public static List<Category> all() {
    return instances;
  }

  public static void clearList() {
    instances.clear();
  }

  public int getId() {
    return mId;
  }

  public static Category find(int id) {
    try {
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public List<ToDo> getTasks() {
    return mTasks;
  }

  public void addTask(ToDo task) {
    mTasks.add(task);
  }
}
