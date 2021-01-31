package com.company.commands;

import com.company.Main;
import com.company.Main;
import com.company.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.IOException;


public class RankCMD extends Util implements CommandExecutor, Listener {

    Main mhu;
    public Util util1;

    public RankCMD(Util utils, Main autoevent) {
        this.util1 = utils;
        this.mhu = autoevent;}


    @Override
    public boolean onCommand(CommandSender sender1, Command cmd, String label, String[] args) {

        Player sender = Bukkit.getPlayer(sender1.getName());

        if (cmd.getName().equals("globalrank")) {


            try {
                ts.writeTotalPoints(sender.getPlayer().getUniqueId(), sender.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                CalculatePosition(sender.getPlayer());

            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                sender.getPlayer().sendMessage("Global Rank: ");
                sender.getPlayer().sendMessage(GlobalRank(sender));

            } catch (IOException e) {
                sender.getPlayer().sendMessage("Something went wrong");
            }


            try {

                for (String names : Reader("BelowPlayers", sender.getUniqueId().toString(), "Below: "))
                    sender.sendMessage("Before: " + names + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                try {
                    ThreeClostsToScore(sender.getPlayer());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for (String names : Reader("BelowPlayers", sender.getUniqueId().toString(), "Below: "))
                    sender.sendMessage("After: " + names + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return true;}




}