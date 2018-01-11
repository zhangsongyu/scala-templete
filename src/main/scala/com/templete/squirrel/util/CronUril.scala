package com.templete.squirrel.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import org.junit.Test
import org.quartz.TriggerUtils
import org.quartz.impl.calendar.CronCalendar
import org.quartz.impl.triggers.{CalendarIntervalTriggerImpl, CronTriggerImpl}

import scala.collection.JavaConverters

object CronUril {
  def getRunTimes(cron: String, numTimes: Option[Int]): Seq[String] = {
    val ct = new CronTriggerImpl()
    try {
      ct.setCronExpression(cron)
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val ld = TriggerUtils.computeFireTimes(ct, null, numTimes.getOrElse(5))
      val sld = JavaConverters.asScalaIteratorConverter(ld.iterator()).asScala.toSeq
      sld.map(dateFormat.format)
    } catch {
      case ex@_ => Seq()
    }
  }
}

class CronTest {
  @Test
  def cTest = {
    val li = CronUril.getRunTimes("0 15 10 4 * ?", Some(5))
    println(li)
  }

}