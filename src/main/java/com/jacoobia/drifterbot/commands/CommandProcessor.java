package com.jacoobia.drifterbot.commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public interface CommandProcessor
{
    void process(MessageChannel channel, String name, String[] args, User author);
    boolean relevantCommand(String command);
}