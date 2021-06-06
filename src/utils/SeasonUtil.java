package utils;

import lombok.Getter;

public enum SeasonUtil {

    SPRING(1,"春"),SUMMER(2,"夏"),AUTUMN(3,"秋"),WINTE(4,"冬");

    @Getter private Integer SeasonCode;
    @Getter private String SeasonMessage;

    SeasonUtil(Integer seasonCode, String seasonMessage) {
        SeasonCode = seasonCode;
        SeasonMessage = seasonMessage;
    }

    public static SeasonUtil getSeasonBySeasonCode(Integer SeasonCode){
        SeasonUtil[] utils = SeasonUtil.values();
        for (SeasonUtil util : utils) {
            if(util.getSeasonCode() == SeasonCode){
                return util;
            }
        }
        return null;
    }

}
