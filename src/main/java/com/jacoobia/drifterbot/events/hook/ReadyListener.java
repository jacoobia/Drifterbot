package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.model.channels.audio.AudioFiles;
import com.jacoobia.drifterbot.model.guilds.GuildHandler;
import com.jacoobia.drifterbot.utils.StringUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public class ReadyListener extends ListenerAdapter
{

    @Override
    public void onReady(@NotNull ReadyEvent event)
    {
        for (Guild guild : event.getJDA().getGuilds())
            GuildHandler.registerGuild(guild);
        try
        {
            AudioFiles.addToDings(loadFilesFromDir("/audio/dings"));
            AudioFiles.addToHostiles(loadFilesFromDir("/audio/hostiles"));
            AudioFiles.addToSmallBlockers(loadFilesFromDir("/audio/blocker/small"));
            AudioFiles.addToVex(loadFilesFromDir("/audio/vex"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        StringUtils.print("Bot seeded. Ready for use.");
    }

    private File[] loadFilesFromDir(String dir) throws URISyntaxException
    {
        return new File(Objects.requireNonNull(getClass().getResource(dir)).toURI()).listFiles();
    }

}
