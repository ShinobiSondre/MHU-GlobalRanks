package com.company;

import com.company.util.Util;
import me.realized.duels.api.DuelsAPI;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.UUID;

import static java.lang.Integer.parseInt;
import static mhu.groot.grootlevels.classes.Levels.GetPlayerLevel;

public class TotalScore {

    private Util util;
    public TotalScore(Util util) { this.util = util;}

    public int getTotalScore(Player player) throws IOException {

        int level = GetPlayerLevel(player);

        int mobkills;
        try{mobkills = util.mobKills.get(player.getUniqueId());} catch (Exception e) {
            mobkills = 1;}

        int duelwins;
        try{duelwins = DuelsAPI.getUser(player.getUniqueId(), true).getWins();} catch (Exception e) {
            duelwins = 1;}

        int totalscore = duelwins + mobkills + level;



        return totalscore;}


    public void writeTotalPoints(UUID uuid, Player player) throws IOException {

        int level = GetPlayerLevel(player);
        int mobkills;

        if(!util.readmobkills){
            util.mobKills.put((player.getUniqueId()),util.m.MobsKilled(player.getUniqueId()));
            util.readmobkills = true;}

        try{
            mobkills = util.mobKills.get(player.getUniqueId());} catch (Exception e) {
            mobkills = 1;}

        int duelwins = 1;
        try{
            duelwins = DuelsAPI.getUser(player.getUniqueId(), true).getWins();} catch (Exception e) {}


        int totalscore = duelwins + mobkills + level;

        String input;

        try {
            input = totalscore + "\n" + "Rank: " + util.highestgroup(player) + "\n" + "Mobs Killed: " + mobkills + "\n" + "Playername: " + player.getName() + "\n";
        }
            catch (Exception e) {
            input = 1 + "\n" + "Rank: " + "default" + "\n" + "Mobs Killed: " + 1 + "\n" + "Playername: " + "Steve" + "\n";
        }


        util.Writer(input,uuid.toString(),"PlayerData");

    }


    public int TotalScoreFromFile(UUID uuid) throws IOException {

        String data = "";

        try {
            File Filepath = new File("plugins/MHU_GlobalScore/PlayerData/" + uuid.toString() + ".txt");

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(Filepath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            while (line != null) {

                if (!line.contains("Rank: ")&&!line.contains("Mobs Killed: ")&&!line.contains("Playername: ")&&!line.contains("Below: ")) {

                    data = line;

                }
                // read next line
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //END


            reader.close();

        } finally {

        }
        return parseInt(data);}



}
