package elocindev.indicators.config;

import java.util.List;

import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class MmmIndicatorsConfig {
    @NecConfig
    public static MmmIndicatorsConfig INSTANCE;

    public static String getFile() {
        return NecConfigAPI.getFile("mmmindicators-client.json5");
    }
    
    @Comment("If true, the numbers will appear when any entity takes damage, regardless if the cause is the player or not.")
    public boolean ALWAYS_DISPLAY = false;

    @Comment("The color of the numbers.")
    public int DEFAULT_NUMBER_COLOR = 0xFFFFFF;

    @Comment("Whether or not to make the numbers light up. Works with shaders.")
    public boolean LIT_UP_NUMBERS = false;

    @Comment("Changes the color of the number according to the value.")
    @Comment("Example:")
    @Comment(" \"amount = 100, color = 0xfcba03\" will turn the number into the color 0xfcba03 when the amount is 100 or higher.")
    @Comment(" ")
    @Comment("If no condition is met, the number's color will be DEFAULT_NUMBER_COLOR.")
    public boolean enable_threshold_coloring = false;

    public List<NUMBER_THRESHOLD> threshold_colors = List.of(
        new NUMBER_THRESHOLD(200, 0xfc4103),
        new NUMBER_THRESHOLD(100, 0xfcba03)
    );

    public class NUMBER_THRESHOLD {
        public int threshold;
        public int number_color;

        public NUMBER_THRESHOLD(int amount, int color) {
            threshold = amount;
            number_color = color;
        }
    } 
}
