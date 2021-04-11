package com.jacoobia.drifterbot.commands.impl;

import com.jacoobia.drifterbot.Drifter;
import com.jacoobia.drifterbot.commands.Command;
import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.database.SessionFactory;
import com.jacoobia.drifterbot.database.entity.Metrics;
import com.jacoobia.drifterbot.database.mapper.MetricsMapper;
import com.jacoobia.drifterbot.model.channels.MessageHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import org.apache.ibatis.session.SqlSession;

import java.awt.*;
import java.util.List;

public class MetricsCommand implements CommandProcessor
{
    @Override
    public void process(Command command)
    {
        SqlSession session = Drifter.sessionFactory.getSession();
        MetricsMapper mapper = SessionFactory.getMetricsMapper(session);
        List<Metrics> metrics = mapper.findAll();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE);
        builder.setTitle("**Metrics!**");

        for(Metrics metric : metrics) {
            builder.appendDescription("**" + metric.getName() + ":** " + metric.getValue());
            int indexOf = metrics.indexOf(metric);
            if(indexOf < metrics.size())
                builder.appendDescription("\n");
        }

        MessageHandler.sendPreBuiltMessage(command.getChannel(), "Looking up the metrics...", builder.build());
    }

    @Override
    public boolean relevantCommand(String command)
    {
        return command.equalsIgnoreCase("metrics");
    }
}
