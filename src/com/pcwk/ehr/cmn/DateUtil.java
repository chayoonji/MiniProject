package com.pcwk.ehr.cmn;

import java.util.Calendar;

public class DateUtil {

	public static String toDateYM(Calendar calendar) {
		return String.format("%02d/%02d", calendar.get(Calendar.YEAR)
				,calendar.get(Calendar.MONTH)+1);
//				,calendar.get(Calendar.DAY_OF_MONTH) );
	}

}