package com.github.shynixn.astraledit.bukkit.logic.business.command;

import com.github.shynixn.astraledit.api.bukkit.business.command.PlayerCommand;
import com.github.shynixn.astraledit.api.bukkit.business.entity.Selection;
import com.github.shynixn.astraledit.bukkit.AstralEditPlugin;
import com.github.shynixn.astraledit.bukkit.Permission;
import com.github.shynixn.astraledit.bukkit.logic.business.SelectionManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Shynixn 2018.
 * <p>
 * Version 1.2
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2018 by Shynixn
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class AutoFollowCommand implements PlayerCommand {
    private final SelectionManager manager;

    /**
     * Creates a new instance of the JoinCommand with SelectionController as dependency.
     *
     * @param manager dependency.
     */
    public AutoFollowCommand(SelectionManager manager) {
        this.manager = manager;
    }

    /**
     * Executes the given command if the arguments match.
     *
     * @param player executing the command.
     * @param args   arguments.
     * @return True if this command was executed. False if the arguments do not match.
     */
    @Override
    public boolean onPlayerExecuteCommand(Player player, String[] args) {
        if (args.length != 1 && !args[0].equalsIgnoreCase("auto-follow") && !Permission.AUTO_FOLLOW.hasPermission(player)) {
            return false;
        }

        if (!this.manager.hasSelection(player)) {
            player.sendMessage(AstralEditPlugin.PREFIX_ERROR + "You don't have a valid render.");
        } else {
            final Selection selection = this.manager.getSelection(player);
            selection.setAutoFollowEnabled(!selection.isAutoFollowEnabled());
            if (selection.isAutoFollowEnabled()) {
                player.sendMessage(AstralEditPlugin.PREFIX_SUCCESS + "Enabled auto-follow.");
            } else {
                player.sendMessage(AstralEditPlugin.PREFIX_SUCCESS + "Disabled auto-follow.");
            }
        }

        return true;
    }
}
