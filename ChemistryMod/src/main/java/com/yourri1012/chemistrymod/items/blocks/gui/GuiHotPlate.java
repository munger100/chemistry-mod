package com.yourri1012.chemistrymod.items.blocks.gui;

import com.yourri1012.chemistrymod.items.blocks.containers.ContainerHotPlate;
import com.yourri1012.chemistrymod.tileEntities.TileEntityHotPlate;
import com.yourri1012.chemistrymod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiHotPlate extends GuiContainer {
	private static ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/hot_plate.png");
	private final EntityPlayer player;
	private final TileEntityHotPlate tileEntity;
	
	public GuiHotPlate(EntityPlayer player, TileEntityHotPlate tileEntity) {
		super(new ContainerHotPlate(player.inventory, tileEntity));
		this.player = player;
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileEntity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 8, 4210752);		
		int l = this.getCookProgressiveScaled(24);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(75, 36, 177, 15, l, 17);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
	}
	
	private int getCookProgressiveScaled(int pixels) {
		int i = this.tileEntity.getField(1);
		int j = this.tileEntity.getField(2);
		if (j == 0) return 0;
		return i == 0 ? 0 : i*pixels/j;
	}

}
