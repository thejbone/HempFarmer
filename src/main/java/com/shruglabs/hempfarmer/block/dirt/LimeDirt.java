package com.shruglabs.hempfarmer.block.dirt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.shruglabs.hempfarmer.block.HFBlockDirt;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LimeDirt extends HFBlockDirt {

	public LimeDirt(String name) {
		super(name);
		this.blockSoundType = SoundType.SLIME;
	}

	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		EntityPlayer player = null;
		if (world.getPlayerEntityByName(entity.getName()) != null) {
			player = world.getPlayerEntityByName(entity.getName());
		}
		if (player != null) {
			player.heal(0.1F);
		}
	}

	@Override
	public void onPlayerDestroy(World world, BlockPos pos, IBlockState state) {
		playerDestroyed = true;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		Random rand = world instanceof World ? ((World) world).rand : RANDOM;
		Item item = null;
		if (playerDestroyed && !fromOil) {
			item = Blocks.DIRT.getItemDropped(state, rand, fortune);
		} else {
			item = this.getItemDropped(state, rand, fortune);
		}
		if (item != null) {
			ret.add(new ItemStack(item, 1));
		}
		return ret;
	}
}
