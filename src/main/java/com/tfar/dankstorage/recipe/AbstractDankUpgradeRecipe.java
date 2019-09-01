package com.tfar.dankstorage.recipe;

import com.tfar.dankstorage.DankStorage;
import com.tfar.dankstorage.block.DankBlock;
import com.tfar.dankstorage.inventory.PortableDankHandler;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public abstract class AbstractDankUpgradeRecipe extends ShapedRecipe {

  public AbstractDankUpgradeRecipe(ResourceLocation idIn,NonNullList<Ingredient> ingredients,ItemStack result) {
    super(idIn,DankStorage.MODID, 3, 3, ingredients,result);
  }

  @Nonnull
  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    ItemStack bag = super.getCraftingResult(inv).copy();
    ItemStack oldBag = inv.getStackInSlot(4);
    PortableDankHandler oldHandler = DankBlock.getHandler(oldBag);
    PortableDankHandler newHandler = DankBlock.getHandler(bag);
    for (int i = 0;i < oldHandler.getSlots();i++){
      newHandler.insertItem(i,oldHandler.getStackInSlot(i),false);
    }
    CompoundNBT nbt = newHandler.serializeNBT();
    bag.setTag(nbt);
    return bag;
  }
}