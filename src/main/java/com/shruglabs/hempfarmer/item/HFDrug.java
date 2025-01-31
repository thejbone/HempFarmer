package com.shruglabs.hempfarmer.item;

import com.shruglabs.hempfarmer.creativetab.HFCreativeTabs;
import com.shruglabs.hempfarmer.init.HFItems;

import net.minecraft.item.Item;

public class HFDrug extends Item {

	public HFDrug(String name, int maxStack) {
		this.setMaxStackSize(maxStack);
		this.setRegistryName(name);
		this.setTranslationKey(name);
		this.setCreativeTab(HFCreativeTabs.HempFarmer);
		addToItems(this);
	}

	private void addToItems(Item item) {
		HFItems.items.add(item);
	}
}
