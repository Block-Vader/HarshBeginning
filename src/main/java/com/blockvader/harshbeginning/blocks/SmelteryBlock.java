package com.blockvader.harshbeginning.blocks;

import java.util.Random;

import com.blockvader.harshbeginning.init.ModTileEntities;
import com.blockvader.harshbeginning.tileentities.SmelteryTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

public class SmelteryBlock extends Block{
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public SmelteryBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.valueOf(false)));
	}
	
	@Override
	public int getLightValue(BlockState state, IEnviromentBlockReader world, BlockPos pos)
	{
		return state.get(LIT) ? 15 : 0;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
		builder.add(LIT);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return ModTileEntities.SMELTERY.create();
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (!worldIn.isRemote)
		{
			SmelteryTileEntity te = (SmelteryTileEntity) worldIn.getTileEntity(pos);
			if (te != null)
			{
				NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
			}
		}
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof SmelteryTileEntity)
			{
				((SmelteryTileEntity)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock())
		{
	         TileEntity tileentity = worldIn.getTileEntity(pos);
	         if (tileentity instanceof SmelteryTileEntity)
	         {
	        	for (int i = 0; i < 6; i++)
	        	{
	        		ItemStack stack = ((SmelteryTileEntity)tileentity).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null).getStackInSlot(i);
	        		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	        	}
	            worldIn.updateComparatorOutputLevel(pos, this);
	         }
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.get(LIT))
		{
			double d0 = pos.getX() + 0.5D;
		    double d1 = pos.getY();
		    double d2 = pos.getZ() + 0.5D;
		    if (rand.nextDouble() < 0.1D)
		    {
		    	worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
		    }
		    Direction direction = stateIn.get(FACING);
		    Direction.Axis direction$axis = direction.getAxis();
		    double d4 = rand.nextDouble() * 0.6D - 0.3D;
		    double d5 = direction$axis == Direction.Axis.X ? direction.getXOffset() * 0.52D : d4;
		    double d6 = rand.nextDouble() * 9.0D / 16.0D;
		    double d7 = direction$axis == Direction.Axis.Z ? direction.getZOffset() * 0.52D : d4;
		    worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		    
		    double d10 = rand.nextDouble() * 9.0D / 16.0D + 0.5D;
		    worldIn.addParticle(ParticleTypes.SMOKE, d0, d1 + d10, d2, 0.0D, 0.05D, 0.0D);
		}
	}
}
