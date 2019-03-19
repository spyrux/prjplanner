package controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Task;
import ui.EditTask;
import ui.EditTaskDemo;
import ui.ListView;
import ui.PomoTodoApp;
import utility.JsonFileIO;
import utility.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Controller class for Todobar UI
public class TodobarController implements Initializable {
    private static final String todoOptionsPopUpFXML = "resources/fxml/TodoOptionsPopUp.fxml";
    private static final String todoActionsPopUpFXML = "resources/fxml/TodoActionsPopUp.fxml";
    private File todoOptionsFile = new File(todoOptionsPopUpFXML);
    private File todoActionsFile = new File(todoActionsPopUpFXML);

    @FXML
    private Label descriptionLabel;
    @FXML
    private JFXHamburger todoActionsPopUpBurger;
    @FXML
    private StackPane todoActionsPopUpContainer;
    @FXML
    private JFXRippler todoOptionsPopUpRippler;
    @FXML
    private StackPane todoOptionsPopUpBurger;
    
    private Task task;
    private JFXPopup optionsPopup;
    private JFXPopup actionsPopup;
    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: sets the task in this Todobar
    //          updates the Todobar UI label to task's description
    public void setTask(Task task) {
        this.task = task;
        descriptionLabel.setText(task.getDescription());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOptionsPopup();
        loadOptionPopupActionListener();
        loadTodoActionsPopup();
        loadActionPopupActionListener();

    }

    //EFFECTS: load options popup (edit delete)
    public void loadOptionsPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoOptionsFile.toURI().toURL());
            fxmlLoader.setController(new TodobarOptionsPopupController());
            optionsPopup = new JFXPopup(fxmlLoader.load());
        } catch (IOException io) {
            throw new RuntimeException(io);
        }
    }

    private void loadTodoActionsPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoActionsFile.toURI().toURL());
            fxmlLoader.setController(new ActionsPopUpController());
            actionsPopup = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void loadOptionPopupActionListener() {
        todoOptionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                optionsPopup.show(todoOptionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT,-12,15);
            }
        });
    }

    private void loadActionPopupActionListener() {
        todoActionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                actionsPopup.show(todoActionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.LEFT,12,15);
            }
        });
    }

    class TodobarOptionsPopupController {

        @FXML
        private JFXListView<?> optionPopUpList;

        @FXML
        public void submit() {
            int selectedIndex = optionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodobarOptionsPopupController","Editing a task");
                    PomoTodoApp.setScene(new EditTask(task));
                    JsonFileIO.write(PomoTodoApp.getTasks());
                    break;

                case 1:
                    Logger.log("TodobarOptionsPopupController","Removing a task");
                    deleteTask();
                    PomoTodoApp.setScene(new ListView(PomoTodoApp.getTasks()));
                    break;


                default:
                    Logger.log("TodobarOptionsPopupController", "No functionality has been added");
            }
            optionsPopup.hide();
        }




    }

    class ActionsPopUpController {
        @FXML
        private JFXListView<?> actionPopUpList;

        @FXML
        public void submit() {
            int selectedIndex = actionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {




                default:
                    Logger.log("TodobarActionsPopupController", "No functionality has been added");
            }
            actionsPopup.hide();
        }

    }

    private void deleteTask() {
        PomoTodoApp.getTasks().remove(task);
        PomoTodoApp.setScene(new ListView(PomoTodoApp.getTasks()));
    }


}
