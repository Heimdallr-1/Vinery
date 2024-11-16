package net.satisfy.vinery.platform.forge;

import net.satisfy.vinery.forge.config.VineryForgeConfig;

import java.util.List;

public class PlatformHelperImpl {
    public static int getTotalFermentationTime() {
        return VineryForgeConfig.TOTAL_FERMENTATION_TIME.get();
    }

    public static int getApplePressMaxProgress() {
        return VineryForgeConfig.APPLE_PRESS_CRAFTING_TIME.get();
    }

    public static double getCherryGrowthChance() {
        return VineryForgeConfig.CHERRY_GROWTH_CHANCE.get();
    }

    public static double getAppleGrowthChance() {
        return VineryForgeConfig.APPLE_GROWTH_CHANCE.get();
    }

    public static double getGrapeGrowthChance() {
        return VineryForgeConfig.GRAPE_GROWTH_CHANCE.get();
    }

    public static int getWineMaxLevel() {
        return VineryForgeConfig.MAX_LEVEL.get();
    }

    public static int getWineStartDuration() {
        return VineryForgeConfig.START_DURATION.get();
    }

    public static int getWineDurationPerYear() {
        return VineryForgeConfig.DURATION_PER_YEAR.get();
    }

    public static int getWineDaysPerYear() {
        return VineryForgeConfig.DAYS_PER_YEAR.get();
    }

    public static int getWineYearsPerEffectLevel() {
        return VineryForgeConfig.YEARS_PER_EFFECT_LEVEL.get();
    }

    public static int getWineMaxDuration() {
        return VineryForgeConfig.MAX_DURATION.get();
    }

    public static boolean shouldGiveEffect() {
        return VineryForgeConfig.GIVE_EFFECT.get();
    }

    public static boolean shouldShowTooltip() {
        return VineryForgeConfig.GIVE_EFFECT.get() && VineryForgeConfig.SHOW_TOOLTIP.get();
    }

    public static List<? extends String> getBasketBlacklist() {
        return VineryForgeConfig.BASKET_BLACKLIST.get();
    }

    public static double getTraderSpawnChance() {
        return VineryForgeConfig.TRADER_SPAWN_CHANCE.get();
    }

    public static boolean shouldSpawnWithMules() {
        return VineryForgeConfig.SPAWN_WITH_MULES.get();
    }

    public static int getTraderSpawnDelay() {
        return VineryForgeConfig.TRADER_SPAWN_DELAY.get();
    }
}
