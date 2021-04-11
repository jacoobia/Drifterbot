package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.commands.impl.BankCommand;
import com.jacoobia.drifterbot.commands.Command;
import com.jacoobia.drifterbot.commands.impl.DingCommand;
import com.jacoobia.drifterbot.commands.impl.MetricsCommand;
import com.jacoobia.drifterbot.model.channels.MessageHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MessageListener extends ListenerAdapter
{

    private static final ArrayList<CommandProcessor> commandProcessors = new ArrayList<>();

    static
    {
        commandProcessors.add(new DingCommand());
        commandProcessors.add(new MetricsCommand());
        commandProcessors.add(new BankCommand());
    }

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