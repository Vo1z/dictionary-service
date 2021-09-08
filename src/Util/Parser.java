package Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Parser
{
    public static Map<String, String> parseDictionary(Path pathToFile)
    {
        var translatedWords = new HashMap<String, String>();
        var unknownWordPattern = Pattern.compile("(\\D+\\s*)+\\s*-");
        var translationWordPattern = Pattern.compile("-\\s+(\\D+\\s*)+");

        try
        {
            Files.lines(pathToFile)
                    .forEach(line ->
                    {
                        var modifiedLine = line.toLowerCase().trim();
                        var matcher = unknownWordPattern.matcher(modifiedLine);
                        var unknownWord = "";
                        var translatedWord = "";

                        while (matcher.find())
                            unknownWord += matcher.group();
                        unknownWord = unknownWord.trim().replaceAll("\\s*-\\s*", "");

                        matcher = translationWordPattern.matcher(modifiedLine);
                        while (matcher.find())
                            translatedWord += matcher.group();
                        translatedWord = translatedWord.trim().replaceAll("\\s*-\\s*", "");

                        translatedWords.put(unknownWord, translatedWord);
                    });
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

        return translatedWords;
    }
}