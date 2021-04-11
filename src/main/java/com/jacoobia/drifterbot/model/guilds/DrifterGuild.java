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
import net.dv8tion.jda.api.entities.VoiceChannel;

/**
 * An entity/object created for each discord {@link Guild} the bot is
 * a member of
 */
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

    /**
     * Builds an audio IO channel for the guild to allow the bot
     * to send (and potentially receive) audio
     * @param guild the guild to create the audio tunnel/channel for
     */
    public void createAudioChannel(Guild guild)
    {
        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerLocalSource(playerManager);

        player = playerManager.createPlayer();
        scheduler = new ClipScheduler(guild, player);
        player.addListener(scheduler);
    }

    /**
     * Load and queue a cip from a local file
     * @param clip the directory path to the clip
     * @param channel the voice channel it's queue'd for
     */
    public void queueClip(String clip, VoiceChannel channel)
    {
        playerManager.loadItem(clip, new LoadResult(channel));
    }

    /**
     * The load result handler class to control what happens
     * depending on the result of a load attempt.
     * When a single clip or multiple clips were successfully loaded then
     * queue said clips and connect to the voice channel ready to play
     */
    protected class LoadResult implements AudioLoadResultHandler
    {

        private final VoiceChannel channel;

        LoadResult(VoiceChannel channel)
        {
            this.channel = channel;
        }

        @Override
        public void trackLoaded(AudioTrack audioTrack)
        {
            scheduler.queue(audioTrack);
            ChannelHandler.connect(channel);
        }

        @Override
        public void playlistLoaded(AudioPlaylist audioPlaylist)
        {
            for (AudioTrack clip : audioPlaylist.getTracks())
            {
                scheduler.queue(clip);
            }
            ChannelHandler.connect(channel);
        }

        @Override
        public void noMatches()
        {
            System.out.println("no matches!");
        }

        @Override
        public void loadFailed(FriendlyException e)
        {
            System.out.println("failed to load!");
        }

    }

}
