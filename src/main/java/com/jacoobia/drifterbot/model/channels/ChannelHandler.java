package com.jacoobia.drifterbot.model.channels;

import com.jacoobia.drifterbot.model.channels.audio.AudioSender;
import com.jacoobia.drifterbot.model.guilds.DrifterGuild;
import com.jacoobia.drifterbot.model.guilds.GuildHandler;
import com.jacoobia.drifterbot.utils.GuildUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class ChannelHandler
{

    public static boolean connect(VoiceChannel channel)
    {
        Guild guild = channel.getGuild();
        if (GuildUtils.getMemberVoiceChannel(guild, guild.getSelfMember()) == null)
        {
            DrifterGuild drifterGuild = GuildHandler.getGuild(guild.getId());
            AudioManager audioManager = guild.getAudioManager();
            AudioSender sender = new AudioSender(drifterGuild.getPlayer());

            audioManager.setSendingHandler(sender);
            audioManager.openAudioConnection(channel);
            return true;
        }
        return false;
    }

    public static void disconnect(Guild guild)
    {
        AudioManager audioManager = guild.getAudioManager();
        audioManager.closeAudioConnection();
    }

}
