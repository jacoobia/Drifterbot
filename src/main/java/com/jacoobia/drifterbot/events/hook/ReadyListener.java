package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.model.channels.audio.AudioFiles;
import com.jacoobia.drifterbot.model.guilds.GuildRegister;
import com.jacoobia.drifterbot.utils.ResourceScanner;
import com.jacoobia.drifterbot.utils.StringUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * The ready listener to listen for the {@link ReadyEvent}
 */
public class ReadyListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        for (Guild guild : event.getJDA().getGuilds())
            GuildRegister.registerGuild(guild);
        try {
            ResourceScanner scanner = new ResourceScanner();

            AudioFiles.addToFileList(scanner.scanPath("audio/dings"), AudioFiles.DING_CLIPS);
            AudioFiles.addToFileList(scanner.scanPath("audio/hostiles"), AudioFiles.HOSTILES);
            AudioFiles.addToFileList(scanner.scanPath("audio/blocker/small"), AudioFiles.SMALL_BLOCKER);
            AudioFiles.addToFileList(scanner.scanPath("audio/blocker/medium"), AudioFiles.MEDIUM_BLOCKER);
            AudioFiles.addToFileList(scanner.scanPath("audio/blocker/large"), AudioFiles.LARGE_BLOCKER);
            AudioFiles.addToFileList(scanner.scanPath("audio/vex"), AudioFiles.VEX);
            AudioFiles.addToFileList(scanner.scanPath("audio/invade"), AudioFiles.INVADE);

        } catch (Exception e) {
            e.printStackTrace();
        }
        StringUtils.print("Bot seeded. Ready for use.");
    }

}
