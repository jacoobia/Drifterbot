package com.jacoobia.drifterbot.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

@Getter
@Setter
@AllArgsConstructor
public class Command
{

    private Guild guild;
    private MessageChannel channel;
    private Member member;
    private String name;
    private String[] args;

}
