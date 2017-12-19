package com.templete.squirrel.common

import slick.jdbc.MySQLProfile.api._


trait DBsource {
  val db = Database.forConfig("dbmysql")
}

