package com.shruglabs.hempfarmer.block.cannibis;

import java.util.List;

import com.shruglabs.hempfarmer.ConfigHandler;
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

public class Sativa extends Hemp {

	
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	public static final AxisAlignedBB[] HEMP_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.19D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.38D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.57D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.76D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.95D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.14D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.33D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.52D, 1.0D) };

	public Sativa() {
		super(HEMP_TYPE.SATIVA);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		NonNullList<ItemStack> ret = NonNullList.create();
		int age = getAge(state);
		int i;
		if (age >= getMaxAge()) {
			i = ConfigHandler.sativaSeedsCropAmount - 3;
			if(i > 0)
				super.getDrops(ret, world, pos, state, i);
			Item crop = getCrop();
			i = HUtils.random.nextInt((crop == HFItems.lime_raw_hemp ? ConfigHandler.sativaAmount : ConfigHandler.sativaBudAmount) + 1);
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
