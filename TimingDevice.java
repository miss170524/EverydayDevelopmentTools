package com.Miaojiang.EverydayDvelopmentTools;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @author Miao jiang
 * 定时器(指定时间点,而不是时间长短)
 */
public class TimingDevice {
    /**
     *
     * @param date
     * @return
     * date为字符串类型,格式为"HH:mm:ss",例如 15:30:00
     * 此方法返回值为距离设定时间的秒数值
     */
    public static int redisKeyExpireTime(String date) {
        Calendar calendar = Calendar.getInstance();
        //判断当前时间时分秒是否大于设定时间
        if (timeCompare(date)) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        String[] times = date.split(":");
        int[] array = Arrays.stream(times).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i <array.length ; i++) {
            calendar.set(Calendar.HOUR_OF_DAY, array[0]);
            calendar.set(Calendar.MINUTE, array[1]);
            calendar.set(Calendar.SECOND, array[2]);
        }
        //剩余时间就等于
        long expireTime = ((calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000);
        return (int) expireTime;
    }

    /**
     * 判断时间大小
     * @param time
     * @return
     */
    private static boolean timeCompare(String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(time, dateTimeFormatter);
        return LocalTime.now().isAfter(localTime);

    }
}
