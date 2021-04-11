package com.jacoobia.drifterbot.model.guilds;

import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

public class GuildHandler
{

    private static final Map<String, DrifterGuild> GUILD_REGISTRY = new HashMap<>();

    public static DrifterGuild getGuild(String id)
    {
        return GUILD_REGISTRY.get(id);
    }

    public static void registerGuild(Guild guild)
    {
        DrifterGuild drifterGuild = new DrifterGuild();
        drifterGuild.setGuildId(guild.getId());
        drifterGuild.setGuild(guild);
        drifterGuild.createAudioChannel(guild);
        GUILD_REGISTRY.put(guild.getId(), drifterGuild);
    }

    public static void deregisterGuild(String id)
    {
        GUILD_REGISTRY.remove(id);
    }

}
