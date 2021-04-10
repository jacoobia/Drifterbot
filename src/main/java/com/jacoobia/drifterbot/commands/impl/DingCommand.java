package com.jacoobia.drifterbot.commands.impl;

import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.model.guild.GuildHandler;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class DingCommand implements CommandProcessor
{

    private static final String COMMAND_NAME = "!ding";

    @Override
    public void process(MessageChannel channel, String name, String[] args, User author)
    {
        Member member = GuildHandler.getMemberByID(author.getId());
        VoiceChannel connectedChannel = GuildHandler.getVoiceChannelMemberIsIn(member);

        /*

        if(message.equals("!join")) {
            // Checks if the bot has permissions.
            if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
                // The bot does not have permission to join any voice channel. Don't forget the .queue()!
                channel.sendMessage("I do not have permissions to join a voice channel!").queue();
                return;
            }
            // Creates a variable equal to the channel that the user is in.
            VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
            // Checks if they are in a channel -- not being in a channel means that the variable = null.
            if(connectedChannel == null) {
                // Don't forget to .queue()!
                channel.sendMessage("You are not connected to a voice channel!").queue();
                return;
            }
            // Gets the audio manager.
            AudioManager audioManager = event.getGuild().getAudioManager();
            // When somebody really needs to chill.
            if(audioManager.isAttemptingToConnect()) {
                channel.sendMessage("The bot is already trying to connect! Enter the chill zone!").queue();
                return;
            }
            // Connects to the channel.
            audioManager.openAudioConnection(connectedChannel);
            // Obviously people do not notice someone/something connecting.
            channel.sendMessage("Connected to the voice channel!").queue();
        } else if(message.equals("!leave")) { // Checks if the command is !leave.        }
        */
    }

    @Override
    public boolean relevantCommand(String command)
    {
        return command.equals(COMMAND_NAME) || command.startsWith(COMMAND_NAME);
    }

}
