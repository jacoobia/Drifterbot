package com.jacoobia.drifterbot;

import com.jacoobia.drifterbot.database.SessionFactory;
import com.jacoobia.drifterbot.events.hook.MessageListener;
import com.jacoobia.drifterbot.events.hook.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.EnumSet;

public class Drifter
{

    private static final int ERROR = 1;

    private static JDA jda;
    public static String guildId;

    public static SessionFactory sessionFactory;

    public static void main(String[] args)
    {
        guildId = args[1];
        try
        {
            sessionFactory = new SessionFactory();

            EnumSet<GatewayIntent> intents = EnumSet.of(
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_VOICE_STATES
            );

            jda = JDABuilder.createLight(args[0], intents)
                    .setActivity(Activity.watching("some Gambit matches."))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .enableCache(CacheFlag.VOICE_STATE)
                    .build();

            registerEvents();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(ERROR);
        }
    }

    private static void registerEvents()
    {
        jda.addEventListener(new MessageListener());
        jda.addEventListener(new ReadyListener());
    }

    public static JDA getJda()
    {
        return jda;
    }
}
