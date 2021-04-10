package com.jacoobia.drifterbot.events.model;

import net.dv8tion.jda.api.events.GenericEvent;
import org.jetbrains.annotations.NotNull;

public interface EventHandler
{
    void handle(GenericEvent event);

    boolean instanceOf(@NotNull GenericEvent genericEvent);
}