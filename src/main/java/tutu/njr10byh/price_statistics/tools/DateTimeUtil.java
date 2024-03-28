package tutu.njr10byh.price_statistics.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {

    public static Date getCurrentDateTimeAsDate() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 判断日期是否在今天
    public static boolean isToday(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        return localDateTime.getYear() == now.getYear() && localDateTime.getMonthValue() == now.getMonthValue() && localDateTime.getDayOfMonth() == now.getDayOfMonth();
    }
}
