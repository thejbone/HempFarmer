package com.shruglabs.hempfarmer.item;

import com.shruglabs.hempfarmer.init.HFItems;

import net.minecraft.item.Item;

public class HFEntityItem extends Item {

	public HFEntityItem(String name) {
		this.setRegistryName(name);
		this.setTranslationKey(name);
		addToItems(this);
	}

	private void addToItems(Item item) {
		HFItems.items.add(item);
	}
}
