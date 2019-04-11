package com.wxhao.blog.server.boot.utils;

import java.util.function.Function;

public abstract class EnumUtils {

    public static <T extends Enum> T getEnum(Class<T> enumClass, Function<T, Boolean> function) {
        for (T iEnum : enumClass.getEnumConstants()) {
            if (function.apply(iEnum)) {
                return iEnum;
            }
        }
        return null;
    }

}
