package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientMain extends Application
{
    private ClientView clientView;
    private ClientModel clientModel;
    private ClientController clientController;

    private String serverAddress;
    private int serverPort;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Translator");
        stage.setWidth(540);

        stage.setMinHeight(140);
        stage.setMaxHeight(140);
        stage.setMaxWidth(1080);

        clientView = new ClientView();
        stage.setScene(getServerAddress(stage, clientView.scene));

        stage.show();
    }

    private Scene getServerAddress(Stage stage, Scene nextScene)
    {
        var root = new VBox();
        var hbox = new HBox();
        var serverAddressArea = new TextArea("Server address");
        var serverPortArea = new TextArea("Server port");
        var submitButton = new Button("Ok");

        serverAddressArea.setPrefSize(540, 40);
        serverPortArea.setPrefSize(540, 40);
        submitButton.setPrefSize(1080, 40);

        submitButton.setOnAction(e ->
        {
            try
            {
                serverAddress = serverAddressArea.getText().trim();
                serverPort = Integer.parseInt(serverPortArea.getText());
            }
            catch (NumberFormatException ex)
            {
                serverPortArea.setText("Here must be a number");
                return;
            }

            clientModel = new ClientModel(serverAddress, serverPort);
            clientController = new ClientController(clientModel, clientView);
            clientView.setClientController(clientController);
            clientModel.setClientController(clientController);

            stage.setScene(nextScene);
        });

        hbox.getChildren().addAll(serverAddressArea, serverPortArea);
        root.getChildren().addAll(hbox, submitButton);

        return new Scene(root);
    }
}