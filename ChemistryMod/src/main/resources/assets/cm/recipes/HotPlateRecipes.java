package assets.cm.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.yourri1012.chemistrymod.init.ModBlocks;
import com.yourri1012.chemistrymod.init.ModItems;

import net.minecraft.item.ItemStack;

public class HotPlateRecipes {
	private static final HotPlateRecipes INSTANCE = new HotPlateRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static HotPlateRecipes getInstance() {
		return INSTANCE;
	}
	
	private HotPlateRecipes() {
		addHotPlateRecipe(new ItemStack(ModItems.COPPER), new ItemStack(ModItems.MOLTEN_COPPER), 5.0F);
		addHotPlateRecipe(new ItemStack(ModItems.PLATINUM), new ItemStack(ModItems.MOLTEN_PLATINUM), 5.0F);
	}

	
	public void addHotPlateRecipe(ItemStack input1, ItemStack result, float experience) {
		this.smeltingList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getHotPlateResult(ItemStack input1) {
		for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
	        if (this.compareItemStacks(input1, entry.getKey())) {
	            return entry.getValue();
	        }
		}
		System.out.println("HOTPLATERESULT IS EMPTY but input = " + input1.getDisplayName());
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getDisplayName().equals(stack1.getDisplayName());
		// && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata())
	}
	
	public Map<ItemStack, ItemStack> getDualSmeltingList()  {
		return this.smeltingList;
	}
	
	public float getHotPlateExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
