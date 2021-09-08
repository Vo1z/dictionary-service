package Server;

import Util.Language;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ProcessingSocket implements Runnable
{
    private final Socket receivedSocket;
    private final Translator translator;

    public ProcessingSocket(Socket receivedSocket, Translator translator)
    {
        this.receivedSocket = receivedSocket;
        this.translator = translator;
    }

    @Override
    public void run()
    {
        try
        {
            var out = new PrintWriter(receivedSocket.getOutputStream());
            var in = new BufferedReader(new InputStreamReader(receivedSocket.getInputStream()));

            var incomeWord = in.readLine();
            var incomeLanguage = Language.getLanguageById(Integer.parseInt(in.readLine()));

            var respond = translator.getDictionary(incomeLanguage).get(incomeWord);
            out.println(Objects.requireNonNullElse(respond, "No such word"));
            out.flush();

            receivedSocket.close();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
}