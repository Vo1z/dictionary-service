package Client;

public class ClientController
{
    public final ClientModel clientModel;
    public final ClientView clientView;

    public ClientController(ClientModel clientModel, ClientView clientView)
    {
        this.clientModel = clientModel;
        this.clientView = clientView;
    }

    public void translate()
    {
        clientModel.setInputWord(clientView.getInputWord().toLowerCase());
        clientModel.setSelectedLanguage(clientView.getSelectedLanguage());
        clientModel.askForTranslation();

        clientView.setOutputWord(clientModel.getTranslation());
    }
}