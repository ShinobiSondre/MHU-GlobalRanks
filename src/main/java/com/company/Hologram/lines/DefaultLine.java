package com.company.Hologram.lines;

import net.blitzcube.mlapi.api.tag.ITagController;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class DefaultLine implements ITagController.TagLine {

    public String getText(Entity entity, Player player) {
        return "text";
    }

    public boolean keepSpaceWhenNull(Entity entity) {
        return false;
    }
}