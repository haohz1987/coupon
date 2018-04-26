
package com.handpay.coupon.utils;

import android.content.Context;
import android.util.Log;

import com.handpay.coupon.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期时间工具类
 *
 * @author：Klaus Liu E-mail: lpluck08@gmail.com
 * @date：2013-3-20 下午01:33:11
 */
public final class DateUtils {
    /**
     * 将日期按照格式化为指定格式字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat
                .getInstance();
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    public static String formatLongTimeToString(long time, String pattern){
        if(time<=0)return null;
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat
                .getInstance();
        sdf.applyPattern(pattern);
        return sdf.format(time);
    }
    /**
     * 将字符串按照格式化为指定格式日期
     *
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date formatStringToDate(String dateString, String pattern) {
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat
                .getInstance();
        sdf.applyPattern(pattern);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            LogT.w("format " + dateString+ " to date by yyyyMMddHHmmss throw exception!!");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 功能：判断字符串是否为日期格式
     *
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算选择的日期是否大于30天
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean changequerystate(Date startDate, Date endDate) {
        int startday = startDate.getDate();
		int endday = endDate.getDate();
		int startmoth = startDate.getMonth() + 1;
		int endmoth = endDate.getMonth() + 1;
		int Intervalday = 0;
		if (!startDate.after(endDate)) {
			if (endDate.getYear() - startDate.getYear() == 0
					|| endDate.getYear() - startDate.getYear() == 1) {
				Log.i("result", "endmoth=" + endmoth + ",startmoth="
						+ startmoth);
				if (endmoth - startmoth <= 1) {
					if (endmoth - startmoth == 1 || endmoth - startmoth == -11) {// 不在同一个月
						// 开始月剩下的天数
						int tempstartdata = getday(startDate.getYear(),
								startmoth) - startday;
						Log.i("result", "tempstartdata=" + tempstartdata);
						Log.i("result", (tempstartdata + endday) + "");
						if (tempstartdata + endday >30) {
							return false;
						} else {
							return true;
						}
					} else {
						// 同一个月
						if (endDate.getYear() - startDate.getYear() != 0
								|| endday - startday > getday(
										startDate.getYear(), startmoth)) {
							return false;
						} else {
							return true;
						}
					}
				} else {
					return false;
				}
			} else {
				if (endDate.getYear() - startDate.getYear() == 1) {
					if (startday + endday > 30) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			}
		} else {
			Log.i("result", "选择的开始日期大于结束日期");
			return false;
		}
	}

    /**
     * 计算选择的时间是否大于90天
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean changeQueryState(Date startDate, Date endDate) {
        int startday = startDate.getDate();
        int endday = endDate.getDate();
        int startmoth = startDate.getMonth() + 1;
        int endmoth = endDate.getMonth() + 1;
        int Intervalday = 0;
        if (!startDate.after(endDate)) {
            if (endDate.getYear() - startDate.getYear() == 0
                    || endDate.getYear() - startDate.getYear() == 1) {
                Log.i("result", "endmoth=" + endmoth + ",startmoth="
                        + startmoth);
                if (endmoth - startmoth <= 3) {
                    if (endmoth - startmoth == 3 || endmoth - startmoth == -9) {// 所选时间包含四个月 或者不在同一年但包含四个月
                        // 开始月剩下的天数
                        int tempstartdata = getday(startDate.getYear(),
                                startmoth) - startday;
                        Log.i("result", "tempstartdata=" + tempstartdata);
                        Log.i("result", (tempstartdata + endday) + "");
                        int secondDay = getday(startDate.getYear(), startmoth + 1);//第二个月的天数
                        int thirdDay = getday(startDate.getYear(), startmoth + 2);//第三个月的天数
                        int day = tempstartdata + secondDay + thirdDay;
                        if (day + endday > 90) {
                            return false;
                        } else {
                            return true;
                        }
                    } else if (endmoth - startmoth == 2 || endmoth - startmoth == -10) {// 所选时间包含三个月 或者不在同一年但包含三个月
                        // 开始月剩下的天数
                        int tempstartdata = getday(startDate.getYear(),
                                startmoth) - startday;
                        Log.i("result", "tempstartdata=" + tempstartdata);
                        Log.i("result", (tempstartdata + endday) + "");
                        int secondDay = getday(startDate.getYear(), startmoth + 1);//第二个月的天数
                        //int thirdDay=getday(startDate.getYear(), startmoth+2);//第三个月的天数
                        int day = tempstartdata + secondDay;
                        if (day + endday > 90) {
                            return false;
                        } else {
                            return true;
                        }
                    } else if (endmoth - startmoth == 1 || endmoth - startmoth == -11) {// 所选时间包含两个月 或者不在同一年但包含两个月
                        /*// 开始月剩下的天数
                        int tempstartdata = getday(startDate.getYear(),
								startmoth) - startday;
						Log.i("result", "tempstartdata=" + tempstartdata);
						Log.i("result", (tempstartdata + endday) + "");
						if (tempstartdata + endday >90) {
							return false;
						} else {
							return true;
						}*/
                        return true;
                    } else
                        // 同一个月
                        if (endDate.getYear() - startDate.getYear() != 0
                                || endday - startday > getday(
                                startDate.getYear(), startmoth)) {
                            return false;
                        } else {
                            return true;
                        }
                }
            } else {
                return false;
            }

        } else {
            Log.i("result", "选择的开始日期大于结束日期");
            return false;
        }
        return false;
    }



    public static int getday(int year, int curr_moth) {
        int allday = 0;
        switch (curr_moth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                allday = 31;
                break;
            case 2:
                allday = get2Days(year);
                break;
            default:
                allday = 30;
                break;
        }
        return allday;
    }

    public static int get2Days(int year)// 计算某一年2月份有多少天
    {
        Calendar c = Calendar.getInstance();
        c.set(year, 2, 1);// 0-11->1-12 将日期设置为某一年的3月1号
        c.add(Calendar.DAY_OF_MONTH, -1);// 将日期减去一天，即日期变成2月的最后一天
        return c.get(Calendar.DAY_OF_MONTH);// 返回二月最后一天的具体值
    }

    public static boolean isOkDate(Context mcontext, Date startDate, Date endDate) {
        if (startDate == null) {
            DialogFactory.showAlertDialog(mcontext, R.string.tip,
                    R.string.select_start_date_tip);
            return false;
        }
        if (endDate == null) {
            DialogFactory.showAlertDialog(mcontext, R.string.tip,
                    R.string.select_end_date_tip);
            return false;
        }
        if (startDate.after(endDate)) {
            DialogFactory.showAlertDialog(mcontext, R.string.tip,
                    R.string.select_date_tip);
            return false;
        }
        return true;
    }
}
