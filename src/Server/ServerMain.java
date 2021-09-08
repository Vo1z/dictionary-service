package Server;

import Util.Parser;

import java.nio.file.Path;

public class ServerMain
{
    public static void main(String[] args) throws InterruptedException
    {
        var polishToEnglish = Parser.parseDictionary(Path.of("./res/English"));
        var polishToFrench = Parser.parseDictionary(Path.of("./res/French"));
        var polishToGerman = Parser.parseDictionary(Path.of("./res/German"));

        var translator = new Translator(polishToEnglish, polishToFrench, polishToGerman);

        var server = new Server(translator, 0);
        server.start();

        server.join();
    }
}