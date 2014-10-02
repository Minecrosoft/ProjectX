package twinrealm.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Fligabob
 */
public class BlockHillGrass extends BlockGrass {

	@SideOnly(Side.CLIENT)
	private IIcon topTexture;
	@SideOnly(Side.CLIENT)
	private IIcon dirtTexture;
	@SideOnly(Side.CLIENT)
	private IIcon sideOverlay;

	public BlockHillGrass() {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		this.blockIcon = iconReg.registerIcon(this.getTextureName());
		this.topTexture = iconReg.registerIcon(this.getTextureName() + "_top");
		this.sideOverlay = iconReg.registerIcon(this.getTextureName() + "_side_overlay");
		this.dirtTexture = TRBlocks.hillDirt.getBlockTextureFromSide(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.topTexture : (side == 0 ? this.dirtTexture : this.blockIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess iba, int x, int y, int z, int side) {
		return getIcon(side, iba.getBlockMetadata(x, y, z));
	}

	@SideOnly(Side.CLIENT)
	public static IIcon getIconSideOverlay() {
		return TRBlocks.hillGrass.sideOverlay;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (!world.isRemote) {
			if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2) {
				world.setBlock(x, y, z, TRBlocks.hillDirt);
			} else if (world.getBlockLightValue(x, y + 1, z) >= 9) {
				for (int i = 0; i < 4; ++i) {
					int x1 = x + random.nextInt(3) - 1;
					int y1 = y + random.nextInt(5) - 3;
					int z1 = z + random.nextInt(3) - 1;

					if (world.getBlock(x1, y1, z1) == TRBlocks.hillDirt && world.getBlockMetadata(x1, y1, z1) == 0 && world.getBlockLightValue(x1, y1 + 1, z1) >= 4 && world.getBlockLightOpacity(x1, y1 + 1, z1) <= 2)
						world.setBlock(x1, y1, z1, TRBlocks.hillGrass);
				}
			}
		}
	}

}
