package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.events.registry.EventRegistry;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class EventHook implements EventListener
{
    @Override
    public void onEvent(@NotNull GenericEvent genericEvent)
    {
        EventRegistry.fireEvent(genericEvent);
    }
}
