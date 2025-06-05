package org.abhishek.structural;

// Client
import java.util.ArrayList;
import java.util.List;

/*
To use:
When the application has a hierarchical structure and needs generic functionality across the structure.
When an application needs to aggregate data across a hierarchy.
When an application wants to treat composite and individual objects uniformly.
 */
// Component
interface Task {
    String getTitle();
    void setTitle(String title);
    void display();
}

// Leaf
class SimpleTask implements Task {
    private String title;

    public SimpleTask(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void display() {
        System.out.println("Simple Task: " + title);
    }
}

// Composite
class TaskList implements Task {
    private String title;
    private List<Task> tasks;

    public TaskList(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public void display() {
        System.out.println("Task List: " + title);
        for (Task task : tasks) {
            task.display();
        }
    }
}

// Client
public class Composite {
    public static void main(String[] args) {
        // Creating a task list
        TaskList projectTasks = new TaskList("Project Tasks");
        projectTasks.addTask(new SimpleTask("Complete Coding"));
        projectTasks.addTask(new SimpleTask("Write Documentation"));

        // Nested task list
        TaskList phase1Tasks = new TaskList("Phase 1 Tasks");
        phase1Tasks.addTask(new SimpleTask("Design"));
        phase1Tasks.addTask(new SimpleTask("Implementation"));


        // Nested task list
        TaskList phase2Tasks = new TaskList("Phase 2 Tasks");

        phase2Tasks.addTask(new SimpleTask("Unit testing"));
        phase2Tasks.addTask(new SimpleTask("Integration testing"));
        phase1Tasks.addTask(phase2Tasks);
        projectTasks.addTask(phase1Tasks);

        // Displaying tasks
        projectTasks.display();
    }
}
