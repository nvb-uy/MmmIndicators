package elocindev.indicators.utils;

// Credits https://github.com/MehVahdJukaar
// This class is from the Target Dummy mod
// https://github.com/MehVahdJukaar/DuMmmMmmy/blob/1.20/common/src/main/java/net/mehvahdjukaar/dummmmmmy/configs/CritMode.java
//
// Mmm Indicators uses Dummy's damage number rendering which I personally love.
public enum CritMode{
    OFF,COLOR,COLOR_AND_MULTIPLIER;

    public static double encodeIntFloatToDouble(int integerPart, float floatPart) {
        long combinedValue = ((long) Float.floatToIntBits(floatPart) << 32) | (integerPart & 0xFFFFFFFFL);
        return Double.longBitsToDouble(combinedValue);
    }

    public static int extractIntegerPart(double encodedDouble) {
        long combinedValue = Double.doubleToLongBits(encodedDouble);
        return (int) (combinedValue & 0xFFFFFFFFL);
    }

    public static float extractFloatPart(double encodedDouble) {
        long combinedValue = Double.doubleToLongBits(encodedDouble);
        int floatBits = (int) (combinedValue >> 32);
        return Float.intBitsToFloat(floatBits);
    }
}