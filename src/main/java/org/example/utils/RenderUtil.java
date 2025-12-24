package org.example.utils;

import org.example.enums.Constants;

public final class RenderUtil {
    public static void printSplitLine() {
        System.out.println(("_").repeat(Constants.WIDTH_MAP.getValue()*5));
    }
}
