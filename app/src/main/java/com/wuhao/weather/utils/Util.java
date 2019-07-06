package com.wuhao.weather.utils;

import android.text.TextUtils;

import com.wuhao.weather.data.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class Util {

    public static String getHighLowTemperature(double high, double low) {
        return (int) high + Constants.TEMPERATURE_UNIT + "/" + (int) low + Constants.TEMPERATURE_UNIT;
    }

    public static String getTime(long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(timeStamp * 1000);
        if (!TextUtils.isEmpty(str) && str.contains(" ") && str.split(" ").length > 1) {
            return str.split(" ")[1];
        }
        return " ";
    }

    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date().getTime());
    }

    public static String getWindDirection(double deg) {
        if (deg < 22.5 || deg > 337.5) {
            return "N";
        }
        if (deg >= 22.5 && deg < 67.5) {
            return "NE";
        }
        if (deg >= 67.5 && deg < 112.5) {
            return "E";
        }
        if (deg >= 112.5 && deg < 157.5) {
            return "SE";
        }
        if (deg >= 157.5 && deg < 202.5) {
            return "S";
        }
        if (deg >= 202.5 && deg < 247.5) {
            return "SW";
        }
        if (deg >= 247.5 && deg < 292.5) {
            return "W";
        }
        if (deg >= 292.5 && deg < 337.5) {
            return "NW";
        }
        return " ";
    }

    public static String getWeekOfDate(Date dt, int i) {
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        w = (w + i) % 7;
        return weekDays[w];
    }

    public static String getDateFromBean(String fullDate) {
        if (TextUtils.isEmpty(fullDate))
            return fullDate;
        fullDate = fullDate.trim();
        if (!fullDate.contains(" "))
            return fullDate;
        return fullDate.split(" ")[0];
    }

    public static int getTimeZoneOffSet(Date date, int originalTimeZone) {
        String zoneStr = String.format("%tZ", date);
        int zoneInt = 0;
        if (zoneStr.contains(":")) {
            zoneInt = Integer.valueOf(zoneStr.split(":")[0].substring(3));
        }
        return originalTimeZone - 60 * 60 * zoneInt;
    }

}
