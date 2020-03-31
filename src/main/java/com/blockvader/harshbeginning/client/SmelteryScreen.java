package com.blockvader.harshbeginning.client;

import com.blockvader.harshbeginning.HarshBeginning;
import com.blockvader.harshbeginning.SmelteryContainer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SmelteryScreen extends ContainerScreen<SmelteryContainer>{
	
	private static final ResourceLocation SMELTERY = new ResourceLocation(HarshBeginning.MOD_ID + ":textures/gui/container/smeltery.png");
	
	private SmelteryContainer container;

	public SmelteryScreen(SmelteryContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.container = screenContainer;
		this.xSize = 176;
		this.ySize = 184;
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = this.title.getFormattedText();
		this.font.drawString(s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(SMELTERY);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		if (this.container.isBurning())
		{
			int k = this.container.getBurnTime();
			this.blit(i + 47, j + 66 - k, 176, 12 - k, 14, k + 1);
		}
		int l = container.geSmeltTime();
		this.blit(i + 79, j + 52, 176, 14, l + 1, 16);
	}
	
	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_)
	{
		super.render(p_render_1_, p_render_2_, p_render_3_);
		this.renderHoveredToolTip(p_render_1_, p_render_2_);
	}

}
