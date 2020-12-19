package com.snad.mixin;

import com.snad.registry.ModTags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

@Mixin(SugarCaneBlock.class)
public class SugarCaneMixin extends Block
{
    public SugarCaneMixin(Settings settings)
    {
        super(settings);
    }
    
    @ModifyVariable
    (
        method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z",
        at = @At
        (
            value = "INVOKE",
            target = "net/minecraft/block/BlockState"
        ),
        ordinal = 1
    )
    public BlockState replaceBlockState(BlockState state, BlockState state2, WorldView world, BlockPos pos)
    {
        if (state.isIn(ModTags.SNAD))
        {
            state = Blocks.SAND.getDefaultState();
        }
        return state;
    }
}
