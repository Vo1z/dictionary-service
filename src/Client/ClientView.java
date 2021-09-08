package Client;

import Util.Language;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClientView
{
    public final Scene scene;

    private ClientController clientController;

    private Pane root;

    private TextArea inputArea;
    private TextArea outputArea;

    private ComboBox<Language> languages;

    private Button submitButton;

    public ClientView()
    {
        root = createRootPane();
        scene = new Scene(root);
    }

    private Pane createRootPane()
    {
        var createdRoot = new VBox();
        var inputPane = new HBox();

        inputArea = new TextArea();
        outputArea = new TextArea();
        languages = new ComboBox<>();
        submitButton = new Button("Submit");

        inputArea.setPrefHeight(40);
        inputArea.setPrefWidth(540);
        outputArea.setPrefHeight(40);
        outputArea.setPrefWidth(540);
        outputArea.setEditable(false);

        submitButton.setPrefHeight(40);
        submitButton.setPrefWidth(1080);
        submitButton.setOnAction(event -> clientController.translate());

        for(var language : Language.values())
            languages.getItems().add(language);
        languages.getSelectionModel().selectFirst();
        languages.setPrefWidth(1080);

        inputPane.getChildren().addAll(inputArea, outputArea);
        createdRoot.getChildren().addAll(inputPane, languages, submitButton);

        return createdRoot;
    }

    public void setClientController(ClientController clientController)
    {
        this.clientController = clientController;
    }

    public void setOutputWord(String content)
    {
        outputArea.setText(content);
    }

    public String getInputWord()
    {
        return inputArea.getText();
    }

    public Language getSelectedLanguage()
    {
        return languages.getValue();
    }
}
