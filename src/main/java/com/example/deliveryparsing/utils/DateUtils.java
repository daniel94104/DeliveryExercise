package com.example.deliveryparsing.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {
  private final int ONE_CENTURY_LESS = -100;
  private Logger logger = LoggerFactory.getLogger(DateUtils.class);

  public Optional<Date> convertTwoDigitYearDateToStandardDate(String dateStringForConvert) {
    var sourceDateFormat = new SimpleDateFormat("MM/dd/yy");
    var cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, ONE_CENTURY_LESS);
    sourceDateFormat.set2DigitYearStart(cal.getTime());
    try {
      return Optional.of(sourceDateFormat.parse(dateStringForConvert));
    } catch (Exception e) {
      logger.error("Fail to parse the date: " + dateStringForConvert);
      return Optional.empty();
    }
  }
}
