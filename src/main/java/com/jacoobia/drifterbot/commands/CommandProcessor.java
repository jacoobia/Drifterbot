package com.jacoobia.drifterbot.commands;

/**
 * The pattern for the command processor classes
 */
public interface CommandProcessor
{

    /**
     * Process a command based on the command params wrapped in
     * a {@link Command} object.
     * @param command the command param object
     */
    void process(Command command);

    /**
     * Checks if the command that was ran is a relevant command for
     * this command processor.
     * @param command the command name
     * @return boolean was the correct command ran
     */
    boolean relevantCommand(String command);
}