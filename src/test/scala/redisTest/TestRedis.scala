package redisTest

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.util.JsonParserDelegate
import com.fasterxml.jackson.databind.ObjectMapper
import com.redis.{RedisClient, RedisClientPool}
import com.redis.serialization.Parse._
import com.redis.serialization.Format._
import org.junit.Test

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TestRedis {

  @Test
  def connRedis: Unit = {
    val r = new RedisClient(host = "104.224.158.43", port = 6379, secret = Some("redis"))
    r.set("key", "some value hahaha")
    assert(r.get("key").get == "some value hahaha")

    r.lpush("list-1", "foo")
    r.rpush("list-1", "bar")
    r.llen("list-1")
    r.lrange("list-1", 0, -1).foreach(println(_))

    r.hmset("hash", Map("field1" -> "1", "field2" -> 2))
    println(r.hmget[String, String]("hash", "field1", "field2").toString)
    /*
        import com.redis.serialization.Parse.Implicits.parseInt
        println(r.hmget[String, Int]("hash", "field1", "field2").toString)
    */

    r.set("Q", "q")
    println(r.get("Q"))
  }


  @Test
  def clientPool: Unit = {
    val clients = new RedisClientPool(host = "104.224.158.43", port = 6379, secret = Some("redis"))
    lp(List("1", "2", "3"))

    def lp(msgs: List[String]) = {
      clients.withClient {
        client => {
          msgs.foreach(client.lpush("list-l", _))
          val num = client.llen("list-l")
          println(num)
        }
      }
    }
  }


  @Test
  def redisConf: Unit = {
    val r = new RedisClient(host = "104.224.158.43", port = 6379, secret = Some("redis"))
    val info = r.info.getOrElse(None).toString

    val sc1 ="""(# )?([a-z,A-Z]*)?\r\n(\w*):(.*)""".r

    val rep1 =""""$2":{"$3":"$4",},"""

    val sr1 = """(# )?([a-z,A-Z]*)?\r\n(\w*):(.*)""".r.replaceAllIn(info.toCharArray, """"$2":{"$3":"$4",},""")

    val sc2 ="""},"":{"""

    val sr2 = "{" + sr1.replace(sc2.toCharArray, "") + "}"

    val sc3 =""",(\r\n)?}""".r
    val sr3 = sc3.replaceAllIn(sr2, "}")
    val result = "\r\n".r.replaceAllIn(sr3, "")

    /*    val map: java.util.HashMap[String, Any] = new java.util.HashMap
        val rr=new ObjectMapper().readValue(result, map.getClass)*/

    import org.json4s._
    import org.json4s.native.JsonMethods._
    val rj = parse(result)
    println(rj)
  }
}
