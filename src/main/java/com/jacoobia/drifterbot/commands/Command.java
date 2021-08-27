package com.jacoobia.drifterbot.commands;

import com.jacoobia.drifterbot.utils.GuildUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

/**
 * An entity of our command arguments and a few small
 * operations based on this data.
 */
@Getter
@Setter
@AllArgsConstructor
public class Command {

    private Guild guild;
    private MessageChannel channel;
    private Member member;
    private String name;
    private String[] args;

    public VoiceChannel getVoiceChannel() {
        return GuildUtils.getMemberVoiceChannel(guild, member);
    }

}
