package twinrealm.server.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.DimensionManager;

import java.util.List;

/**
 * Created by lukas on 15.09.14.
 */

/**
 * Created by lukas on 03.08.14.
 */
public class CommandSetDimension extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "dimension";
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return "commands.dimension.usage";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length < 1)
            throw new WrongUsageException("commands.dimension.usage");

        Integer dimension = parseInt(commandSender, args[0]);
        EntityPlayer player = getCommandSenderAsPlayer(commandSender);

        player.travelToDimension(dimension);
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        Integer[] dims = DimensionManager.getIDs();

        String[] strings = new String[dims.length];
        for (int i = 0; i < dims.length; i++)
            strings[i] = String.valueOf(dims[i]);

        return getListOfStringsMatchingLastWord(p_71516_2_, strings);
    }
}
