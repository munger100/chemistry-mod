package com.yourri1012.chemistrymod.items.blocks.gui;

import java.io.IOException;

import com.yourri1012.chemistrymod.init.ModItems;
import com.yourri1012.chemistrymod.items.blocks.containers.ContainerObjectiveTable;
import com.yourri1012.chemistrymod.objectives.Gamemode;
import com.yourri1012.chemistrymod.items.blocks.containers.ContainerObjectiveTable;
import com.yourri1012.chemistrymod.tileEntities.TileEntityObjectiveTable;
import com.yourri1012.chemistrymod.util.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class GuiObjectiveTable extends GuiContainer{
	private static final ResourceLocation TEXTURES = new ResourceLocation (Reference.MOD_ID + ":textures/gui/objective_table.png");
	private final EntityPlayer player;
	private final TileEntityObjectiveTable tileentity;
	private int time = 0;
	private int totalTime = 60;
	private String name;
	private GuiButton button;
	public ItemStack[] valid_items = {
			new ItemStack(ModItems.CALCIUM),
			new ItemStack(ModItems.COPPER),
			new ItemStack(ModItems.PLATINUM),
			new ItemStack(ModItems.SODIUM),
			new ItemStack(ModItems.CHLORINE),
			new ItemStack(ModItems.OBJECTIVE_SKIP)
	};
	
	public GuiObjectiveTable(EntityPlayer player, TileEntityObjectiveTable tileentity) {
		super (new ContainerObjectiveTable(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		tileentity.setName(player.getName());
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2 ) + 3, 8, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		String v;
		if (this.tileentity.getField(1)!=0) {
			int k = this.getTime();
			if(k<=5) {
				tileentity.setName(this.player.getDisplayNameString());
			}
			
			this.fontRenderer.drawString(this.tileentity.getName(), this.getGuiLeft() + 122, this.getGuiTop() +71 + 2,  4210752);
			v = "" + (3-k/20);
		} else {
			v = "" + 0;
		}
		this.buttonList.clear();
		buttonList.add(button = new GuiButton(0, this.guiLeft + 8, this.guiTop + 30 + 5,45, 20, v));
	}
	
	private int getTime() {
		return this.tileentity.getField(1);
	}
	
	public boolean isItemValid(ItemStack stack) {
		for (ItemStack valid : this.valid_items) {
			if (valid.isItemEqualIgnoreDurability(stack)) {
				return true;
			}
		} return false;
	}
	
	
}
