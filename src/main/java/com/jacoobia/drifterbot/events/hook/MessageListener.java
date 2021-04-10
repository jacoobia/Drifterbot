package com.jacoobia.drifterbot.events.hook;

import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.commands.impl.DingCommand;
import com.jacoobia.drifterbot.model.MessageHandler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
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
    }

    private void processCommands(MessageChannel channel, String command, String[] args, User author)
    {
        for(CommandProcessor processor : commandProcessors)
        {
            if(processor.relevantCommand(command))
                processor.process(channel, command, args, author);
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
            processCommands(event.getChannel(), command, args, event.getAuthor());
    }


}