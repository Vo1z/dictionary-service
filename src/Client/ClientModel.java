package Client;

import Util.Language;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientModel
{
    private ClientController clientController;
    public final String serverAddress;
    public final int serverPort;

    private String inputWord;
    private String translation;
    private Language selectedLanguage;

    public ClientModel(String serverAddress, int serverPort)
    {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void setInputWord(String inputWord)
    {
        this.inputWord = inputWord;
    }

    public void setSelectedLanguage(Language selectedLanguage)
    {
        this.selectedLanguage = selectedLanguage;
    }

    public String getTranslation()
    {
        return translation;
    }

    public void askForTranslation()
    {
        try
        {
            var socket = new Socket(serverAddress, serverPort);
            var out = new PrintWriter(socket.getOutputStream());
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(inputWord);
            out.println(Language.valueOf(selectedLanguage));
            out.flush();

            translation = in.readLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setClientController(ClientController clientController)
    {
        this.clientController = clientController;
    }
}