package com.jacoobia.drifterbot.model.channels;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;

import java.util.Arrays;

/**
 * A handler class to send messages to certain messaging channels,
 * prebuilt, embedded and generic messages all supported.
 */
@SuppressWarnings("unused")
public class MessageHandler {

    /**
     * Sends a generic string message to a {@link MessageChannel}
     * @param channel the channel to send the message in
     * @param message the message to send to the channel
     */
    public static void sendMessage(MessageChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    /**
     * Sends multiple generic string messages to a {@link MessageChannel}
     * @param channel the channel to send the messages to
     * @param messages the messages to send to the channel
     */
    public static void sendMessages(MessageChannel channel, String... messages) {
        Arrays.stream(messages).forEach(s -> sendMessage(channel, s));
    }

    /**
     * Sends a generic string message with an attached file payload to a {@link MessageChannel}
     * @param channel the channel to send the message to
     * @param message the message to send to the channel
     * @param title the title for the embedded file message
     * @param payload the payload file data to send
     */
    public static void sendMessageWithFile(MessageChannel channel, String message, String title, String payload) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setImage(payload);
        Message m = new MessageBuilder(message).setEmbed(builder.build()).build();
        channel.sendMessage(m).queue();
    }

    /**
     * Sends a prebuilt embedded message to a {@link MessageChannel}
     * @param channel the channel to send the message to
     * @param message the message to send to the channel
     * @param messageEmbed the prebuilt embedded message to send
     */
    public static void sendPreBuiltMessage(MessageChannel channel, String message, MessageEmbed messageEmbed) {
        String text = message == null ? "" : message;
        Message m = new MessageBuilder(text).setEmbed(messageEmbed).build();
        channel.sendMessage(m).queue();
    }

    /**
     * Sens a private direct message to a user regardless of the guild the bot
     * is a member of. All it requires is a reference to the {@link User}
     * @param user the user to send the message to
     * @param message the generics string message to send
     */
    public static void sendUserMessage(User user, String message) {
        PrivateChannel channel = user.openPrivateChannel().complete();
        sendMessage(channel, message);
    }

    /**
     * Sens a prebuilt embedded private direct message to a user regardless
     * of the guild the bot is a member of. All it requires is a reference to the {@link User}
     * @param user the user to send the message to
     * @param message the generics string message to send
     * @param messageEmbed the prebuilt embedded message to send
     */
    public static void sendUserPreBuiltMessage(User user, String message, MessageEmbed messageEmbed) {
        PrivateChannel channel = user.openPrivateChannel().complete();
        sendPreBuiltMessage(channel, message, messageEmbed);
    }

    /**
     * Splits a message into individual strings
     * @param message the message to split
     * @return a string array of the parts
     */
    public static String[] splitMessage(Message message) {
        return message.getContentRaw().split(" ");
    }

    /**
     * Gets the command name from a send message
     * @param message the command message sent
     * @return the !command name
     */
    public static String getCommand(Message message) {
        return splitMessage(message)[0];
    }

    /**
     * Gets the arguments from a !command message sent
     * @param message the message to extract the arguments from
     * @return a string array of the arguments
     */
    public static String[] getArgs(Message message) {
        String[] args = new String[5];
        String[] split = splitMessage(message);
        for(int i = 1; i < split.length; i++) {
            int argsIndex = i - 1;
            if(argsIndex < 5)
                args[argsIndex] = split[i];
        }
        return args;
    }

    /**
     * Checks if a !command has arguments or not
     * @param message the message command to check
     * @return boolean does the command have arguments
     */
    public static boolean hasArgs(Message message) {
        return message.getContentRaw().split(" ").length > 1;
    }

}
