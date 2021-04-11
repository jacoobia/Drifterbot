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

public class BankCommand implements CommandProcessor
{

    private static final int SMALL_BLOCKER = 5;
    private static final int MEDIUM_BLOCKER = 10;
    private static final int LARGE_BLOCKER = 15;

    @Override
    public void process(Command command)
    {
        String arg = command.getArgs()[0];
        try {
            int count = Integer.parseInt(arg);
            String clip = null;

            if(SMALL_BLOCKER == count)
                clip = AudioFiles.randomSmall();
            else if(MEDIUM_BLOCKER == count)
                clip = AudioFiles.randomSmall();
            else if(LARGE_BLOCKER == count)
                clip = AudioFiles.randomSmall();

            if(clip != null)
            {
                queueClip(command, clip);
                updateMotes(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean relevantCommand(String command)
    {
        return command.equalsIgnoreCase("bank");
    }

    private void queueClip(Command command, String clip)
    {
        VoiceChannel voiceChannel = GuildUtils.getMemberVoiceChannel(command.getGuild(), command.getMember());
        if(voiceChannel != null)
        {
            DrifterGuild guild = GuildHandler.getGuild(command.getGuild().getId());
            ChannelHandler.connect(voiceChannel);
            guild.queueClip(clip);
        }
    }

    private void updateMotes(int count)
    {
        SqlSession session = Drifter.sessionFactory.getSession();
        MetricsMapper mapper = SessionFactory.getMetricsMapper(session);
        Metrics total = mapper.findById(MetricsDictionary.MOTES_BANKED.getId());
        total.setValue(total.getValue() + count);
        mapper.update(total);
        session.commit();
        session.close();
    }

}