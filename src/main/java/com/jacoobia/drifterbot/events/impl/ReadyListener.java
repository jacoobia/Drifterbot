package com.jacoobia.drifterbot.events.impl;

import com.jacoobia.drifterbot.Drifter;
import com.jacoobia.drifterbot.events.model.EventHandler;
import com.jacoobia.drifterbot.model.guild.GuildHandler;
import com.jacoobia.drifterbot.utils.StringUtils;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import org.jetbrains.annotations.NotNull;

public class ReadyListener implements EventHandler
{

    @Override
    public void handle(GenericEvent event)
    {
        ReadyEvent readyEvent = (ReadyEvent) event;
        GuildHandler.seed(Drifter.guildId);
        StringUtils.print("Bot seeded. Ready for use.");
    }

    @Override
    public boolean instanceOf(@NotNull GenericEvent genericEvent)
    {
        return genericEvent instanceof ReadyEvent;
    }
}