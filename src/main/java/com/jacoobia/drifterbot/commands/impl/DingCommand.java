package com.jacoobia.drifterbot.commands.impl;

import com.jacoobia.drifterbot.Drifter;
import com.jacoobia.drifterbot.commands.Command;
import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.database.SessionFactory;
import com.jacoobia.drifterbot.database.entity.Metrics;
import com.jacoobia.drifterbot.database.mapper.MetricsMapper;
import com.jacoobia.drifterbot.model.MetricsDictionary;
import com.jacoobia.drifterbot.model.channels.ChannelHandler;
import com.jacoobia.drifterbot.model.channels.audio.AudioFiles;
import com.jacoobia.drifterbot.model.guilds.DrifterGuild;
import com.jacoobia.drifterbot.model.guilds.GuildHandler;
import com.jacoobia.drifterbot.utils.GuildUtils;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.apache.ibatis.session.SqlSession;

public class DingCommand implements CommandProcessor
{

    private static final String COMMAND_NAME = "ding";

    @Override
    public void process(Command command)
    {
        VoiceChannel voiceChannel = GuildUtils.getMemberVoiceChannel(command.getGuild(), command.getMember());
        if(voiceChannel != null)
        {
            String clip = AudioFiles.randomDing();
            DrifterGuild guild = GuildHandler.getGuild(command.getGuild().getId());
            ChannelHandler.connect(voiceChannel);
            guild.queueClip(clip);
            incrementTotal();
        }
    }

    @Override
    public boolean relevantCommand(String command)
    {
        return command.equals(COMMAND_NAME) || command.startsWith(COMMAND_NAME);
    }

    private void incrementTotal()
    {
        SqlSession session = Drifter.sessionFactory.getSession();
        MetricsMapper mapper = SessionFactory.getMetricsMapper(session);
        Metrics total = mapper.findById(MetricsDictionary.TOTAL_DINGS.getId());
        total.setValue(total.getValue() + 1);
        mapper.update(total);
        session.commit();
        session.close();
    }

}
