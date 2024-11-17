package net.satisfy.vinery.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.satisfy.vinery.platform.PlatformHelper;
import net.satisfy.vinery.registry.MobEffectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayer {
    @Unique
    private int jumpCount = 0;
    @Unique
    private boolean jumpedLastTick = false;

    public ClientPlayerEntityMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }


    @Inject(method = "aiStep", at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        if(this.hasEffect(PlatformHelper.getImprovedJumpBoostEffect())) {
            LocalPlayer player = (LocalPlayer) (Object) this;
            if (player.onGround() || player.onClimbable()) {
                jumpCount = 1;
            } else if (!jumpedLastTick && jumpCount > 0 && player.getDeltaMovement().y < 0) {
                if (player.input.jumping && !player.getAbilities().flying) {
                    if (canJump(player)) {
                        --jumpCount;
                        player.jumpFromGround();
                    }
                }
            }
            jumpedLastTick = player.input.jumping;
        }
    }

    @Redirect(method = "updateAutoJump", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"))
    public boolean improvedJumpBoost(LocalPlayer livingEntity, MobEffect statusEffect) {
        // not always true, ignore warning
        return livingEntity.hasEffect(MobEffects.JUMP) || livingEntity.hasEffect(PlatformHelper.getImprovedJumpBoostEffect());
    }

    @Redirect(method = "updateAutoJump", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getEffect(Lnet/minecraft/world/effect/MobEffect;)Lnet/minecraft/world/effect/MobEffectInstance;"))
    public MobEffectInstance improvedJumpBoostAmplifier(LocalPlayer livingEntity, MobEffect statusEffect) {
        return livingEntity.hasEffect(PlatformHelper.getImprovedJumpBoostEffect()) ?  livingEntity.getEffect(PlatformHelper.getImprovedJumpBoostEffect()) : livingEntity.getEffect(MobEffects.JUMP);
    }

    @Unique
    private boolean wearingUsableElytra(LocalPlayer player) {
        ItemStack chestItemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        return chestItemStack.getItem() == Items.ELYTRA && ElytraItem.isFlyEnabled(chestItemStack);
    }

    @Unique
    private boolean canJump(LocalPlayer player) {
        return !wearingUsableElytra(player) && !player.isFallFlying() && !player.isPassenger()
                && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION);
    }
}
