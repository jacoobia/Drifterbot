package com.jacoobia.drifterbot.utils;

import com.jacoobia.drifterbot.Drifter;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourceScanner
{

    /**
     * Checks if it's ran in the dev env or if it's deployed as a jar then
     * dynamically picks what location to load the audio resources from
     * @param path the sub-path for the audio files
     * @return a list of file directories
     */
    public List<String> scanPath(String path)
    {
        List<String> paths = new ArrayList<>();
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        if(jarFile.isFile())
        {
            final File folder = new File(jarFile.getParent() + "/" + path);
            System.out.println(folder.getPath());
            for(File file : Objects.requireNonNull(folder.listFiles()))
                paths.add(file.getPath());
        }
        else
        {
            final URL url = Drifter.class.getResource("/" + path);
            if (url != null)
            {
                try
                {
                    final File apps = new File(url.toURI());
                    for (File app : Objects.requireNonNull(apps.listFiles()))
                        paths.add(app.getPath());
                }
                catch (URISyntaxException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return paths;
    }
}
