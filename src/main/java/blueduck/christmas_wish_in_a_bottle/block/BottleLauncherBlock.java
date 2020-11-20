package blueduck.christmas_wish_in_a_bottle.block;

import blueduck.christmas_wish_in_a_bottle.item.WishInBottleItem;
import blueduck.christmas_wish_in_a_bottle.registry.Registry;
import blueduck.jollyboxes.registry.JollyBoxesBlocks;
import blueduck.jollyboxes.registry.JollyBoxesSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BottleLauncherBlock extends Block {

    public static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 12.0D, 12.0D, 16.0D);
    public static final VoxelShape SIDEWAYS = Block.makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 12.0D, 12.0D);

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BottleLauncherBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.getHeldItem(handIn).getItem() instanceof WishInBottleItem && !worldIn.isRemote()) {
            if ((player.getPersistentData().getInt("day_presents") + player.getPersistentData().getInt("night_presents")) < 5) {
                if (worldIn.canBlockSeeSky(pos.up())) {
                    player.getPersistentData().putInt("day_presents", player.getPersistentData().getInt("day_presents") + 1);
                    player.getHeldItem(handIn).shrink(1);
                    player.playSound(SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1F, 1F);
                }
                else {
                    TranslationTextComponent chatMessage = new TranslationTextComponent("christmas_wish_in_a_bottle.text.under_block");
                    player.sendMessage(chatMessage, Util.DUMMY_UUID);
                }
            }
            else {
                TranslationTextComponent chatMessage = new TranslationTextComponent("christmas_wish_in_a_bottle.text.too_many");
                player.sendMessage(chatMessage, Util.DUMMY_UUID);
            }
        }
        return ActionResultType.SUCCESS;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.equals(this.stateContainer.getBaseState().with(FACING, Direction.EAST)) || state.equals(this.stateContainer.getBaseState().with(FACING, Direction.WEST)))
        {
            return SHAPE;
        }
        return SIDEWAYS;
    }
}
