package com.company.commands;

import com.company.Main;
import com.company.Main;
import com.company.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

import java.io.IOException;


public class RankCMD implements CommandExecutor, Listener {

    Main mhu;
    private Util util;

    public RankCMD(Util utils, Main autoevent) {
        this.util = utils;
        this.mhu = autoevent;}


    @Override
    public boolean onCommand(CommandSender sender1, Command cmd, String label, String[] args) {

        Player sender = Bukkit.getPlayer(sender1.getName());

        if (cmd.getName().equals("globalrank")) {


            try {
                util.ts.writeTotalPoints(sender.getPlayer().getUniqueId(), sender.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                util.CalculatePosition(sender.getPlayer());

            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                sender.getPlayer().sendMessage("Global Rank: ");
                sender.getPlayer().sendMessage(util.GlobalRank(sender));

            } catch (IOException e) {
                sender.getPlayer().sendMessage("Something went wrong");
            }


            try {

                for (String names : util.Reader("BelowPlayers", sender.getUniqueId().toString(), "Below: "))
                    sender.sendMessage("Before: " + names + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                try {
                    util.ThreeClostsToScore(sender.getPlayer());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for (String names : util.Reader("BelowPlayers", sender.getUniqueId().toString(), "Below: "))
                    sender.sendMessage("After: " + names + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        if(cmd.getName().equals("hgtest")){


            Slime slime = sender.getLocation().getWorld().spawn(sender.getLocation(), Slime.class);
            slime.setSize(-1);

            ArmorStand stand = sender.getLocation().getWorld().spawn(sender.getLocation(), ArmorStand.class);
            stand.setCustomName("EEEEEHHHHHHHHHH?" + "\n" + "§c§lColor Test");
            stand.setArms(false);
            stand.setBasePlate(false);
            stand.setSmall(true);
            stand.setCustomNameVisible(true);


            slime.addPassenger(stand);
            slime.setInvulnerable(true);
            slime.setCollidable(false);
            sender.addPassenger(slime);




        }


        return true;}






}