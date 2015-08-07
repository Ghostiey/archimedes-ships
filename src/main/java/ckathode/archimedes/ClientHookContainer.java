package ckathode.archimedes;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import ckathode.archimedes.entity.EntityShip;
import ckathode.archimedes.network.MsgRequestShipData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientHookContainer extends CommonHookContainer
{
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if (event.world.isRemote && event.entity instanceof EntityShip)
		{
			if (((EntityShip) event.entity).getShipChunk().chunkTileEntityMap.isEmpty())
			{
				return;
			}
			
			MsgRequestShipData msg = new MsgRequestShipData((EntityShip) event.entity);
			ArchimedesShipMod.instance.pipeline.sendToServer(msg);
		}
	}
}
