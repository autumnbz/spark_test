package wm.utils.ExtendOps

import org.joda.time.DateTime

object DateTimeOps {

  /**
    * 采用隐私转换成DateTime的方法
    *
    * @param datetime
    */
  implicit class DateTimeOps(datetime: DateTime) {
    def toDT: String = {
      datetime.toString("yyyy-MM-dd")
    }

    def toLongDT: String = {
      datetime.toString("yyyy-MM-dd HH:mm:ss")
    }
  }

}