package com.jacoobia.drifterbot;

import com.jacoobia.drifterbot.database.entity.Metrics;
import com.jacoobia.drifterbot.database.mapper.MetricsMapper;
import com.jacoobia.drifterbot.events.hook.EventHook;
import com.jacoobia.drifterbot.events.hook.MessageListener;
import com.jacoobia.drifterbot.events.impl.ReadyListener;
import com.jacoobia.drifterbot.events.registry.EventRegistry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Arrays;
import java.util.EnumSet;

public class Drifter
{

    private static final int ERROR = 1;
    private static JDA jda;

    public static String guildId;

    public static void main(String[] args)
    {
        guildId = args[1];
        try
        {
            JDABuilder builder = JDABuilder.createDefault(args[0], Arrays.asList(GatewayIntent.values()));
            builder.setDisabledCacheFlags(EnumSet.of(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE));
            builder.setBulkDeleteSplittingEnabled(false);
            jda = builder.build();
            registerEvents();

            String resource = "config/mybatis-config-dev.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);

            try(SqlSession session = sqlSessionFactory.openSession())
            {
                MetricsMapper mapper = session.getMapper(MetricsMapper.class);
                Metrics metrics = mapper.selectMetricByName("total");
                System.out.println();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(ERROR);
        }
        jda.addEventListener(new MessageListener());
        jda.addEventListener(new EventHook());
    }

    private static void registerEvents()
    {
        EventRegistry.registerEvent(new ReadyListener());
    }

    public static JDA getJda()
    {
        return jda;
    }
}
