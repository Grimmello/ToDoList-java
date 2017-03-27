import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import org.sql2o.*;

public class ToDo {
  private String description;
  private boolean completed;
  private LocalDateTime createdAt;
  private int id;

  public ToDo(String description) {
    this.description = description;
    completed = false;
    createdAt = LocalDateTime.now();
  }

  public String getDescription() {
    return description;
  }

  public boolean isCompleted() {
    return completed;
  }
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public int getId() {
    return id;
  }

  // public static ToDo find(int id) {
  //
  // }

  public static List<ToDo> all() {
    String sql = "SELECT id, description FROM tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(ToDo.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks(description) VALUES (:description)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
      }
    }

  @Override
  public boolean equals(Object otherToDo){
    if (!(otherToDo instanceof ToDo)) {
      return false;
    } else {
      ToDo newToDo = (ToDo) otherToDo;
      return this.getDescription().equals(newToDo.getDescription()) && this.getId() == newToDo.getId();
    }
  }

  public static ToDo find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks where id=:id";
      ToDo todo = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(ToDo.class);
      return todo;
    }
  }

}
