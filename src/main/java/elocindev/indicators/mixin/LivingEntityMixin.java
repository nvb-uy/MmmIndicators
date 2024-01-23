package elocindev.indicators.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.indicators.MmmIndicators;
import elocindev.indicators.utils.ParticleUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    private float lastHealth = 0;
    private float damageTaken = 0;
    private boolean particleDisplayedThisTick = false;

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        LivingEntity inst = (LivingEntity) (Object) this;
        if (!inst.level().isClientSide()) return;

        if (particleDisplayedThisTick) {
            particleDisplayedThisTick = false;
        }

        LivingEntity entity = (LivingEntity) (Object) this;
        float currentHealth = entity.getHealth();

        if (lastHealth > currentHealth && !particleDisplayedThisTick) {
            damageTaken = lastHealth - currentHealth;
        }

        lastHealth = currentHealth;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void afterTick(CallbackInfo ci) {
        LivingEntity inst = (LivingEntity) (Object) this;
        if (!inst.level().isClientSide()) return;

        if (!MmmIndicators.CONFIG.ALWAYS_DISPLAY && inst.getLastDamageSource() != null && !(inst.getLastDamageSource().getEntity() instanceof LocalPlayer)) return;
        
        // Check later the spell engine compat
        //if (inst.getLastDamageSource().type()) 

        double x = inst.getX(); double y = inst.getY(); double z = inst.getZ();

        if (!particleDisplayedThisTick && damageTaken > 0) {
            int color = ParticleUtils.getColorForDamageAmount(damageTaken);
            Minecraft client = Minecraft.getInstance();

            client.level.addParticle(
                MmmIndicators.DAMAGE_PARTICLE,
                x,
                y,
                z,
                damageTaken,
                color,
                0
            ); damageTaken = 0; particleDisplayedThisTick = true;
        }
    }
}