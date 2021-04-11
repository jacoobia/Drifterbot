package com.jacoobia.drifterbot.model.channels.audio;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Audio file cache storing the information about
 * each audio clip ready to be grabbed on demand.
 */
public class AudioFiles
{

    public static final List<String> DING_CLIPS = new ArrayList<>();
    public static final List<String> SMALL_BLOCKER = new ArrayList<>();
    public static final List<String> MEDIUM_BLOCKER = new ArrayList<>();
    public static final List<String> LARGE_BLOCKER = new ArrayList<>();
    public static final List<String> HOSTILES = new ArrayList<>();
    public static final List<String> VEX = new ArrayList<>();
    public static final List<String> INVADE = new ArrayList<>();

    /**
     * Adds a list of file paths to a specific list
     * @param files the files to save the paths for
     * @param list the list to save the file paths to
     */
    public static void addToFileList(List<String > files, List<String> list)
    {
        CollectionUtils.addAll(list, files);
    }

    /**
     * Gets a random clip from a specific list
     * @param list the list to get the clip from
     * @return a String path to an audio file
     */
    public static String randomAudioFile(List<String> list)
    {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

}