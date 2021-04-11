package com.jacoobia.drifterbot.commands;

public interface CommandProcessor
{
    void process(Command command);
    boolean relevantCommand(String command);
}