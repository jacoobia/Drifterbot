package com.jacoobia.drifterbot.model.channels.audio;

import com.jacoobia.drifterbot.model.channels.ChannelHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClipScheduler extends AudioEventAdapter
{

    private final BlockingQueue<AudioTrack> queue;

    private final Guild guild;
    private final AudioPlayer player;

    public ClipScheduler(Guild guild, AudioPlayer player) {
        this.guild = guild;
        this.player = player;
        queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason)
    {
        if(queue.isEmpty())
            ChannelHandler.disconnect(guild);
        else
            if (endReason.mayStartNext)
                nextTrack();
    }

    public void nextTrack()
    {
        player.startTrack(queue.poll(), false);
    }

    public void queue(AudioTrack clip)
    {
        if (!player.startTrack(clip, true))
        {
            queue.offer(clip);
        }
    }
}
