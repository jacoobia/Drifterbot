package com.jacoobia.drifterbot.events.registry;

import com.jacoobia.drifterbot.events.model.EventHandler;
import net.dv8tion.jda.api.events.GenericEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventRegistry
{

    private static final List<EventHandler> eventHandlers = new ArrayList<>();

    public static void registerEvent(EventHandler handler)
    {
        eventHandlers.add(handler);
    }

    public static void fireEvent(@NotNull GenericEvent genericEvent)
    {
        eventHandlers.stream().filter(handler -> handler.instanceOf(genericEvent)).forEach(handler -> handler.handle(genericEvent));
    }

}
