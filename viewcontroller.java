import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class viewcontroller implements Initializable {

    @FXML
    private Button button_1;

    @FXML
    private ChoiceBox<String> choicebox_1;

    @FXML
    private ChoiceBox<String> choicebox_2;

    @FXML
    private ChoiceBox<String> choicebox_3;

    @FXML
    private Label label_count;

    @FXML
    private Label label_result;

    @FXML
    void button_action_1(ActionEvent event) {
        // Get the selected values from the choice boxes
        String value1 = choicebox_1.getValue();
        String value2 = choicebox_2.getValue();
        String value3 = choicebox_3.getValue();
        float result = 0;
        if(value1.equals("920")){
            result +=1;
        }
        if(value2.equals("768")){
            result +=1;
        }
        if(value3.equals("-10")){
            result +=1;
        }
        label_result.setText("result: " + result + " / 3");
        button_1.setDisable(true);

    }
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        startCountdown(5 * 60);
        // Initialize the choice boxes with some values
        choicebox_1.getItems().addAll("927", "730", "920");
        choicebox_2.getItems().addAll("700", "768", "758");
        choicebox_3.getItems().addAll("-10", "10", "17");
    }
    private void startCountdown(int seconds) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = seconds; i >= 0; i--) {
                    int currentSecond = i;
                    // Update UI with the new value
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            label_count.setText(String.valueOf(currentSecond / 60) + "mn"
                                    + String.valueOf(currentSecond % 60) + "s");
                        }
                    });
                    try {
                        Thread.sleep(1000); // Pause for 1 second
                    } catch (InterruptedException e) {
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        label_count.setText("Time's up!");
                        button_action_1(null);
                        button_1.setDisable(true);
                    }
                });
            }
        });

        // Allow thread to stop when the app closes
        thread.setDaemon(true);
        thread.start();
    }

}


