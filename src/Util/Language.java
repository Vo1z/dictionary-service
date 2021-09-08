package Util;

public enum Language
{
    French,
    English,
    German;

    public static Language getLanguageById(int id)
    {
        switch (id)
        {
            case 1:
                return French;
            case 2:
                return German;
            case 3:
                return English;
        }

        return null;
    }

    public static int valueOf(Language language)
    {
        switch (language)
        {
            case French:
                return 1;
            case German:
                return 2;
            case English:
                return 3;
        }

        return 0;
    }
}
