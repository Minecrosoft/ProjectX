package twinrealm.worldgen;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/**
 * Created by lukas on 15.09.14.
 */
public class TeleporterNone extends Teleporter
{
    public TeleporterNone(WorldServer worldServer)
    {
        super(worldServer);
    }

    @Override
    public void placeInPortal(Entity entity, double x, double y, double z, float rotationYaw)
    {

    }
}
