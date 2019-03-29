package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;

import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order of tasks is preserved
public class Project extends Todo implements Iterable<Todo> {
    private List<Todo> tasks;


    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    //  throws EmptyStringException if description is null or empty

    public Project(String description) {
        super(description);
        tasks = new ArrayList<>();
    }
    
    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    //   throws NullArgumentException when task is null
    public void add(Todo task) {
        if (!contains(task) && !(description == task.getDescription())) {
            tasks.add(task);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: removes task from this project
    //   throws NullArgumentException when task is null
    public void remove(Todo task) {
        if (contains(task)) {
            tasks.remove(task);
        }
    }
    
    // EFFECTS: returns the description of this project
    //public String getDescription() {
        //return description;
    //}

    @Override
    public int getEstimatedTimeToComplete() {
        int x = 0;
        for (Todo t: tasks) {
            x += t.getEstimatedTimeToComplete();

        }
        return x;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    public List<Task> getTasks() {
        throw new UnsupportedOperationException();
    }
    
    // EFFECTS: returns an integer between 0 and 100 which represents
    //     the percentage of completed tasks (rounded down to the closest integer).
    //     returns 100 if this project has no tasks!
    public int getProgress() {
        if (tasks.isEmpty()) {
            return 0;
        }
        int x = 0;
        for (Todo t: tasks) {
            x += t.getProgress();
        }
        this.progress = x / getNumberOfTasks();
        return x / getNumberOfTasks();

    }

    // EFFECTS: returns the number of tasks (and sub-projects) in this project
    public int getNumberOfTasks() {
        return tasks.size();
    }

    // EFFECTS: returns true if every task (and sub-project) in this project is completed, and false otherwise
//     If this project has no tasks (or sub-projects), return false.
    public boolean isCompleted() {
        return getNumberOfTasks() != 0 && getProgress() == 100;
    }
    
    // EFFECTS: returns true if this project contains the task
    //   throws NullArgumentException when task is null
    public boolean contains(Todo task) {
        if (task == null) {
            throw new NullArgumentException("Illegal argument: task is null");
        }
        return tasks.contains(task);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(description, project.description);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public Iterator<Todo> iterator() {
        return new ProjectIterator();
    }

    private class ProjectIterator implements Iterator<Todo> {
        Iterator p1Iterator = tasks.iterator();
        Iterator p2Iterator = tasks.iterator();
        Iterator p3Iterator = tasks.iterator();
        Iterator p4Iterator = tasks.iterator();

        @Override
        public boolean hasNext() {
            return p1Iterator.hasNext() || p2Iterator.hasNext() || p3Iterator.hasNext() || p4Iterator.hasNext();
        }

        @Override
        public Todo next() {
            Todo test;
            if (p1Iterator.hasNext()) {
                test = (Todo) p1Iterator.next();
                if (test.getPriority().isUrgent() && test.getPriority().isImportant()) {
                    return test;
                }
            } else if (p2Iterator.hasNext()) {
                test = (Todo) p2Iterator.next();
                if (!test.getPriority().isUrgent() && test.getPriority().isImportant()) {
                    return test;
                }
            } else if (p3Iterator.hasNext()) {
                test = (Todo) p3Iterator.next();
                if (test.getPriority().isUrgent() && !test.getPriority().isImportant()) {
                    return test;
                }
            } else if (p4Iterator.hasNext()) {
                test = (Todo) p4Iterator.next();
                if (!test.getPriority().isUrgent() && !test.getPriority().isImportant()) {
                    return test;
                }
            }
            return null;
        }
    }
}