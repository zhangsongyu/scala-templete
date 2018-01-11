package com.templete.squirrel.util

import com.redis.RedisClient

object RedisUtil {
  def redisConf: String = {
    val r = new RedisClient(host = "104.224.158.43", port = 6379, secret = Some("redis"))
    val info = r.info.getOrElse(None).toString

    val sc1 ="""(# )?([a-z,A-Z]*)?\r\n(\w*):(.*)""".r

    val rep1 =""""$2":{"$3":"$4",},"""

    val sr1 = """(# )?([a-z,A-Z]*)?\r\n(\w*):(.*)""".r.replaceAllIn(info.toCharArray, """"$2":{"$3":"$4",},""")

    val sc2 ="""},"":{"""

    val sr2 = "{" + sr1.replace(sc2.toCharArray, "") + "}"

    val sc3 =""",(\r\n)?}""".r
    val sr3 = sc3.replaceAllIn(sr2, "}")
    r.quit
     "\r\n".r.replaceAllIn(sr3, "")
  }
}
