package com.snad.mixin;

import com.snad.registry.ModTags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SugarCaneBlock;

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
            value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/world/WorldView;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"
        ),
        ordinal = 1
    )
    public BlockState replaceBlockState(BlockState state)
    {
        if (state.isIn(ModTags.SNAD))
        {
            state = Blocks.SAND.getDefaultState();
        }
        return state;
    }
}
