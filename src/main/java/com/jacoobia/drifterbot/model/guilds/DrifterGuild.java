package com.jacoobia.drifterbot.model.guilds;

import com.jacoobia.drifterbot.model.channels.ChannelHandler;
import com.jacoobia.drifterbot.model.channels.audio.ClipScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Guild;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrifterGuild
{

    private String guildId;
    private Guild guild;

    private AudioPlayerManager playerManager;

    private AudioPlayer player;
    private ClipScheduler scheduler;

    private String clip;

    public void createAudioChannel(Guild guild)
    {
        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerLocalSource(playerManager);

        player = playerManager.createPlayer();
        scheduler = new ClipScheduler(guild, player);
        player.addListener(scheduler);
    }

    public void queueClip(String clip)
    {
        playerManager.loadItem(clip, new LoadResult());
    }

    protected class LoadResult implements AudioLoadResultHandler
    {
        @Override
        public void trackLoaded(AudioTrack audioTrack)
        {
            scheduler.queue(audioTrack);
        }

        @Override
        public void playlistLoaded(AudioPlaylist audioPlaylist)
        {
            for (AudioTrack clip : audioPlaylist.getTracks())
            {
                scheduler.queue(clip);
            }
        }

        @Override
        public void noMatches()
        {
            System.out.println("no matches!");
            ChannelHandler.disconnect(guild);
        }

        @Override
        public void loadFailed(FriendlyException e)
        {
            System.out.println("failed to load!");
            ChannelHandler.disconnect(guild);
        }
    }

}
