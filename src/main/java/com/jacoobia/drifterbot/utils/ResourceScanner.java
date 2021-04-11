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

    public List<String > scanPath(String path)
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
