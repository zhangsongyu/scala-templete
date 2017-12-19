package com.templete.squirrel.common

import org.json4s.{DefaultFormats, native}

trait JsonFormats {
  implicit val serialization = native.Serialization
  implicit val formats = DefaultFormats
}