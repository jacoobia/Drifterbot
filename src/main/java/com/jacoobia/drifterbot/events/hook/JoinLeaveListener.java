package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.model.guilds.GuildRegister;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * A listener class for the {@link GuildJoinEvent} and {@link GuildLeaveEvent}
 */
public class JoinLeaveListener extends ListenerAdapter
{

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event)
    {
        Guild guild = event.getGuild();
        GuildRegister.registerGuild(guild);
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event)
    {
        Guild guild = event.getGuild();
        GuildRegister.deregisterGuild(guild.getId());
    }
}
