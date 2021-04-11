package com.jacoobia.drifterbot.model.channels;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;

public class MessageHandler
{

    public static void sendMessage(MessageChannel channel, String message)
    {
        channel.sendMessage(message).queue();
    }

    public static void sendMessages(MessageChannel channel, String... messages)
    {
        for(String s : messages)
            sendMessage(channel, s);
    }

    public static void sendMessageWithFile(MessageChannel channel, String message, String title, String payload)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setImage(payload);
        Message m = new MessageBuilder(message).setEmbed(builder.build()).build();
        channel.sendMessage(m).queue();
    }

    public static void sendPreBuiltMessage(MessageChannel channel, String message, MessageEmbed messageEmbed)
    {
        String text = message == null ? "" : message;
        Message m = new MessageBuilder(text).setEmbed(messageEmbed).build();
        channel.sendMessage(m).queue();
    }

    public static void sendUserMessage(User user, String message)
    {
        PrivateChannel channel = user.openPrivateChannel().complete();
        sendMessage(channel, message);
    }

    public static void sendUserPreBuiltMessage(User user, String message, MessageEmbed messageEmbed)
    {
        PrivateChannel channel = user.openPrivateChannel().complete();
        sendPreBuiltMessage(channel, message, messageEmbed);
    }

    public static String[] splitMessage(Message message)
    {
        return message.getContentRaw().split(" ");
    }

    public static String getCommand(Message message)
    {
        return splitMessage(message)[0];
    }

    public static String[] getArgs(Message message)
    {
        String[] resized = new String[5];
        if(hasArgs(message))
        {
            String[] full = splitMessage(message);
            for (int i = 0; i < full.length; i++)
            {
                if (i > 0)
                    resized[i - 1] = full[i];
            }
            return resized;
        }
        else return resized;
    }

    public static boolean hasArgs(Message message)
    {
        String[] split = message.getContentRaw().split(" ");
        return message.getContentRaw().split(" ").length > 1;
    }

}
