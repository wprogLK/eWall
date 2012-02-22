package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class WallUtil
{
	private static final String inputFormat = "dd.MM.yyyy";
	private static final String birthdayFormat = "dd.MM.yyyy";

	/** Converts a {@link String} to a {@link Date} value.
	 * @param userDateString String of the form of {@value #inputFormat} that should be parsed.
	 * @return The date value.
	 */
	public static Date convertStringToDate(String userDateString)
	{
		DateTimeFormatter dateTimeConvert = DateTimeFormat.forPattern(inputFormat);
		
		DateTime dateTimeParser = dateTimeConvert.parseDateTime(userDateString);
		return dateTimeParser.toDate();
	}
	
	public static String convertDateToString(Date date)
	{
		SimpleDateFormat simpleDateConverter = new SimpleDateFormat(inputFormat);
		if(date != null)
		{
			return simpleDateConverter.format(date);
		}
		else
		{
			return "-";
		}
	}
}
