package persistence;


import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

// Converts model elements to JSON objects
public class Jsonifier {
    
    // EFFECTS: returns JSON representation of tag
   /* public static JSONObject tagToJson(Tag tag) {
        return null;
    }
    
    // EFFECTS: returns JSON representation of priority
    public static JSONObject priorityToJson(Priority priority) {
        return null;
    }
    
    // EFFECTS: returns JSON respresentation of dueDate
    public static JSONObject dueDateToJson(DueDate dueDate) {
        return null;
    }
    
    // EFFECTS: returns JSON representation of task
    public static JSONObject taskToJson(Task task) {
        return null;
    }
    
    // EFFECTS: returns JSON array representing list of tasks
    public static JSONArray taskListToJson(List<Task> tasks) {
        return null;   // stub
    }*/

    // EFFECTS: returns JSON representation of tag
    public static JSONObject tagToJson(Tag tag) {
        JSONObject tagToJson = new JSONObject();
        tagToJson.put("name",tag.getName());
        return tagToJson;
    }

    // EFFECTS: returns JSON representation of priority
    public static JSONObject priorityToJson(Priority priority) {
        JSONObject priorityToJson = new JSONObject();
        priorityToJson.put("important",priority.isImportant());
        priorityToJson.put("urgent",priority.isUrgent());
        return priorityToJson;
    }

    // EFFECTS: returns JSON respresentation of dueDate
    public static JSONObject dueDateToJson(DueDate dueDate) {
        JSONObject ddToJson = new JSONObject();
        if (dueDate == null) {
            ddToJson = null;
            return ddToJson;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dueDate.getDate());

        ddToJson.put("month",cal.get(Calendar.MONTH));
        ddToJson.put("year",cal.get(Calendar.YEAR));
        ddToJson.put("day",cal.get(Calendar.DATE));
        ddToJson.put("hour",cal.get(Calendar.HOUR));
        ddToJson.put("minute",cal.get(Calendar.MINUTE));

        return ddToJson;
    }

    // EFFECTS: returns JSON representation of task
    public static JSONObject taskToJson(Task task) {
        JSONObject tasktoJson = new JSONObject();
        tasktoJson.put("description",task.getDescription());
        tasktoJson.put("tags",tagstoJson(task.getTags()));
        tasktoJson.put("due-date",dueDateToJson(task.getDueDate()));
        tasktoJson.put("priority",priorityToJson(task.getPriority()));
        if (task.getStatus().equals(Status.TODO)) {
            tasktoJson.put("status","TODO");
        } else if (task.getStatus().equals(Status.IN_PROGRESS)) {
            tasktoJson.put("status","IN_PROGRESS");
        } else if (task.getStatus().equals(Status.UP_NEXT)) {
            tasktoJson.put("status","UP_NEXT");
        } else if (task.getStatus().equals(Status.DONE)) {
            tasktoJson.put("status","DONE");
        }
        return tasktoJson;

    }



    public static JSONArray tagstoJson(Set<Tag> tags) {
        JSONArray tagstoJson = new JSONArray();

        for (Tag t: tags) {
            tagstoJson.put(tagToJson(t));
        }
        return tagstoJson;

    }

    // EFFECTS: returns JSON array representing list of tasks
    public static JSONArray taskListToJson(List<Task> tasks) {
        JSONArray taskstoArray = new JSONArray();
        for (Task t : tasks) {
            taskstoArray.put(taskToJson(t));
        }
        return taskstoArray;
    }

}







