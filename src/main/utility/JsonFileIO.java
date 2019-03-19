package utility;

import model.Task;
import org.json.JSONArray;
import parsers.TaskParser;
import persistence.Jsonifier;

import java.io.*;
import java.util.List;

// File input/output operations
public class JsonFileIO {
    public static final File jsonDataFile = new File("./resources/json/tasks.json");
    
    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Task> read() {
        String jsonText = "";
        TaskParser p = new TaskParser();
        try {
            FileReader fr = new FileReader(jsonDataFile);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            jsonText = sb.toString();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return p.parse(jsonText);
    }
    
    // EFFECTS: saves the tasks to jsonDataFile
    public static void write(List<Task> tasks) {
        JSONArray taskArray = Jsonifier.taskListToJson(tasks);
        String tasksString = taskArray.toString();
        try {
            FileWriter file = new FileWriter(jsonDataFile);
            file.write(tasksString);
            file.flush();
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
