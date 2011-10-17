/*
 * Copyright (c) 2011 Socialize Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.socialize.ui.util;

/**
 * @author Jason Polites
 * @deprecated Use DateUtils
 */
@Deprecated
public class TimeUtils {
	
	public static final long minute = 60;
	public static final long hour = minute*60;
	public static final long day = hour*24;
	public static final long month = day*30;
	public static final long year = day*365;
	
	public String getTimeString(long diffMilliseconds) {
		
		StringBuilder builder = new StringBuilder();
		
		long diff = diffMilliseconds / 1000;
		
		String unit = null;
		
		long value = 0;
		
		if(diff >= minute && diff < year) {
			
			// is minutes
			if(diff > hour) {
				if(diff > day) {
					
					if(diff > month) {
						unit = "month";
						value = diff / month;
					}
					else {
						unit = "day";
						value = diff / day;
					}
				}
				else {
					// Calc hours
					unit = "hour";
					value = diff / hour;
				}
			}
			else {
				// Calc minutes
				unit = "minute";
				value = diff / minute;
			}
			
			builder.append(value);
			builder.append(" ");
			builder.append(unit);
			
			if(value > 1) {
				builder.append("s");
			}
			
			builder.append(" ago");
		}
		else if(diff < minute) {
			builder.append("Just now");
		}
		else {
			builder.append("Over a year ago");
		}
		
		return builder.toString();
	}
	
}
