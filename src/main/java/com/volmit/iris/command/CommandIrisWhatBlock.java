package com.volmit.iris.command;

import org.bukkit.FluidCollisionMode;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import com.volmit.iris.Iris;
import com.volmit.iris.util.B;
import com.volmit.iris.util.C;
import com.volmit.iris.util.FastBlockData;
import com.volmit.iris.util.MortarCommand;
import com.volmit.iris.util.MortarSender;

public class CommandIrisWhatBlock extends MortarCommand
{
	public CommandIrisWhatBlock()
	{
		super("block", "b");
		setDescription("Get the block data for looking.");
		requiresPermission(Iris.perm.studio);
		setCategory("Wut");
		setDescription("WAILA,WAWLA etc");
	}

	@Override
	public boolean handle(MortarSender sender, String[] args)
	{
		if(sender.isPlayer())
		{
			Player p = sender.player();
			BlockData bd = p.getTargetBlockExact(128, FluidCollisionMode.NEVER).getBlockData();
			if(bd != null)
			{
				sender.sendMessage("Material: " + C.GREEN + bd.getMaterial().name());
				sender.sendMessage("Full: " + C.WHITE + bd.getAsString(true));

				if(B.isStorage(FastBlockData.of(bd)))
				{
					sender.sendMessage(C.YELLOW + "* Storage Block (Loot Capable)");
				}

				if(B.isLit(FastBlockData.of(bd)))
				{
					sender.sendMessage(C.YELLOW + "* Lit Block (Light Capable)");
				}

				if(B.isFoliage(FastBlockData.of(bd)))
				{
					sender.sendMessage(C.YELLOW + "* Foliage Block");
				}

				if(B.isDecorant(FastBlockData.of(bd)))
				{
					sender.sendMessage(C.YELLOW + "* Decorant Block");
				}

				if(B.isFluid(FastBlockData.of(bd)))
				{
					sender.sendMessage(C.YELLOW + "* Fluid Block");
				}

				if(B.isFoliagePlantable(FastBlockData.of(bd)))
				{
					sender.sendMessage(C.YELLOW + "* Plantable Foliage Block");
				}

				if(B.isSolid(FastBlockData.of(bd)))
				{
					sender.sendMessage(C.YELLOW + "* Solid Block");
				}
			}
		}

		else
		{
			sender.sendMessage("Players only.");
		}

		return true;
	}

	@Override
	protected String getArgsUsage()
	{
		return "";
	}
}
