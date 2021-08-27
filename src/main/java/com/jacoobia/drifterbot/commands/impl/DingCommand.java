package com.jacoobia.drifterbot.commands.impl;

import com.jacoobia.drifterbot.Drifter;
import com.jacoobia.drifterbot.commands.Command;
import com.jacoobia.drifterbot.commands.CommandProcessor;
import com.jacoobia.drifterbot.database.SessionFactory;
import com.jacoobia.drifterbot.database.entity.Metrics;
import com.jacoobia.drifterbot.database.mapper.MetricsMapper;
import com.jacoobia.drifterbot.model.MetricsDictionary;
import com.jacoobia.drifterbot.model.channels.audio.AudioFiles;
import com.jacoobia.drifterbot.model.guilds.DrifterGuild;
import com.jacoobia.drifterbot.model.guilds.GuildRegister;
import net.dv8tion.jda.api.entities.VoiceChannel;
import org.apache.ibatis.session.SqlSession;

/**
 * A command to play a random "ding" audio clip and increase the total
 * count of audio clips in the metrics table
 */
public class DingCommand implements CommandProcessor {

    private static final String COMMAND_NAME = "ding";

    @Override
    public void process(Command command) {
        VoiceChannel voiceChannel = command.getVoiceChannel();
        if(voiceChannel != null) {
            String clip = AudioFiles.randomAudioFile(AudioFiles.DING_CLIPS);
            DrifterGuild guild = GuildRegister.getGuild(command.getGuild().getId());
            guild.queueClip(clip, voiceChannel);
            incrementTotal();
        }
    }

    @Override
    public boolean relevantCommand(String command) {
        return command.startsWith(COMMAND_NAME);
    }

    /**
     * Load the current total number of dings to date,
     * increment it by 1 and then save it again
     */
    private void incrementTotal() {
        SqlSession session = Drifter.sessionFactory.getSession();
        MetricsMapper mapper = SessionFactory.getMetricsMapper(session);
        Metrics total = mapper.findById(MetricsDictionary.TOTAL_DINGS.getId());
        total.setValue(total.getValue() + 1);
        mapper.update(total);
        session.commit();
        session.close();
    }

}
