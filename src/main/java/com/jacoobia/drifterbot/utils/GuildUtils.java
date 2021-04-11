package com.jacoobia.drifterbot.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class GuildUtils
{

    public static Member memberFromUser(Guild guild, User user)
    {
        return guild.getMemberById(user.getId());
    }

    public static VoiceChannel getUserVoiceChannel(Guild guild, User user)
    {
        return getMemberVoiceChannel(guild, memberFromUser(guild, user));
    }

    public static VoiceChannel getMemberVoiceChannel(Guild guild, Member member)
    {
        for(VoiceChannel channel : guild.getVoiceChannels())
        {
            if(channel.getMembers().contains(member))
                return channel;
        }
        return null;
    }

}