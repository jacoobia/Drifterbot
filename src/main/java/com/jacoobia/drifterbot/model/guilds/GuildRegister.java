package com.jacoobia.drifterbot.model.guilds;

import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

/**
 * The guild register creates and stores {@link DrifterGuild} instances
 * for each {@link Guild} the bot is a part of, this allows the bot to
 * work for multiple servers at once.
 */
public class GuildRegister
{

    /**
     * The registry for guilds
     */
    private static final Map<String, DrifterGuild> GUILD_REGISTRY = new HashMap<>();

    /**
     * Gets the {@link DrifterGuild} object for a specific discord guild
     * @param id the ID of the discord guild
     * @return null or {@link DrifterGuild} object
     */
    public static DrifterGuild getGuild(String id)
    {
        return GUILD_REGISTRY.get(id);
    }

    /**
     * Creates a {@link DrifterGuild} object for a discord guild
     * and stores it in our registry to be used later down the line.
     * @param guild the guild to register.
     */
    public static void registerGuild(Guild guild)
    {
        DrifterGuild drifterGuild = new DrifterGuild();
        drifterGuild.setGuildId(guild.getId());
        drifterGuild.setGuild(guild);
        drifterGuild.createAudioChannel(guild);
        GUILD_REGISTRY.put(guild.getId(), drifterGuild);
    }

    /**
     * Removes a guild from our registry, for example if the bot
     * was to be kicked from the server.
     * @param id the ID of the discord guild to deregister
     */
    public static void deregisterGuild(String id)
    {
        GUILD_REGISTRY.remove(id);
    }

}
