package com.shruglabs.hempfarmer.block.cannibis;

import java.util.List;

import com.shruglabs.hempfarmer.ConfigHandler;
import com.shruglabs.hempfarmer.block.HFBlockCrops;
import com.shruglabs.hempfarmer.init.HFItems;
import com.shruglabs.hempfarmer.utils.HUtils;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Hemp extends HFBlockCrops {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	private static final AxisAlignedBB[] HEMP_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1328125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.265625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3984375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.53125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6640625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.796875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9296875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0625, 1.0D) };

	public Hemp(String name) {
		super(name);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return HEMP_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}

	@Override
	protected Item getSeed() {
		return HFItems.seeds_hemp;
	}

	@Override
	protected Item getCrop() {
		int x = HUtils.random.nextInt(30) + 1;
		Hemp.crop = x > 27 ? HFItems.bud : HFItems.raw_hemp;
		this.setCropName(crop.equals(HFItems.raw_hemp) ? "hemp" : "bud");
		return HFBlockCrops.crop;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		NonNullList<ItemStack> ret = NonNullList.create();
		int age = getAge(state);
		int i;
		if (age >= getMaxAge()) {
			i = ConfigHandler.hempSeedsCropAmount - 3;
			if(i > 0)
				super.getDrops(ret, world, pos, state, i);
			Item crop = getCrop();
			i = HUtils.random.nextInt((crop == HFItems.raw_hemp ? ConfigHandler.hempAmount : ConfigHandler.hempBudAmount) + 1);
			if (i > 0)
				ret.add(new ItemStack(crop, i));
		}
		
		if (ConfigHandler.enableWand && HUtils.random.nextBoolean())
		{
			i = HUtils.random.nextInt(3);
			if (i > 0)
				ret.add(new ItemStack(HFItems.leaf, i));
		}
		
		return ret;
	}

}
