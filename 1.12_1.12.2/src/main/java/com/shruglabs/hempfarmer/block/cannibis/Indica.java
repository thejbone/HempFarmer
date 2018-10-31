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

public class Indica extends HFBlockCrops {
	
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	private static final AxisAlignedBB[] INDICA_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.755D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.76D, 1.0D) };

	public Indica(String name) {
		super(name);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return INDICA_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}

	@Override
	protected Item getSeed() {
		return HFItems.seeds_indica;
	}

	@Override
	protected Item getCrop() {
		Item crop;
		int x = HUtils.random.nextInt(10) + 1;
		crop = x > 5 ? HFItems.violet_raw_hemp : HFItems.indica_bud;
		this.setCropName(crop.equals(HFItems.violet_raw_hemp) ? "hemp" : "bud");

		return crop;
	}


	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		NonNullList<ItemStack> ret = NonNullList.create();
		int age = getAge(state);
		int i;
		if (age >= getMaxAge()) {
			i = ConfigHandler.indicaSeedsCropAmount - 3;
			if(i > 0)
				super.getDrops(ret, world, pos, state, i);
			Item crop = getCrop();
			i = HUtils.random.nextInt((crop == HFItems.violet_raw_hemp ? ConfigHandler.indicaAmount : ConfigHandler.indicaBudAmount) + 1);
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
