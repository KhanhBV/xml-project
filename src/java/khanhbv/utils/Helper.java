/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import khanhbv.dto.ProductTestDTO;

/**
 *
 * @author vankhanhbui
 */
public class Helper {

//    public static String formatPower(String powerStr) {
//        if (powerStr.contains(StringConstant.POWER_STRING_V22)) {
//            powerStr = powerStr.replace(StringConstant.POWER_STRING_V22, "");
//            powerStr = powerStr + StringConstant.POWER_UNIT_V2;
//        }

//        int index = powerStr.indexOf(":");
//        if (index != -1) {
//            if (index + 1 < powerStr.length()) {
//                String subString = powerStr.substring(0, index + 1);
//                powerStr = powerStr.replace(subString, "").trim();
//                int begin = powerStr.indexOf("(");
//                int end = powerStr.indexOf(")");
//                if (begin != -1 && end != -1) {
//                    subString = powerStr.substring(begin, end + 1);
//                    powerStr = powerStr.replace(subString, "");
//                }
//
//            }
//
//        }
//
//        powerStr = powerStr.replace(StringConstant.POWER_STRING_V1, "").replace(StringConstant.POWER_STRING_V2, "")
//                .replace(StringConstant.POWER_STRING_V3, "").replace(StringConstant.POWER_STRING_V4, "")
//                .replace(StringConstant.POWER_STRING_V5, "").replace(StringConstant.POWER_STRING_V6, "")
//                .replace(StringConstant.POWER_STRING_V7, "").replace(StringConstant.POWER_STRING_V8, "")
//                .replace(StringConstant.POWER_STRING_V9, "").replace(StringConstant.POWER_STRING_V10, "")
//                .replace(StringConstant.POWER_STRING_V11, "").replace(StringConstant.POWER_STRING_V12, "")
//                .replace(StringConstant.POWER_STRING_V13, "").replace(StringConstant.POWER_STRING_V14, "")
//                .replace(StringConstant.POWER_STRING_V15, "").replaceAll(",", "")
//                .replace(StringConstant.POWER_STRING_V16, "").replace(StringConstant.POWER_STRING_V16, "")
//                .replace(StringConstant.POWER_STRING_V18, "").replace(".", "");

//        return powerStr.trim();
//    }

    public static float converPower(String powerStr, String powerBefortConver) {
        float result = 0;
        try {
            if (!powerBefortConver.isEmpty()) {
                result = Float.parseFloat(powerBefortConver);
                if (powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V3)) {

                    result = result / 24;
                } else if (powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V4)
                        && powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V5)) {
                    result = (float) (result * 9000 * 0.293 / 1000);
                } else if (powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V4)
                        && !powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V5)) {
                    result = (float) (result * 0.293 / 1000);
                } else if (powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V1)) {

                } else if (powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V2)) {
                    result = result / 1000;
                } else if (powerStr.toUpperCase().contains(StringConstant.POWER_UNIT_V6)) {
                    result = (float) (result * 1.25);
                }else {
                    result = 0;
                }
            }

//            if (powerStr.contains(StringConstant.POWER_UNIT_V3)) {
//                powerStr = powerStr.replace(StringConstant.POWER_UNIT_V3, "").trim();
//                if (powerStr.matches("\\d+")) {
//                    result = Float.parseFloat(powerStr);
//                    result = result / 24;
//                }
//            }
//
//            if (powerStr.contains(StringConstant.POWER_UNIT_V1)) {
//                powerStr = powerStr.replace(StringConstant.POWER_UNIT_V1, "").trim();
//
//                if (powerStr.matches("\\d+")) {
//                    result = Float.parseFloat(powerStr);
//                }
//
//            }
//            if (powerStr.contains(StringConstant.POWER_UNIT_V4)) {
//                powerStr = powerStr.replace(StringConstant.POWER_UNIT_V4, "").trim();
//                if (powerStr.matches("\\d+")) {
//                    result = Float.parseFloat(powerStr);
//                }
//            }
//
//            if (powerStr.contains(StringConstant.POWER_UNIT_V2)) {
//                powerStr = powerStr.replace(StringConstant.POWER_UNIT_V2, "").trim();
//
//                if (powerStr.matches("\\d+")) {
//                    result = Float.parseFloat(powerStr);
//                    result = result / 1000;
//                }
//            }
//
//            if (powerStr.contains(StringConstant.POWER_UNIT_V6)) {
//                powerStr = powerStr.replace(StringConstant.POWER_UNIT_V6, "").trim();
//                if (powerStr.matches("\\d+")) {
//                    result = Float.parseFloat(powerStr);
//                }
//            }
//
//            if (powerStr.contains(StringConstant.POWER_UNIT_V5)) {
//                powerStr = powerStr.replace(StringConstant.POWER_UNIT_V5, "").trim();
//                if (powerStr.matches("\\d+")) {
//                    result = Float.parseFloat(powerStr);
//                    result = result / 1000;
//                }
//            }
//
//            if (powerStr.contains(StringConstant.POWER_UNIT_V7)) {
//                powerStr = powerStr.replace(StringConstant.POWER_UNIT_V7, "").trim();
//                if (powerStr.matches("\\d+")) {
//                    result = Float.parseFloat(powerStr);
//                    result = result / 1000;
//                }
//            }
//            NumberFormat numf = NumberFormat.getNumberInstance();
//            numf.setMaximumFractionDigits(2);
//            numf.setRoundingMode(RoundingMode.UP);
//            String resultStr = numf.format(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String findPowerNumberInSring(String powerString) {
        String data = "";
        boolean check = powerString.toUpperCase().contains(StringConstant.POWER_UNIT_V1) || 
                powerString.toUpperCase().contains(StringConstant.POWER_UNIT_V5) || 
                powerString.toUpperCase().contains(StringConstant.POWER_UNIT_V6);
        if (check) {
            Pattern p = powerString.contains(".") ? Pattern.compile("\\d*\\.\\d+") : Pattern.compile("\\d+");
            Matcher m = p.matcher(powerString.replace(",", ""));
            if (m.find()) {
                data = m.group(0);
            }
        } else {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(powerString.replace(",", "").replace(".", ""));
            if (m.find()) {
                data = m.group(0);
            }
        }

        return data;
    }
    
     public static void addListToList(List<ProductTestDTO> listFrom, List<ProductTestDTO> listTo){
        if(listFrom != null && listTo != null){
            for(int i = 0; i < listFrom.size(); i++){
                listTo.add(listFrom.get(i));
            }
        }
    }
}
