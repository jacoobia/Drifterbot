package com.jacoobia.drifterbot.utils;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A universal portable class that I use to manage strings in different ways,
 * updated to include some blank checks.
 */
public class StringUtils
{

    public static String NEW_LINE = "\n";

    public static boolean isEmpty(String string)
    {
        return string == null || string.isEmpty() || isBlank(string);
    }

    public static boolean isBlank(String string)
    {
        String[] arr = string.split("(?!^)");
        int length = string.length();
        int blanks = (int) Arrays.stream(arr).filter(" "::equals).count();
        return blanks == length;
    }

    /**
     * Prints a message to the console without colour control
     */
    public static void print(String message)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:SS");
        String prefix = sdf.format(new Date());
        System.out.println("[" + prefix + "]: " + message);
    }

    /**
     * Takes in a bunch of messages and then prints them
     * one by one to the console.
     *
     * @param messages the messages you wanna print
     */
    public static void printMultiLine(String... messages)
    {
        Arrays.stream(messages).forEach(StringUtils::print);
    }

    /**
     * Converts string input into int amount to be parsed
     * Example usage would be withdrawing 100k of an item
     * from your bank. Converts to 100000 then you can parse
     * it safely.
     *
     * @param s string input
     * @return our converted string
     */
    public static String replaceNumberShortcuts(String s)
    {
        final String B = "000000000";
        final String M = "000000";
        final String K = "000";

        String output = s;
        if (s.contains("max")) output = output.replaceAll("max", String.valueOf(Integer.MAX_VALUE));
        if (s.contains("b")) output = output.replaceAll("b", B);
        if (s.contains("m")) output = output.replaceAll("m", M);
        if (s.contains("k")) output = output.replaceAll("k", K);

        return output;
    }

    /**
     * Converts a byte buffer to a string for parsing/reading
     */
    public static synchronized String bufferToString(ByteBuffer in)
    {
        StringBuilder builder = new StringBuilder();
        byte date;
        while ((date = in.get()) != 10)
        {
            builder.append((char) date);
        }
        return builder.toString();
    }

    /**
     * Capitalize the first letter of each word in a string
     */
    public static String capitalize(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if (i == 0)
            {
                s = String.format("%s%s",
                        Character.toUpperCase(s.charAt(0)),
                        s.substring(1));
            }
            if (!Character.isLetterOrDigit(s.charAt(i)))
            {
                if (i + 1 < s.length())
                {
                    s = String.format("%s%s%s",
                            s.subSequence(0, i + 1),
                            Character.toUpperCase(s.charAt(i + 1)),
                            s.substring(i + 2));
                }
            }
        }
        return s;
    }

    /**
     * Parses a string to an int safely
     *
     * @param s string input
     * @return integer output
     */
    public static int parseInt(String s)
    {
        int i = 0;
        try
        {
            String parse = replaceNumberShortcuts(s);
            i = Integer.parseInt(parse);
        } catch (Exception e)
        {
            print("Input was not strictly an int string.");
        }
        return i;
    }

    /**
     * Converts string list to a single string with spacing.
     *
     * @param in list of strings
     * @return a built string
     */
    public static String listToString(List<String> in)
    {
        StringBuilder out = new StringBuilder();
        for (String s : in)
        {
            out.append(s);
            if (in.indexOf(s) != (in.size() - 1))
                out.append(" ");
        }
        return out.toString();
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e)
        {
            return false;
        }
    }

}