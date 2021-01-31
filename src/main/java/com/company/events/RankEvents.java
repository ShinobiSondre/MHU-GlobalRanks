package com.company.events;

import com.company.util.Util;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import mhu.groot.grootlevels.events.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

import static mhu.groot.grootlevels.classes.Levels.GetPlayerLevel;


public class RankEvents extends Util implements Listener {


    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent ev) throws IOException {

        Player player = ev.getPlayer();

        if(ev.getPlayer().isOnline()){
            ts.writeTotalPoints(player.getUniqueId(),player);
            p.CalculatePosition(player);
            DelayedHologram(player,50,"Login"+player.getUniqueId().toString());}}


    @EventHandler
    public void PlayerLogOut(PlayerQuitEvent p) {

        Player pp = p.getPlayer();

        hologramPlayers.remove(pp.getName()+"[Global-Rank]").delete();

        try{


        }catch (Exception e){


        }
    }


    @EventHandler public void TeleportEvent(PlayerTeleportEvent ev){

        Player hs = ev.getPlayer();

        if(!hs.hasMetadata("NPC")){

            teleported = true;

            try {CalculatePosition(hs.getPlayer());} catch (IOException e) {
                e.printStackTrace();
            }
        }}




    @EventHandler public void mobDeath(MythicMobDeathEvent dd) throws IOException {

        if (dd.getMob().getFaction().equals("Villain") || dd.getMob().getFaction().equals("Hero")) {


            String killer = dd.getKiller().getName();


            if (!mobKills.containsKey(dd.getKiller().getUniqueId())) {

                mobKills.put(dd.getKiller().getUniqueId(), 1);


            } else if (mobKills.containsKey(dd.getKiller().getUniqueId())) {

                kills = mobKills.get(dd.getKiller().getUniqueId());
                kills++;

                mobKills.put((dd.getKiller().getUniqueId()), kills);
            }

        }

    }

        @EventHandler
        public void PlayerDeath (PlayerDeathEvent p){

            if (p.getEntity().isOnline()) {

                hologramPlayers.remove(p.getEntity().getName() + "[Global-Rank]").delete();

                for (Player player : Bukkit.getOnlinePlayers()) {

                    try {
                        ts.writeTotalPoints(player.getPlayer().getUniqueId(), player.getPlayer());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        CalculatePosition(player.getPlayer());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
    }


    @EventHandler
    public void LevelUpEvent (PlayerLevelUpEvent ev) throws NullPointerException, IOException {

        for (Player player : Bukkit.getOnlinePlayers()) {

            try {
                ts.writeTotalPoints(player.getPlayer().getUniqueId(), player.getPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                CalculatePosition(player.getPlayer());

            } catch (IOException e) {
                e.printStackTrace();
            }

            DelayedHologram(player, 80, player.getUniqueId() + "lvlUp");

        }


    }


    @EventHandler
    public void moveEvent (PlayerMoveEvent m) throws NullPointerException, IOException {
        //Move event:
        Player playerThatMoved = m.getPlayer();


        if (playerThatMoved.isOnline()) {

            Location where1 = new Location(playerThatMoved.getWorld(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getX(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getY() + 2.7, Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getZ());
            Location where2 = new Location(playerThatMoved.getWorld(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getX(), Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getY() + 3.1, Bukkit.getPlayer(playerThatMoved.getName()).getLocation().getZ());


            if (hologramPlayers.get(playerThatMoved.getName()) != null)
                hologramPlayers.get(playerThatMoved.getName()).teleport(where2);

            if (hologramPlayers.get(playerThatMoved.getName() + "[Global-Rank]") != null) {
                hologramPlayers.get(playerThatMoved.getName() + "[Global-Rank]").teleport(where1);
            } else {

            }

            //Checks if the rank is different
            if (!Rank(m.getPlayer().getUniqueId()).equals(highestgroup(m.getPlayer()))) {

                for (Player hs : Bukkit.getOnlinePlayers()) {

                    try {
                        ts.writeTotalPoints(hs.getPlayer().getUniqueId(), hs.getPlayer());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        CalculatePosition(hs.getPlayer());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    DelayedHologram(hs, 60, hs.getUniqueId() + "ChangeRank");


                }
            }

            int level = GetPlayerLevel(m.getPlayer());

            int mobkills = 0;

            try {
                if (!(mobKills.get(playerThatMoved.getUniqueId()) == null))
                    mobkills = mobKills.get(playerThatMoved.getUniqueId());
            } catch (Exception e) {
                mobkills = 1;
            }

            if (teleported == true && playerThatMoved.isOnline()) {


                try {
                    ts.writeTotalPoints(playerThatMoved.getPlayer().getUniqueId(), playerThatMoved.getPlayer());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    CalculatePosition(playerThatMoved.getPlayer());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                teleported = false;


            }


        }

    }




}
