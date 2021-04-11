package com.jacoobia.drifterbot.model.channels.audio;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AudioFiles
{

    private static final List<String> DING_CLIPS = new ArrayList<>();
    private static final List<String> SMALL_BLOCKER = new ArrayList<>();
    private static final List<String> HOSTILES = new ArrayList<>();
    private static final List<String> VEX = new ArrayList<>();

    public static void addToDings(File[] files)
    {
        Arrays.stream(files).map(File::getPath).forEach(DING_CLIPS::add);
    }

    public static void addToSmallBlockers(File[] files)
    {
        Arrays.stream(files).map(File::getPath).forEach(SMALL_BLOCKER::add);
    }

    public static void addToHostiles(File[] files)
    {
        Arrays.stream(files).map(File::getPath).forEach(HOSTILES::add);
    }

    public static void addToVex(File[] files)
    {
        Arrays.stream(files).map(File::getPath).forEach(VEX::add);
    }

    public static String randomDing()
    {
        Random random = new Random();
        int index = random.nextInt(DING_CLIPS.size());
        return DING_CLIPS.get(index);
    }

    public static String randomSmall()
    {
        Random random = new Random();
        int index = random.nextInt(SMALL_BLOCKER.size());
        return SMALL_BLOCKER.get(index);
    }

}