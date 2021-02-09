package com.company.commands;

import com.company.Main;
import com.company.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

import java.io.IOException;


public class RankCMD implements CommandExecutor, Listener {

    Main mhu;
    private Utilities utilities;

    public RankCMD(Utilities utils, Main autoevent) {
        this.utilities = utils;
        this.mhu = autoevent;}


    @Override
    public boolean onCommand(CommandSender sender1, Command cmd, String label, String[] args) {

        Player sender = Bukkit.getPlayer(sender1.getName());

        if (cmd.getName().equals("globalrank")) {


            try {
                utilities.ts.writeTotalPoints(sender.getPlayer().getUniqueId(), sender.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                utilities.CalculatePosition(sender.getPlayer());

            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                sender.getPlayer().sendMessage("Global Rank: ");
                sender.getPlayer().sendMessage(utilities.GlobalRank(sender));

            } catch (IOException e) {
                sender.getPlayer().sendMessage("Something went wrong");
            }

//
//            try {
//
//                for (String names : utilities.Reader("BelowPlayers", sender.getUniqueId().toString(), "Below: "))
//                    sender.sendMessage("Before: " + names + "\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            try {
//
//                try {
//                    utilities.ThreeClostsToScore(sender.getPlayer());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }catch (Exception e){}
//
//
//        }
        }

            if (cmd.getName().equals("hgtest")) {

                utilities.updatePlayerTag(sender, args[0]);

            }


            return true;
        }


    }
