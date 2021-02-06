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


            try {

                for (String names : utilities.Reader("BelowPlayers", sender.getUniqueId().toString(), "Below: "))
                    sender.sendMessage("Before: " + names + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                try {
                    utilities.ThreeClostsToScore(sender.getPlayer());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for (String names : utilities.Reader("BelowPlayers", sender.getUniqueId().toString(), "Below: "))
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