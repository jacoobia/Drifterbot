package com.jacoobia.drifterbot.model.channels;

import com.jacoobia.drifterbot.model.channels.audio.AudioSender;
import com.jacoobia.drifterbot.model.guilds.DrifterGuild;
import com.jacoobia.drifterbot.model.guilds.GuildRegister;
import com.jacoobia.drifterbot.utils.GuildUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

/**
 * Handles joining and leaving of voice channels for the bot,
 * also opens and closes the audio sending channel for whatever
 * guild the bot is joining or leaving a channel in.
 */
public class ChannelHandler {

    /**
     * Connects the bot to a voice channel and opens the audio sending
     * channel for that guild
     * @param channel the channel to connect to
     */
    public static void connect(VoiceChannel channel) {
        Guild guild = channel.getGuild();
        if (GuildUtils.getMemberVoiceChannel(guild, guild.getSelfMember()) == null) {
            DrifterGuild drifterGuild = GuildRegister.getGuild(guild.getId());
            AudioManager audioManager = guild.getAudioManager();
            AudioSender sender = new AudioSender(drifterGuild.getPlayer());

            audioManager.setSendingHandler(sender);
            audioManager.openAudioConnection(channel);
        }
    }

    /**
     * Disconnects the bot from any channel in a guild and closes the
     * audio sending channel
     * @param guild the guild to disconnect from
     */
    public static void disconnect(Guild guild) {
        AudioManager audioManager = guild.getAudioManager();
        audioManager.closeAudioConnection();
    }

}
