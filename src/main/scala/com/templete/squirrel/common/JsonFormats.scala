package com.templete.squirrel.common

import org.json4s.{DefaultFormats, jackson, native}

trait JsonFormats {
  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats

}