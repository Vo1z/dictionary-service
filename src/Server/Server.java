package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread
{
    private Translator translator;
    private boolean isWorking = false;

    public final int port;

    public Server(Translator translator, int port)
    {
        this.translator = translator;
        this.port = port;
    }

    @Override
    public void run()
    {
        isWorking = true;

        try
        {
            var serverSocket = new ServerSocket(port);

            System.out.println("Server is working on such port: " + serverSocket.getLocalPort());

            while(isWorking)
            {
                var incomeSocket = serverSocket.accept();

                (new Thread(new ProcessingSocket(incomeSocket, translator))).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}