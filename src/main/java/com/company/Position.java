package com.company;

import com.company.util.Util;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Set;

public class Position extends Util {

    public int ReturnPosition(Player player) throws IOException {

        int position_citizen = 0;

        int position_student = 0;
        int position_sidekick = 0;
        int position_hero = 0;
        int position_prohero = 0;

        int position_thug = 0;
        int position_delinquent = 0;
        int position_villain = 0;
        int position_provillain = 0;

        int counter_citizen = 0;

        int counter_student = 0;
        int counter_sidekick = 0;
        int counter_hero = 0;
        int counter_prohero = 0;

        int counter_thug = 0;
        int counter_delinquent = 0;
        int counter_villain = 0;
        int counter_provillain = 0;


        //Calculate Score


        Set<String> keys = HighestScoreList().keySet();

        for(String key : keys){

            if(key.toString().contains(" default"))
                counter_citizen++;

            else if(key.toString().contains(" student"))
                counter_student++;
            else if(key.toString().contains(" sidekick"))
                counter_sidekick++;
            else if(key.toString().contains(" hero"))
                counter_hero++;
            else if(key.toString().contains(" pro-hero"))
                counter_prohero++;

            else if(key.toString().contains(" thug"))
                counter_thug++;
            else if(key.toString().contains(" delinquent"))
                counter_delinquent++;
            else if(key.toString().contains(" villain"))
                counter_villain++;
            else if(key.toString().contains(" pro-villain"))
                counter_provillain++;


            if(key.toString().split(" default")[0].contains(player.getUniqueId().toString()))
                position_citizen = counter_citizen;

            if(key.toString().split(" student")[0].contains(player.getUniqueId().toString()))
                position_student = counter_student;
            if(key.toString().split(" sidekick")[0].contains(player.getUniqueId().toString()))
                position_sidekick = counter_sidekick;
            if(key.toString().split(" hero")[0].contains(player.getUniqueId().toString()))
                position_hero = counter_hero;
            if(key.toString().split(" pro-hero")[0].contains(player.getUniqueId().toString()))
                position_prohero = counter_prohero;

            if(key.toString().split(" thug")[0].contains(player.getUniqueId().toString()))
                position_thug = counter_thug;
            if(key.toString().split(" delinquent")[0].contains(player.getUniqueId().toString()))
                position_delinquent = counter_delinquent;
            if(key.toString().split(" villain")[0].contains(player.getUniqueId().toString()))
                position_villain = counter_villain;
            if(key.toString().split(" pro-villain")[0].contains(player.getUniqueId().toString()))
                position_provillain = counter_provillain;

        }


        if(highestgroup(player).equals("default"))
            return position_citizen;

        else if(highestgroup(player).equals("student"))
            return position_student;
        else if(highestgroup(player).equals("sidekick"))
            return position_sidekick;
        else if(highestgroup(player).equals("hero"))
            return position_hero;
        else if(highestgroup(player).equals("pro-hero"))
            return position_prohero;

        else if(highestgroup(player).equals("thug"))
            return position_thug;
        else if(highestgroup(player).equals("delinquent"))
            return position_delinquent;
        else if(highestgroup(player).equals("villain"))
            return position_villain;
        else if(highestgroup(player).equals("pro-villain"))
            return position_provillain;


        return 0;}

}
