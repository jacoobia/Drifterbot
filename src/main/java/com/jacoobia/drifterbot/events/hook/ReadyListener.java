package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.model.channels.audio.AudioFiles;
import com.jacoobia.drifterbot.model.guilds.GuildRegister;
import com.jacoobia.drifterbot.utils.StringUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * The ready listener to listen for the {@link ReadyEvent}
 */
public class ReadyListener extends ListenerAdapter
{

    @Override
    public void onReady(@NotNull ReadyEvent event)
    {
        for (Guild guild : event.getJDA().getGuilds())
            GuildRegister.registerGuild(guild);
        try
        {
            AudioFiles.addToFileList(loadFilesFromDir("/audio/dings"), AudioFiles.DING_CLIPS);
            AudioFiles.addToFileList(loadFilesFromDir("/audio/hostiles"), AudioFiles.HOSTILES);
            AudioFiles.addToFileList(loadFilesFromDir("/audio/blocker/small"), AudioFiles.SMALL_BLOCKER);
            AudioFiles.addToFileList(loadFilesFromDir("/audio/vex"), AudioFiles.VEX);

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
