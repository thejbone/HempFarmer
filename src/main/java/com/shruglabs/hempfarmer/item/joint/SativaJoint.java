package com.shruglabs.hempfarmer.item.joint;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SativaJoint extends Joint {

	public SativaJoint(String name, int maxStack) {
		super(name, maxStack);
	
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
		entity.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 1000, 2, true, false));
		return super.onItemUseFinish(stack, world, entity);
	}
}
