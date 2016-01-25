package ru.mail.utils;

/**
 * Created by cqi on 25.1.16.
 * Educate. Grow. Satan.
 */
public class OsType
{
    private static String OS = null;
    public static String getOsName()
    {
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS;
    }
    public static boolean isWindows()
    {
        return getOsName().startsWith("Windows");
    }

    public static boolean isUnix()
    {
        return getOsName().startsWith("Linux");
    }
}
