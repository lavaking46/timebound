package us.newrealms.timeBound.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.Nullable;

import java.util.Collection;

public class Statistics implements BasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] strings) {
        //Add functionality for getting statistics data when running without arguments, also this is the admin command to edit the statistics(which will include their team) and such.
    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        return BasicCommand.super.suggest(commandSourceStack, args);
    }

    @Override
    public boolean canUse(CommandSender sender) {
        return BasicCommand.super.canUse(sender);
    }

    @Override
    public @Nullable String permission() {
        return BasicCommand.super.permission();
    }
}
