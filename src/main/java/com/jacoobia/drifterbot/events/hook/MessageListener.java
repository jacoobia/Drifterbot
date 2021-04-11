package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.commands.impl.*;
import com.jacoobia.drifterbot.commands.Command;
import com.jacoobia.drifterbot.model.channels.MessageHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * The message listener to listen to whatever type of messages are sent.
 * Currently only the {@link GuildMessageReceivedEvent} is implemented since
 * this bot only works with server commands not DM commands.
 */
public class MessageListener extends ListenerAdapter
{

    private static final ArrayList<CommandProcessor> commandProcessors = new ArrayList<>();

    /* Register our command processors here! */
    static
    {
        commandProcessors.add(new DingCommand());
        commandProcessors.add(new MetricsCommand());
        commandProcessors.add(new BankCommand());
        commandProcessors.add(new HostilesCommand());
        commandProcessors.add(new VexCommand());
        commandProcessors.add(new InvadeCommand());
    }

    /**
     * Loop through each of the command processors and find the relevant
     * one for the command that was used and process the command.
     * @param command the command arguments object to process
     */
    private void processCommands(Command command)
    {
        for(CommandProcessor processor : commandProcessors)
        {
            if(processor.relevantCommand(command.getName()))
                processor.process(command);
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
    {
        if(event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String command = MessageHandler.getCommand(message);
        String[] args = MessageHandler.getArgs(message);
        if(command.startsWith("!"))
        {
            String[] split = command.split("!");
            processCommands(new Command(event.getGuild(), event.getChannel(), event.getMember(), split[1], args));
        }
    }


}