package com.company.Hologram.lines;

import net.blitzcube.mlapi.api.tag.ITagController;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LineRank implements ITagController.TagLine {

    private String f;

    public LineRank(String p) {
        f = p;
    }

    public String getText(Entity entity, Player player) {
        return f;
    }

    public boolean keepSpaceWhenNull(Entity entity) {
        return false;
    }
}
