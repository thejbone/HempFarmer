package com.shruglabs.hempfarmer.block.cannibis;

import java.util.List;

import com.shruglabs.hempfarmer.ConfigHandler;
import com.shruglabs.hempfarmer.creativetab.HFCreativeTabs;
import com.shruglabs.hempfarmer.init.HFBlocks;
import com.shruglabs.hempfarmer.init.HFItems;
import com.shruglabs.hempfarmer.utils.HUtils;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Hemp extends BlockCrops {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
	public static final AxisAlignedBB[] HEMP_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1328125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.265625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3984375D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.53125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6640625D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.796875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9296875D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0625, 1.0D) };

	private final Item seed;
	private final Item[] crops;
	
	public Hemp() {
		this(HEMP_TYPE.HYBRID);
	}
	
	public Hemp(HEMP_TYPE type) {
		Item seed;
		String name;
		Item[] crops = new Item[2];
		switch(type)
		{
			case INDICA:
				seed = HFItems.seeds_indica;
				name = "indica_crop";
				crops[0] = HFItems.violet_raw_hemp;
				crops[1] = HFItems.indica_bud;
				break;
			case SATIVA:
				seed = HFItems.seeds_sativa;
				name = "sativa_crop";
				crops[0] = HFItems.lime_raw_hemp;
				crops[1] = HFItems.sativa_bud;
				break;
			default:
				seed = HFItems.seeds_hemp;
				name = "hemp_crop";
				crops[0] = HFItems.raw_hemp;
				crops[1] = HFItems.bud;
				break;
		}
		
		this.seed = seed;
		this.crops = crops;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(HFCreativeTabs.HempFarmer);
		HFBlocks.blocks.add(this);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return HEMP_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}

	@Override
	protected Item getSeed() {
		return seed;
	}

	@Override
	protected Item getCrop() {
		return crops[HUtils.random.nextInt(2)];
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
