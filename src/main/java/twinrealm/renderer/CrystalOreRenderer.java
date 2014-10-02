
package twinrealm.renderer;

import net.malisis.core.renderer.BaseRenderer;
import net.minecraft.util.IIcon;
import twinrealm.blocks.crystaldesert.CrystalOre;

/**
 * @author Ordinastie
 *
 */
public class CrystalOreRenderer extends BaseRenderer
{
    public static int renderPass;

    @Override
    public void render()
    {
        if(renderPass == 0 || renderType == TYPE_ISBRH_INVENTORY)
        {
            rp.icon.reset();
            drawShape(shape, rp);
        }

        if(renderPass == 1 || renderType == TYPE_ISBRH_INVENTORY)
        {
            int metadata = renderType == TYPE_ISBRH_INVENTORY ? 0 : blockMetadata;
            IIcon icon = ((CrystalOre) block).getIconOverlay(metadata);
            rp.icon.set(icon);
            drawShape(shape, rp);
        }
    }
}
