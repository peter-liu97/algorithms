package com.leetcode;

import jdk.nashorn.internal.parser.DateParser;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

	  /** 年-月-日 */
	  public static SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
	
	  /**
	   * 根据年月周 获取 周一和 周日 
	   * @param year 年
	   * @param month 月 
	   * @param weekOfMonth 一月中的第几周
	   * @return    0：周一 、1：周日 
	   */
	  public static String[] getWeekStartEndDay(int year, int month, int weekOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		Date lastDay = getLastDayOfMonth(calendar.getTime());
		Date tempDate = null;
		while(calendar.getTime().before(lastDay) || calendar.getTime().equals(lastDay)) {
		  if(calendar.get(Calendar.WEEK_OF_MONTH) == weekOfMonth) {
			tempDate = calendar.getTime();
			break;
		  }
		  calendar.add(Calendar.DATE, 1);
		}
		String start_end_date[] = null;
		if(tempDate != null) {
		  start_end_date = new String[2];
		  start_end_date[0] = getMonday(tempDate);
		  start_end_date[1] = getSunday(tempDate);
		}
		return start_end_date;
	  }
	
	  /***
	    * 获取特殊报表一周第一天
	    * 周六
	    * @param calendar
	    * @return
	    */
	  public static String getFirstDayOfWeek_rpt(Calendar calendar) {
		calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
		int curDay = calendar.get(7);
		if(curDay == 1) {
		  calendar.add(5, -1);
		} else {
		  calendar.add(5, -curDay);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	  }
	
	  /***
	   * 获取特殊报表一周最后一天
	   * 周五
	   * @param calendar
	   * @return
	   */
	  public static String getLastDayWeek_rpt(Calendar calendar) {
		calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
		int curDay = calendar.get(7);
		if(curDay != 6) {
		  calendar.add(5, 6 - curDay);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	  }
	
	  /**
	   * 根据年月周 获取 周一和 周日 
	   * @param year 年
	   * @param month 月 
	   * @param weekOfMonth 一月中的第几周
	   * @return    0：周六 、1：下周五 
	   */
	  public static String[] getWeekStartEndDay_rpt(int year, int month, int weekOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		Date lastDay = getLastDayOfMonth(calendar.getTime());
		Date tdate = null;
		String start_end_date[] = null;
		start_end_date = new String[2];
		while(calendar.getTime().before(lastDay) || calendar.getTime().equals(lastDay)) {
		  if(calendar.get(Calendar.WEEK_OF_MONTH) == weekOfMonth) {
			start_end_date[0] = getFirstDayOfWeek_rpt(calendar);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 6);
			start_end_date[1] = getLastDayWeek_rpt(calendar);
			break;
		  }
		  calendar.add(Calendar.DATE, 1);
		}
		return start_end_date;
	  }
	
	  /**
	   * 获取双周日期
	   * 一个月分为2个双周
	   * @param year
	   * @param month
	   * @param weekOfMonth
	   * @return
	   */
	  public static String[] getDoubleWeekStartEndDay(int year, int month, int weekOfMonth) {
		String smonth = month < 10 ? "0" + month : month + "";
		String start_end_date[] = new String[2];
		if(weekOfMonth == 1) {//第一个双周
		  StringBuffer sb = new StringBuffer();
		  start_end_date[0] = sb.append(year).append("-").append(smonth).append("-").append("01")
			  .toString();
		  sb.setLength(0);
		  start_end_date[1] = sb.append(year).append("-").append(smonth).append("-").append("15")
			  .toString();
		} else {//第二个双周
		  StringBuffer sb = new StringBuffer();
		  start_end_date[0] = sb.append(year).append("-").append(smonth).append("-").append("16")
			  .toString();
		  sb.setLength(0);
		  start_end_date[1] = sb.append(year).append("-").append(smonth).append("-").append("31")
			  .toString();
		}
		return start_end_date;
	  }

	  /***
	   * 取某月最后一天
	   * @param date
	   * @return
	   */
	  public static Date getLastDayOfMonth(Date date) {
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	  }

	  //获取累计月的字符串
//	  public static String getMonths(String month){
//		  if(StringUtils.isNotBlank(month)){
//			  int mon = Integer.parseInt(month);
//              String months ="";
//			  for(int i=1;i<=mon;i++){
//				  months+= "'"+i+"',";
//			  }
//			  return months.substring(0,months.length()-1);
//		  }
//		  return "";
//	  }
	//获取累计月的字符串
//	public static String getIntegerMonth(String month){
//		if(StringUtils.isNotBlank(month)){
//			int mon = Integer.parseInt(month);
//			String months ="";
//			for(int i=1;i<=mon;i++){
//				months+= i+",";
//			}
//			return months.substring(0,months.length()-1);
//		}
//		return "";
//	}
	  /**
	   * 获取日期的天
	   * @param date
	   * @return
	   */
	  public static String DateToMonthStr(Date date){
			SimpleDateFormat df=new SimpleDateFormat("dd");
			String dateStr=df.format(date);
			return dateStr;
	  }
	  
	  /**
	   * 获取日期的天
	   * @param date
	   * @return
	   */
	  public static int DateToMonthStr1(Date date){
			SimpleDateFormat df=new SimpleDateFormat("dd");
			String dateStr=df.format(date);
			return Integer.parseInt(dateStr);
	  }
	  
	  /***
	   * 取某月最后一天
	   * @param date
	   * @return
	   */
	  public static String getLastDayOfMonth1(Date date) {
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.add(Calendar.MONTH, 1);
		  cal.set(Calendar.DATE, 1);
		  cal.add(Calendar.DATE, -1);
		if((cal.get(Calendar.DAY_OF_MONTH) < 30) && ((cal.get(Calendar.MONTH))+1 != 2)){
			  int num = 30 - cal.get(Calendar.DAY_OF_MONTH);
			  cal.add(Calendar.DATE, +num);
		  }
		  return sdf.format(cal.getTime());
	  }
	  
	  /***
	   * 取某月最后一天（最后一天大于30号，也是30号）
	   * @param date
	   * @return
	   */
	  public static String getLastDayOfMonth2(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		if(cal.get(Calendar.DAY_OF_MONTH) > 30){
			cal.add(Calendar.DATE, -1);
			//二月没有30号，所以Calendar在这里不能保留最后一天为30号，只能在引用的地方处理
		} else if((cal.get(Calendar.DAY_OF_MONTH) < 30) && ((cal.get(Calendar.MONTH))+1 != 2)){
			int num = 30 - cal.get(Calendar.DAY_OF_MONTH);
			cal.add(Calendar.DATE, +num);
		} 
		return sdf.format(cal.getTime());
	  }

	public static String findLastDate(Date date, Integer i) {
	  	if(date == null){
			date = new Date();
		}
		if(i == null){
			i = 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);//当前时间前去一个月，即一个月前的时间
		return DateUtil.dateToStrLong(calendar.getTime());
	}


	//获得明年最后一天的日期
	   public String getNextYearEnd(){
	        String str = "";
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    
	      Calendar lastDate = Calendar.getInstance();
	      lastDate.add(Calendar.YEAR, 1);//加一个年
	      lastDate.set(Calendar.DAY_OF_YEAR, 1);
	      lastDate.roll(Calendar.DAY_OF_YEAR, -1);
	       str=sdf.format(lastDate.getTime());    
	       return str;      
	    }    
	         
	  //获得明年第一天的日期    
	    public String getNextYearFirst(){
	        String str = "";
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    
	      Calendar lastDate = Calendar.getInstance();
	      lastDate.add(Calendar.YEAR, 1);//加一个年
	      lastDate.set(Calendar.DAY_OF_YEAR, 1);
	           str=sdf.format(lastDate.getTime());    
	      return str;      
	             
	    }    
	    
	    //获得年最后一天的日期    
	    public static Date getYearEnd(Date date){
	    	Calendar lastDate = Calendar.getInstance();
	    	lastDate.setTime(date);
	    	lastDate.set(Calendar.DAY_OF_YEAR, 1);
	    	lastDate.roll(Calendar.DAY_OF_YEAR, -1);
	    	return lastDate.getTime();      
	    }    
	    
	    //获得年第一天的日期    
	    public static Date getYearFirst(Date date){
	    	Calendar lastDate = Calendar.getInstance();
	    	lastDate.setTime(date);
	    	lastDate.set(Calendar.DAY_OF_YEAR, 1);
	    	return lastDate.getTime();      
	    }    
	    
	    /**
		 * 计算两个日期相差年数
		 * @param dstr1
		 * @param dstr2
		 * @return
		 */
		public static Integer getYearNum(Date dstr1, Date dstr2){
			// 计算日期差值
			Integer[] strs = compareYMD(dstr1, dstr2);
			if(strs[1] > 0 || strs[2] > 0){
				strs[0] += 1;
			}
			return strs[0];
		}
  
	  /** 
	   * 得到本季度第一天的日期 
	   * @Methods Name getFirstDayOfQuarter 
	   * @return Date 
	   */  
	  public static Date getFirstDayOfQuarter(Date date)   {
	      Calendar cDay = Calendar.getInstance();
	      cDay.setTime(date);  
	      int curMonth = cDay.get(Calendar.MONTH);
	      if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){
	          cDay.set(Calendar.MONTH, Calendar.JANUARY);
	      }  
	      if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){
	          cDay.set(Calendar.MONTH, Calendar.APRIL);
	      }  
	      if (curMonth >= Calendar.JULY && curMonth <= Calendar.SEPTEMBER) {
	          cDay.set(Calendar.MONTH, Calendar.JULY);
	      }  
	      if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
	          cDay.set(Calendar.MONTH, Calendar.OCTOBER);
	      }  
	      cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
	      return cDay.getTime();     
	  }  
  
	  /** 
	   * 得到本季度最后一天的日期 
	   * @Methods Name getLastDayOfQuarter 
	   * @return Date 
	   */  
	  public static Date getLastDayOfQuarter(Date date)   {
	      Calendar cDay = Calendar.getInstance();
	      cDay.setTime(date);  
	      int curMonth = cDay.get(Calendar.MONTH);
	      if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){
	          cDay.set(Calendar.MONTH, Calendar.MARCH);
	      }  
	      if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){
	          cDay.set(Calendar.MONTH, Calendar.JUNE);
	      }  
	      if (curMonth >= Calendar.JULY && curMonth <= Calendar.SEPTEMBER) {
	          cDay.set(Calendar.MONTH, Calendar.SEPTEMBER);
	      }  
	      if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
	          cDay.set(Calendar.MONTH, Calendar.DECEMBER);
	      } 
	      cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
	      return cDay.getTime();     
	  }
	  /**
	   * 取某月第一天
	   * @param date
	   * @return
	   */
	  public static Date getMonthFirstDay(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH,1);
			return cal.getTime();
		  }

	/**
	 * 取某月第一个周五
	 * @param
	 * @return
	 */
	public static String getMonthFirstFriDay(String yearMonth) {
		Date date = DateUtil.parseStringToDate3(yearMonth);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);

		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE,4);
		return DateUtil.yyyy_mm_dd.format(cal.getTime());

	}
	  
	  /**
	   * 取某月第一天
	   * @param date
	   * @return
	   */
	  public static Date getFirstDayOfMonth(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH,1);
			return cal.getTime();
		  }
	  
	  /**
		 * 取日期的天
		 * @param date
		 * @return
		 */
		public static int getDayByDate(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_MONTH);
		}
		
		/**
		 * 取日期的月
		 * @param date
		 * @return
		 */
		public static int getMonthByDate(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.MONTH)+1;//月份从1开始，所以加1
		}
		
		/**
		 * 取日期的年
		 * @param date
		 * @return
		 */
		public static int getYearByDate(Date date) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.YEAR);
		}
	  	/**
	  	 * 设置年月日
	  	 * @param year
	  	 * @param month
	  	 * @param day
	  	 * @return 返回String格式的日期
	  	 * @throws ParseException
	  	 */
	  	public String daysAfterOneDate(int year, int month, int day) throws ParseException {
			Calendar c = Calendar.getInstance();
			c.get(Calendar.YEAR);
			c.get(Calendar.MONTH);
			c.get(Calendar.DATE);//获取年,月,日 当然时分秒也可以 注意这里的月份比较特殊 从0开始
			c.get(Calendar.DAY_OF_WEEK);//获取当前日期在星期中的第几天 从1-7对应 日-六
			c.getActualMaximum(Calendar.DAY_OF_MONTH);//获取当月中天数的最大值 即当月的日期数
			c.get(Calendar.DAY_OF_MONTH);//当前天数所在月份的第几天
			c.set(year, month-1, day);//指定一个日期 
			SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	      sdf.applyPattern("yyyy-MM-dd");
	      return sdf.format(c.getTime());
		}
	  	
	  	/**
		 * 返回加n天后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static String nDaysAfterOneDate(String htqdsj, int n) throws ParseException {
	        String beginTime = "" ;
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, +n);
	        beginTime = sdf.format(cal.getTime());
	        return beginTime;
	     }
		 
		 /**
		 * 返回加n天后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static Date nDaysAfterOneDate1(String htqdsj, int n) throws ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, +n);
	        return cal.getTime();
	     }

	/**
	 * 返回减n天后的日期
	 * @param
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String addDayforDateStr(String htqdsj, String pattern, int n) throws ParseException {
		String beginTime = "" ;
		SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
		sdf.applyPattern(pattern);
		Date date = sdf.parse(htqdsj);
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		beginTime = sdf.format(cal.getTime());
		return beginTime;
	}
		 
		 /**
		 * 返回减n天后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static String nDaysFrontOneDate(String htqdsj, int n) throws ParseException {
	        String beginTime = "" ;
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, -n);
	        beginTime = sdf.format(cal.getTime());
	        return beginTime;
	     }
		 
		 /**
		 * 返回减n天后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static Date nDaysFrontOneDate1(String htqdsj, int n) throws ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, -n);
	        return cal.getTime();
	     }

	/**
	 * 返回减n天后的日期
	 * @param
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static Date nDaysFrontOneDate2(String htqdsj, int n) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
		sdf.applyPattern("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(htqdsj);
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -n);
		return cal.getTime();
	}

	/**
	 * 返回减n年后的日期
	 * @param
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String addYearforDateStr(String htqdsj, String pattern, int n) throws ParseException {
		String beginTime = "" ;
		SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
		sdf.applyPattern(pattern);
		Date date = sdf.parse(htqdsj);
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, n);
		beginTime = sdf.format(cal.getTime());
		return beginTime;
	}
		 
		 /**
		 * 返回加n月后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static Date nMonthsAfterOneDate1(String htqdsj, int n) throws ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, +n);
	        return cal.getTime();
	     }
		 
		 /**
		 * 返回减n月后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static String nMonthsfrontOneDate(String htqdsj, int n) throws ParseException {
	        String beginTime = "" ;
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, -n);
	        beginTime = sdf.format(cal.getTime());
	        return beginTime;
	     }
		 
		 /**
		 * 返回加n月后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static String nMonthsAfterOneDate(String htqdsj, int n) throws ParseException {
	        String beginTime = "" ;
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, +n);
	        beginTime = sdf.format(cal.getTime());
	        return beginTime;
	     }
			 
		 /**
		 * 返回减n月后的日期  
		 * @param
		 * @param n
		 * @return
		 * @throws ParseException
		 */
		 public static Date nMonthsfrontOneDate1(String htqdsj, int n) throws ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
	        sdf.applyPattern("yyyy-MM-dd");
	        Date date = sdf.parse(htqdsj);
	        Calendar cal= Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, -n);
	        return cal.getTime();
	     }

	/**
	 * 返回减n月后的日期
	 * @param
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static Date subDateMonth(String htqdsj, int n) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("", Locale.CHINESE);
			sdf.applyPattern("yyyy-MM");
			Date date = sdf.parse(htqdsj);
			Calendar cal= Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -n);
			return cal.getTime();
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 返回n月后的日期
	 * @param
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static Date addMonth(Date date, int n) {
		try{
			Calendar cal= Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, n);
			return cal.getTime();
		}catch (Exception e){
			return new Date();
		}
	}
  	
  	/**
  	 * 设置年月日
  	 * @param year
  	 * @param month
  	 * @param day
  	 * @return 返回Date的日期
  	 * @throws ParseException
  	 */
  	public static Date daysAfterOneDate1(int year, int month, int day) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.get(Calendar.YEAR);
		c.get(Calendar.MONTH);
		c.get(Calendar.DATE);//获取年,月,日 当然时分秒也可以 注意这里的月份比较特殊 从0开始
		c.get(Calendar.DAY_OF_WEEK);//获取当前日期在星期中的第几天 从1-7对应 日-六
		c.getActualMaximum(Calendar.DAY_OF_MONTH);//获取当月中天数的最大值 即当月的日期数
		c.get(Calendar.DAY_OF_MONTH);//当前天数所在月份的第几天
		c.set(year, month-1, day);//指定一个日期 
		return c.getTime();
	}

  /**
   * 返回周一
   * 
   * @param date
   * @return
   */
  public static String getMonday(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	return DateUtil.yyyy_mm_dd.format(cal.getTime());
  }

	/**
	 * 返回周五
	 *
	 * @param date
	 * @return
	 */
	public static String getFriDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE,4);
		return DateUtil.yyyy_mm_dd.format(cal.getTime());
	}

	/**
	 * 获取前一周周五日期
	 *
	 * @param date
	 * @return String
	 */
	public static String getPreWeekFriDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE,-3);
		return DateUtil.yyyy_mm_dd.format(cal.getTime());
	}

	/**
	 * 格式转换Date 转 String   yyyy-mm-dd
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = formatter.format(dateDate);
		  return dateString;
	}

	/**
	 * 获取前一周周四日期
	 *
	 * @param date
	 * @return String
	 */
	public static String getPreWeekThuDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DATE,-4);
		return DateUtil.yyyy_mm_dd.format(cal.getTime());
	}

  /**
   * 星期日
   * 
   * @param date
   * @return
   */
  public static String getSunday(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	cal.add(Calendar.DATE, 6);
	return DateUtil.yyyy_mm_dd.format(cal.getTime());
  }

  /**
   * 取得某日期接下来一天的日期
   * 
   * @param strDate
   *            yyyy-mm-dd
   * @return yyyy-mm-dd
   */
  public static String getNextDay(String strDate) {
	String tmp = strDate + " " + "00:00";
	Calendar calendar = parseStringToCalendar(tmp);
	calendar.add(Calendar.DAY_OF_MONTH, 1);
	String nextDate = parseDateToString(calendar.getTime());
	return nextDate;
  }
  
  /**
   * 取得某日期前一天的日期
   * 
   * @param
   * @return yyyy-mm-dd
   */
//  public static String getPreDayNoSecond() {
//	Calendar calendar = Calendar.getInstance();
//	calendar.setTime(new Date());
//	calendar.add(Calendar.DAY_OF_YEAR, -1);
//	String preDate= DateParser.formatDate(calendar.getTime(), "MM-dd");
//	return preDate;
//  }

	/**
	 * 取得某日期前一天的日期
	 *
	 * @param
	 * @return yyyy-mm-dd
	 */
//	public static String getPreDayNoSecondCN() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		calendar.add(Calendar.DAY_OF_YEAR, -1);
//		String preDate=DateParser.formatDate(calendar.getTime(), "MM月dd日");
//		return preDate;
//	}


  /**
   * 取得某日期前一天的日期
   * 
   * @param strDate
   *            yyyy-mm-dd
   * @return yyyy-mm-dd
   */
  public static String getPreDay(String strDate) {
	String tmp = strDate;
	if(tmp == null || "".equals(tmp))
		  return null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();
	try {
	  Date date = dateFormat.parse(tmp);
	  calendar.setTime(date);
	} catch(ParseException e) {
		e.printStackTrace();
	  // e.printStackTrace();
	}
	calendar.add(Calendar.DAY_OF_MONTH, -1);
	String preDate = parseDateToString(calendar.getTime());
	return preDate;
  }

  /**
   * 把日历字符串转换为日历对象
   * 
   * @param strCal
   *            格式:"yyyy-mm-dd hh:ss"
   * @return
   */
  public static Calendar parseStringToCalendar(String strCal) {
	if(strCal == null || "".equals(strCal))
	  return null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	Calendar calendar = Calendar.getInstance();
	try {
	  Date date = dateFormat.parse(strCal);
	  calendar.setTime(date);
	} catch(ParseException e) {
		e.printStackTrace();
	  // e.printStackTrace();
	}
	return calendar;
  }

  /**
   * 把日期类型转换为字符串
   * 
   * @param date
   * @return yyyy-mm-dd
   */
  public static String parseDateToString(Date date) {
	if(date == null)
	  return "";
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	String year = String.valueOf(c.get(Calendar.YEAR));
	String month = "";
	String day = "";
	int imonth = c.get(Calendar.MONTH) + 1;
	if(imonth < 10) {
	  month = "0" + String.valueOf(imonth);
	} else {
	  month = String.valueOf(imonth);
	}
	int iday = c.get(Calendar.DATE);
	if(iday < 10) {
	  day = "0" + String.valueOf(iday);
	} else {
	  day = String.valueOf(iday);
	}
	return year + "-" + month + "-" + day;
  }


	/**
	 * 把日期类型转换为字符串
	 *
	 * @param date
	 * @return yyyy-mm-dd
	 */
	public static String parseDateToStringYYYYMM(Date date) {
		if(date == null)
			return "";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = "";
		int imonth = c.get(Calendar.MONTH) + 1;
		if(imonth < 10) {
			month = "0" + String.valueOf(imonth);
		} else {
			month = String.valueOf(imonth);
		}
		return year + "-" + month;
	}

  static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * 把日期类型转换为字符串
   * 
   * @param date
   * @return yyyy-mm-dd HH:MM:SS
   */
  public static String parseDatetTimeToString(Date date) {
	if(date == null)
	  return "";
	return dateTimeFormat.format(date);
  }

  /**
   * 取得本周周一日期
   * 
   * @param strDate
   * @return
   */
  public static String getNowWeekDay(String strDate) {
	Calendar c = Calendar.getInstance();
	c.setTime(parseStringToDate(strDate));
	int day = c.get(Calendar.DAY_OF_WEEK);
	int i;
	if(day == 1) {
	  i = -6;
	} else {
	  i = 2 - day;
	}
	c.add(Calendar.DAY_OF_MONTH, i);
	String date = parseDateToString(c.getTime());
	return date;
  }

  /**
   * 取得本周周日日期
   */
  public static String getEndWeekDay(String strDate) {
	Calendar c = Calendar.getInstance();
	c.setTime(parseStringToDate(strDate));
	int day = c.get(Calendar.DAY_OF_WEEK);
	int i;
	if(day == 1) {
	  i = 0;
	} else {
	  i = 8 - day;
	}
	c.add(Calendar.DAY_OF_MONTH, i);
	String date = parseDateToString(c.getTime());
	return date;
  }

  /**
   * 字符串日期,转换为java.util.Date类型
   * 
   * @param strDate
   * @return
   */
  public static Date parseStringToDate(String strDate) {
		if(strDate == null || "".equals(strDate) || "null".equalsIgnoreCase(strDate))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date parseStringToDate3(String strDate) {
		if(strDate == null || "".equals(strDate) || "null".equalsIgnoreCase(strDate))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

  public static Date parseStringToDate1(String strDate) {
	if(strDate == null || "".equals(strDate))
	  return null;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = null;
	try {
	  date = dateFormat.parse(strDate);
	} catch(ParseException e) {
	  e.printStackTrace();
	}
	return date;
  }

	public static Date parseStringToDate2(String strDate) {
		if(strDate == null || "".equals(strDate))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date parseStringToDate4(String strDate) {
		if(strDate == null || "".equals(strDate))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

  // 取这个月第一天
  public static String getCurMonthFirstDay() {

	Calendar gc = Calendar.getInstance();
	gc.set(Calendar.DAY_OF_MONTH, 1);
	String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());
	return firstDay10;
  }
  
  /***
   * 取某月最后一天
   * @param date
   * @return
   */
  public static Date getCurMonthLastDay(Date date) {

	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.MONTH, 1);
	cal.set(Calendar.DATE, 1);
	cal.add(Calendar.DATE, -1);
	return cal.getTime();
  }

  // 取这个月最后一天
  public static String getCurMonthLastDay() {

	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, 1);
	cal.set(Calendar.DATE, 1);
	cal.add(Calendar.DATE, -1);
	String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	return firstDay10;
  }

  // 取上个月第一天
  public static String getPreMonthFirstDay() {

	Calendar gc = Calendar.getInstance();
	gc.set(Calendar.DAY_OF_MONTH, 1);
	gc.add(Calendar.MONTH, -1);
	String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());

	return "";
  }


	// 取指定时间的上个月第一天
	public static String getPreMonthFirstDay(String ym) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar gc = Calendar.getInstance();
		try {
			gc.setTime(f.parse(ym));
			gc.set(Calendar.DAY_OF_MONTH, 1);
			gc.add(Calendar.MONTH, -1);
		}catch (ParseException e){
		}
		String date = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());
		return date;
	}

  // 取上个月最后一天
  public static String getPreMonthLastDay() {

	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.DATE, 1);
	cal.add(Calendar.DATE, -1);
	String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	return firstDay10;
  }

	// 取指定月的最后一天
	public static String getMonthLastDay(String month) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar gc = Calendar.getInstance();
		try {
			gc.setTime(f.parse(month));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.set(Calendar.DATE, gc.getActualMaximum(Calendar.DATE));
		String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());

		return lastDay;
	}


	// 取指定月的最后一天
	public static String getMonthLastDay1(String month) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		Calendar gc = Calendar.getInstance();
		try {
			gc.setTime(f.parse(month));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.set(Calendar.DATE, gc.getActualMaximum(Calendar.DATE));
		String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());

		return lastDay;
	}

	// 取指定月的最后一天
	public static String getMonthLastDayYYYYMM(String month) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMM");
		Calendar gc = Calendar.getInstance();
		try {
			gc.setTime(f.parse(month));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.set(Calendar.DATE, gc.getActualMaximum(Calendar.DATE));
		String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());

		return lastDay;
	}


	// 取指定月的第一天
	public static String getMonthFirstDay1(String month) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		Calendar gc = Calendar.getInstance();
		try {
			gc.setTime(f.parse(month));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.set(Calendar.DATE, gc.getActualMinimum(Calendar.DATE));
		String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());

		return lastDay;
	}

  // 取下个月第一天
  public static String getNextMonthFirstDay() {

	// 获取下月
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	calendar.add(Calendar.MONTH, 1);
	Date theDate = calendar.getTime();

	// 下月的第一天
	calendar.setTime(theDate);
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	return firstDay10;
  }
  
  
  // 指定日期的下一个月的第一天
  public static String getNextMonthFirstDay(Date date) {

	// 获取下月
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	calendar.add(Calendar.MONTH, 1);
	Date theDate = calendar.getTime();

	// 下月的第一天
	calendar.setTime(theDate);
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	String firstDay10 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	return firstDay10;
  }

	/**
	 * 获取当前月
	 * @return
	 */
	public static String getCurrMonth() {
		Calendar gc = Calendar.getInstance();
		gc.set(Calendar.DAY_OF_YEAR, 1);
		gc.set(Calendar.DAY_OF_MONTH, 2);
		String month = new SimpleDateFormat("yyyy-MM").format(gc.getTime());
		month = month.substring(5, 7);
		int m = Integer.valueOf(month);
		return String.valueOf(m);
	}


	/**
	 * 获取当前月
	 * @return
	 */
	public static String getCurrMonthTime() {
		Calendar gc = Calendar.getInstance();
		gc.set(Calendar.DAY_OF_YEAR, 1);
		gc.set(Calendar.DAY_OF_MONTH, 2);
		String month = new SimpleDateFormat("yyyy-MM").format(gc.getTime());
		return month;
	}

  // 去下个月最后一天
  public static String getNextMonthLastDay() {

	// 获取下月
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	calendar.add(Calendar.MONTH, 1);

	// 下月的最后一天
	calendar.add(Calendar.MONTH, 1);
	calendar.set(Calendar.DATE, 1);
	calendar.add(Calendar.DATE, -1);
	String lastDay10 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	return lastDay10;
  }

  /**
   * 判断两段日期是否冲突 false:A时间段与B时间段冲突 true：A时间段与B时间段不冲突
   */
  public static boolean checkDateConflict(Date beginA, Date endA, Date beginB, Date endB) {
	boolean flag = false;
	if(beginA.after(endB) || beginB.after(endA))
	  return true;
	else return false;
  }

  /**
   * 获取现在时间
   *
   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
   */
  public static Date getCurrtDate() {
	return new Date(System.currentTimeMillis());
  }

  /**
   * 获取当前年
   */
  public static String getCurrYear() {
	Calendar gc = Calendar.getInstance();
	gc.set(Calendar.DAY_OF_YEAR, 1);
	String year = new SimpleDateFormat("yyyy").format(gc.getTime());
	return year;
  }


  /**
   * 获取当前天
   * @return
   */
  public static String getCurrDay() {
	Calendar gc = Calendar.getInstance();
	gc.set(Calendar.DAY_OF_YEAR, 1);
	gc.set(Calendar.DAY_OF_MONTH, 2);
	String day = new SimpleDateFormat("yyyy-MM-dd").format(gc.getTime());
	day = day.substring(8, 9);
	int d = Integer.valueOf(day);
	return String.valueOf(d);
  }

	/**
	 * 获取当前天
	 * @return
	 */
	public static String getToDay() {
		Date dt=new Date();
		String day = new SimpleDateFormat("yyyy-MM-dd").format(dt);
		return day;
	}

	public static String getYesterday(){
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		Date time=cal.getTime();
		String day=new SimpleDateFormat("yyyy-MM-dd").format(time);
		return day;
	}

	/**
	 * 获取前日日期
	 */
	public static String getDayBeforeYesterday(){
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DATE,-2);
		Date time=cal.getTime();
		String day=new SimpleDateFormat("yyyy-MM-dd").format(time);
		return day;
	}
	public static String getYesterdayMonth(){
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		Date time=cal.getTime();
		String day=new SimpleDateFormat("yyyy-MM").format(time);
		return day;
	}

  /**
   * 获取上一年当月当日
   */
  public static String getLastYearAndMon(String ym) {
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
	Calendar gc = Calendar.getInstance();
	try {
	  gc.setTime(f.parse(ym));
	} catch(ParseException e) {
	  e.printStackTrace();

	}
	gc.add(Calendar.YEAR, -1);

	return f.format(gc.getTime());
  }
  
  /**
   * 获取上一年的日期
   */
  public static String getPreYearDate(String date) {
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	Calendar gc = Calendar.getInstance();
	try {
	  gc.setTime(f.parse(date));
	} catch(ParseException e) {
	  e.printStackTrace();

	}
	gc.add(Calendar.YEAR, -1);
	return f.format(gc.getTime());
  }
  
  /**
   * 获取下一年的日期
   */
  public static String getNextYearDate(String date) {
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	Calendar gc = Calendar.getInstance();
	try {
	  gc.setTime(f.parse(date));
	} catch(ParseException e) {
	  e.printStackTrace();

	}
	gc.add(Calendar.YEAR, +1);
	return f.format(gc.getTime());
  }

  /**
	 * 获取上一月
	 */
	public static String getLastMonth(String ym) {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try {
			gc.setTime(f.parse(ym));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.MONTH, -1);
		return new SimpleDateFormat("yyyy-MM").format(gc.getTime());
	}


	/**
	 * 获取上一月
	 */
	public static String getLastMonth() {
		Date date = new Date();
		Calendar gc = Calendar.getInstance();
		try {
			gc.setTime(new Date());
		} catch(Exception e) {
			e.printStackTrace();
		}
		gc.add(Calendar.MONTH, -1);
		return new SimpleDateFormat("yyyy-MM").format(gc.getTime());
	}

	/**
	 * 获取指定日期的当月
	 */
	public static String getCurrMonth1(String ym) {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try {
			gc.setTime(f.parse(ym));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.MONTH, 0);
		return new SimpleDateFormat("yyyy-MM").format(gc.getTime());
	}

	/**
	 * 获取指定日期的当月
	 */
	public static String getCurrLastYearMonth(String ym) {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try {
			gc.setTime(f.parse(ym));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.YEAR, -1);
		return new SimpleDateFormat("yyyy-MM").format(gc.getTime());
	}


	/**
	 * 获取指定日期的前年
	 */
	public static String getCurrLastYear(String ym) {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			gc.setTime(f.parse(ym));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.YEAR, -1);
		return new SimpleDateFormat("yyyy").format(gc.getTime());
	}

	/**
	 * 获取指定日期的前年日期
	 */
	public static String getCurrLastYearDay(String ymd) {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			gc.setTime(f.parse(ymd));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.YEAR, -1);
		return f.format(gc.getTime());
	}

	/**
	 * 获取指定日期的当前年
	 */
	public static String getCurrYear(String ymd) {
		Calendar gc = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			gc.setTime(f.parse(ymd));
		} catch(ParseException e) {
			e.printStackTrace();

		}
		gc.add(Calendar.YEAR, 0);
		return new SimpleDateFormat("yyyy").format(gc.getTime());
	}



  /**
   * 获取下一月
   */
  public static Date getNextMonth(String strDate) {
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	try {
		calendar.setTime(f.parse(strDate));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	calendar.add(Calendar.MONTH, 1);

	// 下月的最后一天
	calendar.add(Calendar.MONTH, 1);
	calendar.set(Calendar.DATE, 1);
	calendar.add(Calendar.DATE, -1);
	return calendar.getTime();//new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());//String
  }

  /**
   * 获取前一周的第一天
   * @return
   */
  public static String getFirstDayOfPreWeek() {
	return getFirstDayOfWeek(getCalendar(-7));
  }

  /***
   * 获取前一周的最后一天
   * @return
   */
  public static String getLastDayOfPreWeek() {
	return getLastDayWeek(getCalendar(-7));
  }

  /***
   * 时间计算
   * @param day
   * @return
   */
  public static Calendar getCalendar(int day) {
	Calendar calendar = GregorianCalendar.getInstance();
	calendar.add(5, day);
	return calendar;
  }

  /***
   * 获取一周第一天
   * @param calendar
   * @return
   */
  public static String getFirstDayOfWeek(Calendar calendar) {
	calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	int curDay = calendar.get(7);
	if(curDay == 1) {
	  calendar.add(5, -6);
	} else {
	  calendar.add(5, 2 - curDay);
	}
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	return format.format(calendar.getTime());
  }

  /***
   * 获取一周最后一天
   * @param calendar
   * @return
   */
  public static String getLastDayWeek(Calendar calendar) {
	calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	int curDay = calendar.get(7);
	if(curDay != 1) {
	  calendar.add(5, 8 - curDay);
	}

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	return format.format(calendar.getTime());
  }

  /***
   * 获得上个半月信息
   * @param args
   * @return
   */
  public static String[] getLastHalfMonth(String args) {
	String date = "";
	String[] strArray = new String[4];
	Calendar calendar = parseStringToCalendar(args + " 01:01");
	calendar.add(Calendar.DATE, -13);
	if(calendar.getTime().getDate() < 15) {
	  String date1 = DateUtil.parseDateToString(calendar.getTime());
	  date = date1.substring(0, date1.lastIndexOf("-") + 1) + "01";
	  strArray[0] = date;
	  date = date1.substring(0, date1.lastIndexOf("-") + 1) + "15";
	  strArray[1] = date;
	}
	if(calendar.getTime().getDate() > 15) {
	  String date1 = DateUtil.parseDateToString(calendar.getTime());
	  date = date1.substring(0, date1.lastIndexOf("-") + 1) + "16";
	  strArray[0] = date;
	  date = date1.substring(0, date1.lastIndexOf("-") + 1) + "31";
	  strArray[1] = date;
	}
	return strArray;
  }

  public static String getCurrentTime() {
	Calendar gc = Calendar.getInstance();
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
	return f.format(gc.getTime());
  }

  /**
   * 计算两个时间的相差月份数（参数为字符串格式）
   * 
   * @param date1 开始时间
   * @param date2 结束时间
   * @return
   * @throws Exception
   */
  public static int getMonthCountStr(String date1, String date2) {
	int result = 0;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	try {
	  Date begin = sdf.parse(date1);
	  Date end = sdf.parse(date2);
	  result = getMonthCountDate(begin, end);
	} catch(ParseException e) {
	  e.printStackTrace();
	}
	return result;
  }

  /**
   * 计算两个时间的相差月份数（参数为日期格式）
   * @param begin
   * @param end
   * @return
   * @throws Exception
   */
  public static int getMonthCountDate(Date begin, Date end) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int result = 0;
	Calendar c1 = Calendar.getInstance();
	Calendar c2 = Calendar.getInstance();
	String beginStr = sdf.format(begin);
	String endStr = sdf.format(end);
	try {
	  c1.setTime(sdf.parse(beginStr));
	  c2.setTime(sdf.parse(endStr));
	  if(c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
	  } else {
		result = 12 * (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) + c2.get(Calendar.MONTH)
			- c1.get(Calendar.MONTH);
	  }
	} catch(ParseException e) {
	  e.printStackTrace();
	}
	return result;
  }

  /**
   * 根据传入的时间获取其年月
   * @param date yyyy-MM-dd
   * @return yyyy-MM
   */
  public static String getCurrYearMonth(Date date) {
	if(date == null)
	  return "";
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	String year = String.valueOf(c.get(Calendar.YEAR));
	String month = "";
	int imonth = c.get(Calendar.MONTH) + 1;
	if(imonth < 10) {
	  month = "0" + String.valueOf(imonth);
	} else {
	  month = String.valueOf(imonth);
	}
	return year + "-" + month;
  }
  
  /**
   * 根据传入的时间获取上一个月的年月信息
   */
  public static String getUpYearMonth(Date date) {
		if(date == null)
		  return "";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = "";
		int imonth = c.get(Calendar.MONTH);
		if(imonth == 0){
			year = String.valueOf(c.get(Calendar.YEAR) - 1 );
			month = "12";
		}else	if(imonth < 10) {
		  month = "0" + String.valueOf(imonth);
		} else {
		  month = String.valueOf(imonth);
		}
		return year + "-" + month;
}
  
	/**  
     * 计算两个日期之间相差的天数  
     * @param smDate 较小的时间 
     * @param bDate  较大的时间 
     * @return 相差天数 
     * @throws ParseException
     */    
    public static int getDaysCountDate(Date smDate, Date bDate){
    	int result = 0;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(sdf.format(smDate));
	        Date date2 = sdf.parse(sdf.format(bDate));
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date1);
	        long time1 = cal.getTimeInMillis();
	        cal.setTime(date2);
	        long time2 = cal.getTimeInMillis();
	        long between_days=(time2-time1)/(1000*3600*24);
	        result = Integer.parseInt(String.valueOf(between_days));
	        if(between_days > 0){
	        	result = result+1;
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}  
       return result;
    }
    
    
    /**  
     * 计算两个日期之间相差的天数 (包含当天：2014-10-1减去2014-10-1相差天数1)
     * @param smDate 较小的时间 
     * @param bDate  较大的时间 
     * @return 相差天数 
     * @throws ParseException
     */    
    public static int getDaysCountDate1(Date smDate, Date bDate){
    	int result = 0;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(sdf.format(smDate));
	        Date date2 = sdf.parse(sdf.format(bDate));
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date1);
	        long time1 = cal.getTimeInMillis();
	        cal.setTime(date2);
	        long time2 = cal.getTimeInMillis();
	        long between_days=(time2)/(1000*3600*24) - (time1)/(1000*3600*24);
       		result = Integer.parseInt(String.valueOf(between_days));
        	result = result+1;
		} catch (ParseException e) {
			e.printStackTrace();
		}  
       return result;
    }

    /**
     * 计算相差的小时数
     * @param endDate
     * @param nowDate
     * @return
     */
    public static long getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;

        return day*8*60+hour*60+min;
    }
    
    /**
	 * 字符串的日期格式的计算
	 */
	public static int getDaysCountDate(String smDate, String bDate) {
		int result = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smDate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bDate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			result = Integer.parseInt(String.valueOf(between_days));
	        if(between_days > 0){
	        	result = result+1;
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 计算两个时间相差的年月日
	 *
	 * @param     src
	 * @param     dst
	 * @throws ParseException
	 */
	public static Integer[] compareYMD(Date src, Date dst) {
		Calendar srcCal = Calendar.getInstance();
		srcCal.setTime(src);
		Calendar dstCal = Calendar.getInstance();
		dstCal.setTime(dst);

		// 比较年月日
		int year = dstCal.get(Calendar.YEAR) - srcCal.get(Calendar.YEAR);
		int month = dstCal.get(Calendar.MONTH) - srcCal.get(Calendar.MONTH);
		int day = dstCal.get(Calendar.DAY_OF_MONTH)
				- srcCal.get(Calendar.DAY_OF_MONTH);
		// 实际年份差：
		year = year
				- ((month > 0) ? 0 : ((month < 0) ? 1 : ((day >= 0 ? 0 : 1))));
		// 实际月份差：
		month = (month <= 0) ? (day > 0 ? 12 + month : 12 + month - 1)
				: (day >= 0 ? month : month - 1);
		// 去除年月之后的剩余的实际天数差：
		dstCal.add(Calendar.MONTH, -1);
		day = (day <= 0) ? (perMonthDays(dstCal)) + day : day;
		String ages = year + "年" + month + "个月零" + day + "天";
		return new Integer[]{year,month,day};
	}

	/**
	 * 判断一个时间所在月有多少天
	 *
	 * @param cal  具体时间的日历对象
	 * @throws ParseException
	 */
	public static int perMonthDays(Calendar cal) {
		int maxDays = 0;
		int month = cal.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.MARCH:
		case Calendar.MAY:
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.OCTOBER:
		case Calendar.DECEMBER:
			maxDays = 31;
			break;
		case Calendar.APRIL:
		case Calendar.JUNE:
		case Calendar.SEPTEMBER:
		case Calendar.NOVEMBER:
			maxDays = 30;
			break;
		case Calendar.FEBRUARY:
			if (isLeap(cal.get(Calendar.YEAR))) {
				maxDays = 29;
			} else {
				maxDays = 28;
			}
			break;
		}
		return maxDays;
	}

	/**
	 * 判断某年是否是闰年
	 *
	 * @param year
	 *            年份
	 * @throws ParseException
	 */
	public static boolean isLeap(int year) {
		boolean leap = false;
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			leap = true;
		}
		return leap;
	}
	
	//new add zengqing-hws
	/**
	 * 计算两个时间相差的年月日 （租赁合同专用 ）
	 *
	 * @param  src
	 * @param  dst
	 * @throws ParseException
	 */
	public static int[] getNeturalAge(Date src, Date dst) {
		Calendar calendarBirth = Calendar.getInstance();
		calendarBirth.setTime(src);
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(dst);
		int diffYears = 0, diffMonths, diffDays;
		int dayOfBirth = calendarBirth.get(Calendar.DAY_OF_MONTH);
		int dayOfNow = calendarNow.get(Calendar.DAY_OF_MONTH)+1;//这里因为租赁合同的问题特意加了一天
		if (dayOfBirth <= dayOfNow) {
			diffMonths = getMonthsOfAge(calendarBirth, calendarNow);
			diffDays = dayOfNow - dayOfBirth;
			if (diffMonths == 0)
				diffDays++;//合同开始日和解约日为同一天时
		} else {
			if (isEndOfMonth(calendarBirth)) {
				if (isEndOfMonth(calendarNow)) {
					diffMonths = getMonthsOfAge(calendarBirth, calendarNow);
					diffDays = 0;
				} else {
					calendarNow.add(Calendar.MONTH, -1);
					diffMonths = getMonthsOfAge(calendarBirth, calendarNow);
					diffDays = dayOfNow + 1;
				}
			} else {
				if (isEndOfMonth(calendarNow)) {
					diffMonths = getMonthsOfAge(calendarBirth, calendarNow);
					diffDays = 0;
				} else {
					calendarNow.add(Calendar.MONTH, -1);// 上个月
					diffMonths = getMonthsOfAge(calendarBirth, calendarNow);
					// 获取上个月最大的一天
					int maxDayOfLastMonth = calendarNow
							.getActualMaximum(Calendar.DAY_OF_MONTH);
					if (maxDayOfLastMonth > dayOfBirth) {
						diffDays = maxDayOfLastMonth - dayOfBirth + dayOfNow;
					} else {
						diffDays = dayOfNow;
					}
				}
			}
		}
		// 计算月份时，没有考虑年
		diffYears = diffMonths / 12;
		diffMonths = diffMonths % 12;
		return new int[] { diffYears, diffMonths, diffDays };
	}

	/**
	 * 获取两个日历的月份之差
	 * 
	 * @param calendarBirth
	 * @param calendarNow
	 * @return
	 */
	public static int getMonthsOfAge(Calendar calendarBirth,
                                     Calendar calendarNow) {
		return (calendarNow.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR))* 12+ calendarNow.get(Calendar.MONTH)- calendarBirth.get(Calendar.MONTH);
	}

	/**
	 * 判断这一天是否是月底
	 * 
	 * @param calendar
	 * @return
	 */
	public static boolean isEndOfMonth(Calendar calendar) {
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth == calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
			return true;
		return false;
	}


	/**
	 * 判断这一天是否是月底
	 *
	 * @param date
	 * @return
	 */
	public static boolean isStartOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth == calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
			return true;
		return false;
	}
	
	/**
	 * 比较两个日期的大小
	 * @param src
	 * @param dst
	 * @return
	 */
	public static int compareDate(Date src, Date dst) {
		if (src.getTime() > dst.getTime()) {
            return 1;
        } else if (src.getTime() < dst.getTime()) {
            return -1;
        } else {
            return 0;
        }
	}

	public static int compareDate1(Date src, Date dst) {
		if(src.getTime() == dst.getTime()){
			return 1;
		}
		if (src.getTime() > dst.getTime()) {
			return 1;
		} else if (src.getTime() < dst.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 获得当前日期
	 * @return
	 */
	public static String getCurrDateStr(){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String dateStr = dateFormat.format( now );
		return dateStr;

	}
	
	/**
	 * 获得当前日期
	 * @return
	 */
	public static String getCurrDateStr(String format){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String dateStr = dateFormat.format( now );
		return dateStr;

	}
	
	/**
	 * 获得当前年月的上一个年月
	 * @return
	 */
	public static String getCurrLastMonth(){
		String currYm = getCurrYearMonth(getCurrtDate());
		return getLastMonth(currYm);
	}
	
	/**
	 * 比较时间的大小HH:mm
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static int  compareTime(String t1, String t2){
        Date date1 = null;
        Date date2 = null;
        DateFormat formart = new SimpleDateFormat("HH:mm");
        
        try {
			date1 = formart.parse(t1);
            date2 = formart.parse(t2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
      return  DateUtil.compareDate(date1, date2);
	  }

	/**
	 * 比较时间的大小HH:mm
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static int  compareData(String t1, String t2){
		Date date1 = null;
		Date date2 = null;
		DateFormat formart = new SimpleDateFormat("yyyyMMdd");

		try {
			date1 = formart.parse(t1);
			date2 = formart.parse(t2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return  DateUtil.compareDate(date1, date2);
	}




	/**
	 * 比较时间相差时数
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static int  differHour(String t1, String t2){
        Date date1 = null;
        Date date2 = null;
        DateFormat formart = new SimpleDateFormat("HH:mm");
        
        try {
			date1 = formart.parse(t1);
            date2 = formart.parse(t2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
        
        return hour1 - hour2;
	  }
	
	/**
	 * 增加小时数
	 * @param t
	 * @param hours
	 * @return
	 */
	public static String addHour(String t, int hours){
	  Date date = null;
      DateFormat formart = new SimpleDateFormat("HH:mm");
        
      try {
		date = formart.parse(t);
      } catch (ParseException e) {
		e.printStackTrace();
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(Calendar.HOUR_OF_DAY, hours);
      
      return formart.format(cal.getTime());
	}
	
	/**
	 * 获取日期的前一天
	 * @param date
	 * @return
	 */
	public static String getNextDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		String dateStr = DateUtil.parseDateToString(cal.getTime());
		return dateStr;
	}
	/**
	 * 获取日期的一天
	 * @param date
	 * @return
	 */
	public static String getLastDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		String dateStr = DateUtil.parseDateToString(cal.getTime());
		return dateStr;
	}
	/**
	 * 获得两个日期相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differDaysOfTwo(Date date1, Date date2) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date1);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(date2);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;

	}
	/**
	 * 获得两个日期相差的分钟数算出频率

	 * @return
	 */
	public static BigDecimal diffTimesOfTwodays(String date1, String date2, int
			timeRate) {
		BigDecimal num= new BigDecimal(1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(timeRate==0){
            return num;
		}
		try{
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);

			long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			num= new BigDecimal((hours*60)/timeRate);
		}catch (Exception e){
			e.printStackTrace();
			return num;
		}
		return num;
	}

	public static String lastDay(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return df.format(calendar.getTime());
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	/*public static boolean isWeekend(String year, String month, String day) {
		boolean isWeekend = false;
		if(StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month) && StringUtils.isNotBlank(day)){
			String strDate = year + "-" + StringUtils.leftPad(month, 2, '0') + "-"+ StringUtils.leftPad(day, 2, '0');
			Date bdate = DateUtil.parseStringToDate(strDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(bdate);
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				isWeekend = true;
			} else{
				isWeekend = false;
			}
		}
		return isWeekend;
	}*/
	
	/**
	 * 将日期转成周几
	 * @param date
	 * @return 
	 * String
	 * @exception 
	 */
	public static String getWeekDay(Date date){
		String[] weeks = {"日","一","二","三","四","五","六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){  
            week_index = 0;  
        }   
        return weeks[week_index]; 
    } 
	
	/**
	 * 和当前时间相差分钟数
	 * @param date
	 * @return
	 */
	public static long diffMinute(Date date){
		return diffMinute(DateUtil.getCurrtDate(),date);
	}
	
	
	/**
	 * 
	 *  获取当前属于第几个季度（个人绩效考核网批中用到）
	 *  （ 1）      第1季度为：3月25日-4月30日
	 *	（2）       半年度为：6月25 日-7月31日
	 *	（3）       第3季度为：9月18 日-10月31日
	 *	（4）       年度为：12月 25日-1月31日
	 * @return
	 * @throws Exception
	 */
	public static String getQuarter() throws Exception {

		Date currTime=new Date();
		Calendar calender = Calendar.getInstance();
		int year=calender.get(Calendar.YEAR);

		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		
		Date quarter1_begin=sdf.parse(year+"0325000000");
		Date quarter1_end=sdf.parse(year+"0430235959");
	
		if(currTime.before(quarter1_end)&& currTime.after(quarter1_begin)){
			
			return "1-第一季度";
		}
		
		
		Date quarter2_begin=sdf.parse(year+"0625000000");
		Date quarter2_end=sdf.parse(year+"0731235959");
		
		
		if(currTime.before(quarter2_end)&& currTime.after(quarter2_begin)){
			return "2-第二季度";
		}
		
		
		Date quarter3_begin=sdf.parse(year+"0918000000");
		Date quarter3_end=sdf.parse(year+"1031235959");
		
		if(currTime.before(quarter3_end)&& currTime.after(quarter3_begin)){
			return "3-第三季度";
		}
		
		
		Date quarter4_begin=sdf.parse(year+"1225000000");
		Date quarter4_end=sdf.parse((year+1)+"0131235959");
		
		
		if(currTime.before(quarter4_end)&& currTime.after(quarter4_begin)){
			return "4-年度";
		}
		
		return " - ";
	
	}
	
	public static long diffMinute(Date date1, Date date2){
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
		long minute= 0l;
		if(date1 != null && date2 != null){
			long timeOne=date1.getTime();
			long timeTwo=date2.getTime();
			long diff = timeTwo-timeOne;
			long day = diff / nd;
		    // 计算差多少小时
		    long hour = diff % nd / nh;
			long minute1=diff% nd % nh / nm;//转化minute diff % nd % nh / nm;
			minute = day*24*60+hour*60+minute1;
		}
		return minute;
	}
	
	/**
     * 获取下一个审批时间
     * @param start 网批发起时间
     * @param end 网批第一个审批人审批时间
     * @param num 需要填入的审批节点个数
     * @param nowNum 当前第几个填入审批时间的审批节点
     * @return
     */
    public static Date getNextApproveDate(Date start, Date end, int num, int nowNum) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(start);
		long startMi = cal.getTimeInMillis();
		cal.setTime(end);
		long endMi = cal.getTimeInMillis();
		
		long ca = endMi - startMi;
		
		long middle = Math.round(ca/num);
		
		long diff = Math.round((Math.random()*middle) + middle*(nowNum-1));
		diff = diff/1000/60; // 分
		int diffMi = 0;
		cal.setTime(start);
		if (diff == 0) {
			diff *= 60;
			diffMi = Integer.parseInt(diff+""); // 秒
			cal.add(Calendar.SECOND, diffMi);
		} else {
			try {
				diffMi = Integer.parseInt(diff+""); // 分
				cal.add(Calendar.MINUTE, diffMi);
				cal.set(Calendar.SECOND, (int)(Math.random()*60));
			} catch (Exception e) {
				diffMi = Integer.parseInt(diff/60 + ""); // 小时
				cal.add(Calendar.HOUR, diffMi);
				cal.set(Calendar.MINUTE, (int)(Math.random()*60));
				cal.set(Calendar.SECOND, (int)(Math.random()*60));
			}
		}
		return cal.getTime();
    }

    public static String getDailyCalendar(List<Map<Date, BigDecimal>> lists, Date yearMonth){
		Map calendarMap= new LinkedHashMap<String, Object>();
		Calendar firstWeekCalendar= Calendar.getInstance();
		Calendar lastWeekCalendar= Calendar.getInstance();
		firstWeekCalendar.setTime(yearMonth);
		lastWeekCalendar.setTime(yearMonth);
		firstWeekCalendar.set(Calendar.DAY_OF_MONTH,firstWeekCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		lastWeekCalendar.set(Calendar.DAY_OF_MONTH,lastWeekCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		int dayIndex=1;
		while (firstWeekCalendar.getTime().before(lastWeekCalendar.getTime())){
			calendarMap.put(dayIndex,firstWeekCalendar.getTime());
			firstWeekCalendar.add(Calendar.DAY_OF_YEAR,1);
			dayIndex++;
		}
		calendarMap.put(dayIndex , firstWeekCalendar.getTime());
		firstWeekCalendar.setTime((Date) calendarMap.get(1));
		int firstDayOfweek = firstWeekCalendar.get(firstWeekCalendar.DAY_OF_WEEK);
		int lastDayOfWeek = lastWeekCalendar.get(lastWeekCalendar.DAY_OF_WEEK);
		StringBuffer buf = new StringBuffer("<tr>");
		int w = 0;
		for (; w < (firstDayOfweek - 1); w++) {
			buf.append("<td></td>");
		}
		for(Map.Entry obj:(Set<Map.Entry>)calendarMap.entrySet()){
			if (w % 7 == 0) {
				buf.append("</tr>");
				buf.append("<tr>");
			}
			buf.append(dataFormat(lists,obj));
			w++;
		}
		w = lastDayOfWeek;
		for (; w < 7; w++) {
			buf.append("<td></td>");
		}
		buf.append("</tr>");
    	return buf.toString();
	}

	private static String dataFormat(List<Map<Date, BigDecimal>> list, Map.Entry obj){
		StringBuffer str=new StringBuffer("<td>");
		str.append("<div class=\"calendar-date\">"+obj.getKey()+"</div>");
		str.append("<div class=\"calendar-tip\">");
		for (Map maps : list) {
			for(Map.Entry map:(Set<Map.Entry>)maps.entrySet()){
				if(((Date)map.getKey()).compareTo((Date) obj.getValue())==0){
					String[] tmpStr=map.getValue().toString().split("/");
					if(tmpStr!=null) {
						for (int i=0;i<tmpStr.length;i++) {
							switch (i){
								case 0:str.append("<div class=\"calendar-red\">"+tmpStr[i]+"</div>");break;
								case 1:str.append("<div class=\"calendar-green\">"+tmpStr[i]+"</div>");break;
							}
						}
					}else {
						str.append("<div class=\"calendar-red\">"+obj.getValue()+"</div>");
					}
					break;
				}
			}
		}
		str.append("</div>");
		str.append("</td>");
		return str.toString();
	}

	/**
	 *获取传入日期下个月
	 * @param date 传入日期
	 * @param format 时间转成字符串的格式，例如:yyyy-MM
	 * @return
	 */
	public static String getNextMonth(Date date, String format){
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,1);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 今天是否在指定日期的前n天内 for 明源项目
	 * @param specifiedDate 指定日期
	 * @param days
	 * @return
	 */
	public static boolean todayIsWithinSpecifiedDate(Date specifiedDate, int days) {
		if (null == specifiedDate) {
			return false;
		}
		Calendar cal= Calendar.getInstance();
		Date today = new Date();
		cal.setTime(specifiedDate);
		cal.add(Calendar.DATE, -days);
		Date beginTime = cal.getTime();
		if (today.after(beginTime) && today.before(specifiedDate)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if(date1 != null && date2 != null) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			return isSameDay(cal1, cal2);
		}
		return false;
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if(cal1 != null && cal2 != null) {
			return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
		}
		return false;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smDate 较小的时间
	 * @param bDate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int getDaysCountDateForMy(Date smDate, Date bDate){
		int result = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(sdf.format(smDate));
			Date date2 = sdf.parse(sdf.format(bDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			long time1 = cal.getTimeInMillis();
			cal.setTime(date2);
			long time2 = cal.getTimeInMillis();
			long between_days=(time2-time1)/(1000*3600*24);
			result = Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 比较日期获取最小日期
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date getMinDate(Date date1, Date date2){
		if (date1 == null && date2 == null){
			return null;
		}else if (date1 != null && date2 == null){
			return date1;
		}else if (date1 == null && date2 != null){
			return date2;
		}else {
			return date1.compareTo(date2) > 0?date2:date1;
		}
	}


	/**
	 * 获得两个日期相差的时间

	 * @return
	 */
	public static BigDecimal diffDayOfTwodays(String date1, String date2) {
		BigDecimal num= new BigDecimal(1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try{
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);

			num= new BigDecimal(days);
		}catch (Exception e){
			e.printStackTrace();
			return num;
		}
		return num;
	}

	/**
	 * 获得两个日期相差的时间

	 * @return
	 */
	public static Integer diffDayOfTwoDate(Date date1, Date date2) {
		Integer num = 0;
		try{
			Long diff = date2.getTime() - date1.getTime();//这样得到的差值是微秒级别
			Long days = diff / (1000 * 60 * 60 * 24);

			num = days.intValue();
		}catch (Exception e){
			e.printStackTrace();
			return num;
		}
		return num;
	}
}
