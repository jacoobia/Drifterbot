package com.jacoobia.drifterbot.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class GuildUtils
{

    /**
     * Grabs the {@link VoiceChannel} in a Guild that a member is
     * currently active in.
     * @param guild the guild to find the voice channel in
     * @param member the member who we're trying to locate
     * @return either null or a {@link VoiceChannel}
     */
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