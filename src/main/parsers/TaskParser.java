package parsers;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// Represents Task parser
public class TaskParser {
    
    // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
    // string and parses it as a task; each parsed task is added to the list of tasks.
    // Any task that cannot be parsed due to malformed JSON data is not added to the
    // list of tasks.
    // Note: input is a string representation of a JSONArray
    public List<Task> parse(String input) {
        JSONArray dabking = new JSONArray(input);
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < dabking.length(); i++) {
            JSONObject off = dabking.getJSONObject(i);
            if (isObjectValid(off)) {
                Task k = new Task(off.getString("description"));
                k.setDueDate(jsonDateParser(off));
                k.setPriority(jsonPrioParser(off));
                jsonStatusSet(off, k);
                jsonTagsSet(off.getJSONArray("tags"), k);
                taskList.add(k);
            }
        }

        return taskList;
    }

    public void jsonTagsSet(JSONArray array, Task t) {
        for (int i = 0; i < array.length(); i++) {
            if (array.getJSONObject(i).has("name")
                    && (array.getJSONObject(i).get("name") instanceof String)) {
                Tag b = new Tag(array.getJSONObject(i).getString("name"));
                t.addTag(b);
            }
        }

    }

    public boolean isObjectValid(JSONObject obj) {
        return (isDescrValid(obj) && isPriorityValid(obj) && isStatusValid(obj) && isTagArrayValid(obj));
    }



    public void jsonStatusSet(JSONObject obj, Task t) {
        if (obj.getString("status").equals("IN_PROGRESS")) {
            t.setStatus(Status.IN_PROGRESS);
        } else if (obj.getString("status").equals("TODO")) {
            t.setStatus(Status.TODO);
        } else if (obj.getString("status").equals("DONE")) {
            t.setStatus(Status.DONE);
        } else if (obj.getString("status").equals("UP_NEXT")) {
            t.setStatus(Status.UP_NEXT);
        }

    }

    public boolean isTagArrayValid(JSONObject obj) {
        if (obj.isNull("tags")) {
            return false;
        } else {
            return (obj.has("tags") && (obj.getJSONArray("tags") instanceof JSONArray)
                    && !obj.isNull("tags"));
        }
    }

    public boolean isTagValid(JSONObject tag) {
        return (tag.has("name") && (tag.get("name") instanceof String) && !tag.isNull("name"));
    }

    public boolean isStatusValid(JSONObject obj) {
        return (obj.has("status") && (obj.get("status") instanceof String) && !obj.isNull("status"));
    }

    public boolean isPriorityValid(JSONObject prio) {
        return (prio.has("priority") && (prio.get("priority") instanceof JSONObject) && !prio.isNull("priority"));
    }

    public boolean isDescrValid(JSONObject off) {
        if (off.isNull("description")) {
            return false;
        } else {
            return (off.has("description") && (off.get("description") instanceof String)
                    && !off.isNull("description"));
        }
    }


    public Priority jsonPrioParser(JSONObject object) {
        JSONObject prio = object.getJSONObject("priority");
        Priority p = new Priority();
        p.setImportant(prio.getBoolean("important"));
        p.setUrgent(prio.getBoolean("urgent"));
        return p;
    }

    public DueDate jsonDateParser(JSONObject obj) {
        Calendar ca = Calendar.getInstance();
        if (obj.isNull("due-date")) {
            return null;
        } else {
            JSONObject date = obj.getJSONObject("due-date");
            ca.set(Calendar.YEAR, date.getInt("year"));
            ca.set(Calendar.MONTH, date.getInt("month"));
            ca.set(Calendar.HOUR, date.getInt("hour"));
            ca.set(Calendar.MINUTE, date.getInt("minute"));
            ca.set(Calendar.DAY_OF_MONTH, date.getInt("day"));
        }
        DueDate d = new DueDate();
        Date p = ca.getTime();
        d.setDueDate(p);
        return d;

    }






}
    

