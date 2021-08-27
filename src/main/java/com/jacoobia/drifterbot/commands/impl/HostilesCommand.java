package com.jacoobia.drifterbot.commands.impl;

import com.jacoobia.drifterbot.commands.Command;
import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.model.channels.audio.AudioFiles;
import com.jacoobia.drifterbot.model.guilds.DrifterGuild;
import com.jacoobia.drifterbot.model.guilds.GuildRegister;
import com.jacoobia.drifterbot.utils.StringUtils;
import net.dv8tion.jda.api.entities.VoiceChannel;

/**
 * A command to play a random "hostiles" audio clip
 */
public class HostilesCommand implements CommandProcessor {

    @Override
    public void process(Command command) {
        VoiceChannel voiceChannel = command.getVoiceChannel();
        if(voiceChannel != null) {
            String clip = AudioFiles.randomAudioFile(AudioFiles.HOSTILES);
            DrifterGuild guild = GuildRegister.getGuild(command.getGuild().getId());
            guild.queueClip(clip, voiceChannel);
        }
    }

    @Override
    public boolean relevantCommand(String command) {
        return StringUtils.anyEqualsIgnoreCase(command, "hostiles", "enemies");
    }

}
