package elocindev.indicators.utils;

import elocindev.indicators.MmmIndicators;
import elocindev.indicators.config.MmmIndicatorsConfig.NUMBER_THRESHOLD;

public class ParticleUtils {
    public static int getColorForDamageAmount(float damageAmount) {
        int color = 0xFFFFFF;

        if (MmmIndicators.CONFIG.enable_threshold_coloring)
            for (NUMBER_THRESHOLD threshold : MmmIndicators.CONFIG.threshold_colors) {
                if (damageAmount >= threshold.threshold) {
                    color = threshold.number_color;
                }
            }

        return color;
    }
}
