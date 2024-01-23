package elocindev.indicators;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.indicators.config.MmmIndicatorsConfig;
import elocindev.indicators.particle.DamageParticle;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

public class MmmIndicators implements ClientModInitializer {
	public static final String MODID = "mmmindicators";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static MmmIndicatorsConfig CONFIG;

	public static final SimpleParticleType DAMAGE_PARTICLE = FabricParticleTypes.simple(true);

	@Override
	public void onInitializeClient() {
		NecConfigAPI.registerConfig(MmmIndicatorsConfig.class);
		CONFIG = MmmIndicatorsConfig.INSTANCE;

		Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MODID, "damage"), DAMAGE_PARTICLE);
		ParticleFactoryRegistry.getInstance().register(DAMAGE_PARTICLE, DamageParticle.Factory::new);
	}
}