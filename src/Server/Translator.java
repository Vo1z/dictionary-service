package Server;

import Util.Language;

import java.util.HashMap;
import java.util.Map;

public class Translator
{
    private Map<String, String> french;
    private Map<String, String> english;
    private Map<String, String> german;

    private Map<Language, Map<String, String>> dictionaries = new HashMap<>();

    public Translator(Map<String, String> english, Map<String, String> french, Map<String, String> german)
    {
        this.french = french;
        this.english = english;
        this.german = german;

        dictionaries.put(Language.French, french);
        dictionaries.put(Language.English, english);
        dictionaries.put(Language.German, german);
    }

    public Map<String, String> getDictionary(Language language)
    {
        return switch (language)
                {
                    case French -> french;
                    case English -> english;
                    case German -> german;
                };
    }
}