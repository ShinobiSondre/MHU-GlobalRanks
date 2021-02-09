package com.company.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class RemoveItemDamageEvent implements Listener {



    @EventHandler
    public void RemoveItemDamage(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof Player && !(event.getDamager().hasMetadata("NPC"))){

            Player player = (Player) event.getDamager();

            if(player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_AXE)||
                    player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)
                    ||player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE))
                event.setCancelled(true);

        }
    }


}
