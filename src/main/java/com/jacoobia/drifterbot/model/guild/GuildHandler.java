package com.jacoobia.drifterbot.model.guild;

import com.jacoobia.drifterbot.Drifter;
import com.jacoobia.drifterbot.utils.StringUtils;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Since discord considers server as guilds on the api side, or rather, the jda wrapper does
 * then this class handles the vex buddies 'guild'. This includes manipulating the channels,
 * users or 'members', roles etc.
 */
public class GuildHandler
{

    private static Guild guild;

    public static void seed(String seed)
    {
        guild = Drifter.getJda().getGuildById(seed);
    }

    public static Member getMemberByID(String id)
    {
        return guild.getMemberById(id);
    }

    public static boolean isUserOnline(Member member)
    {
        return member!= null && member.getOnlineStatus() != OnlineStatus.OFFLINE;
    }

    public static Member getMemberBestSearch(String unparsed)
    {
        Member member = getMemberByUsername(unparsed);
        if(member == null)
            if(StringUtils.isNumeric(unparsed))
                member = getMemberByID(unparsed);
        return member;
    }

    public static Member getMemberByUsername(String username)
    {
        List<Member> members = guild.getMembers();
        for(Member member : members)
        {
            if(doesMemberHaveNickname(member))
                if(username.equalsIgnoreCase(member.getNickname()))
                    return member;
            if(username.equalsIgnoreCase(member.getEffectiveName()))
                return member;
        }
        return null;
    }

    public static boolean doesMemberHaveNickname(Member member)
    {
        return member.getNickname() != null;
    }

    public static List<Member> getAllOnlineMembers()
    {
        List<Member> members = guild.getMembers();
        List<Member> online = new ArrayList<>();
        for(Member member : members)
        {
            if(member.getOnlineStatus() == OnlineStatus.ONLINE && !member.getUser().isBot())
                online.add(member);
        }
        return online;
    }


    public static void addRole(Member member, String roleName)
    {
        Role role = guild.getRolesByName(roleName, true).get(0);
        guild.addRoleToMember(member, role).complete();
    }

    public static void removeRole(Member member, String roleName)
    {
        Role role = guild.getRolesByName(roleName, true).get(0);
        guild.removeRoleFromMember(member, role).complete();
    }

    public static VoiceChannel getVoiceChannelMemberIsIn(Member member)
    {
        return member.getVoiceState().getChannel();
    }

    /*
    public static void giveServerRank(User user, Ranks rank)
    {
        Member member = getMemberByID(user.getId());
        giveServerRank(member, rank);
    }

    public static void giveServerRank(Member member, Ranks rank)
    {
        addRole(member, rank.getName());
    }
     */

    public static void setNickname(User user, String nickname)
    {
        Member member = getMemberByID(user.getId());
        setNickname(member, nickname);
    }

    public static void setNickname(Member member, String nickname)
    {
        member.modifyNickname(nickname).queue();
    }

}
