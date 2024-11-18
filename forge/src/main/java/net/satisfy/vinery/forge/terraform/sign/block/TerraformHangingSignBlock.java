package net.satisfy.vinery.forge.terraform.sign.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.satisfy.vinery.forge.terraform.sign.BlockSettingsLock;
import net.satisfy.vinery.forge.terraform.sign.TerraformHangingSign;

public class TerraformHangingSignBlock extends CeilingHangingSignBlock implements TerraformHangingSign {
    private final ResourceLocation texture;
    private final ResourceLocation guiTexture;

    public TerraformHangingSignBlock(ResourceLocation texture, ResourceLocation guiTexture, BlockBehaviour.Properties settings) {
        super(BlockSettingsLock.lock(settings), WoodType.OAK);
        this.texture = texture;
        this.guiTexture = guiTexture;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public ResourceLocation getGuiTexture() {
        return this.guiTexture;
    }
}
