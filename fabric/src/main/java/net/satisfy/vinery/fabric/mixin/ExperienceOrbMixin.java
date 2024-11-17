package net.satisfy.vinery.fabric.mixin;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.satisfy.vinery.platform.PlatformHelper;
import net.satisfy.vinery.registry.MobEffectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Objects;

@Mixin(ExperienceOrb.class)
public abstract class ExperienceOrbMixin {
    @ModifyArgs(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ExperienceOrb;repairPlayerItems(Lnet/minecraft/world/entity/player/Player;I)I"))
    public void render(Args args) {
        Player p = args.get(0);
        if(p.hasEffect(PlatformHelper.getExperienceEffect())){
            int amplifier = Objects.requireNonNull(p.getEffect(PlatformHelper.getExperienceEffect())).amplifier;
            int i = args.get(1);

            int xp = (int) (i + (i * (1 + amplifier) * 0.5));

            args.set(1, xp);
        }
    }

}
