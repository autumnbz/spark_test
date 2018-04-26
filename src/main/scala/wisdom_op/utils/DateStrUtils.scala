package wm.utils

import org.joda.time.Days
import org.joda.time.format.DateTimeFormat

object DateStrUtils {
  def dateBefore(dateStr: String, ago: Int): String = {
    val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
    formatter.parseDateTime(dateStr).minusDays(ago).toString(formatter)
  }

  def dateAfter(dateStr: String, ago: Int): String = {
    val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
    formatter.parseDateTime(dateStr).plusDays(ago).toString(formatter)
  }

  def dateRange(dateStart: String, dateEnd: String): List[String] = {
    val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
    val start = formatter.parseDateTime(dateStart)
    val end = formatter.parseDateTime(dateEnd)

    val days = Days.daysBetween(start, end).getDays

    (0 to days).map { i =>
      start.plusDays(i).toString(formatter)
    }.toList
  }
}
