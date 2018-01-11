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
    val r = new RedisClient(host = "104.224.158.43", port = 6378, secret = Some("redis"))
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
   val rt= parse("""{"Server":{"redis_version":"3.2.0","redis_git_sha1":"00000000","redis_git_dirty":"0","redis_build_id":"38a237c39c46d54a","redis_mode":"standalone","os":"Linux 3.10.0-123.el7.x86_64 x86_64","arch_bits":"64","multiplexing_api":"epoll","gcc_version":"4.8.5","process_id":"27267","run_id":"57b38698e859513517d3a31d02165502f043fba9","tcp_port":"6379","uptime_in_seconds":"2495960","uptime_in_days":"28","hz":"10","lru_clock":"4909358","executable":"/data01/redis-3.2.0/src/redis-server","config_file":"/data01/redis-3.2.0/redis.conf"},"Clients":{"connected_clients":"1","client_longest_output_list":"0","client_biggest_input_buf":"0","blocked_clients":"0"},"Memory":{"used_memory":"923512","used_memory_human":"901.87K","used_memory_rss":"581632","used_memory_rss_human":"568.00K","used_memory_peak":"1536144","used_memory_peak_human":"1.46M","total_system_memory":"1930719232","total_system_memory_human":"1.80G","used_memory_lua":"37888","used_memory_lua_human":"37.00K","maxmemory":"0","maxmemory_human":"0B","maxmemory_policy":"noeviction","mem_fragmentation_ratio":"0.63","mem_allocator":"libc"},"Persistence":{"loading":"0","rdb_changes_since_last_save":"0","rdb_bgsave_in_progress":"0","rdb_last_save_time":"1514536350","rdb_last_bgsave_status":"ok","rdb_last_bgsave_time_sec":"0","rdb_current_bgsave_time_sec":"-1","aof_enabled":"0","aof_rewrite_in_progress":"0","aof_rewrite_scheduled":"0","aof_last_rewrite_time_sec":"-1","aof_current_rewrite_time_sec":"-1","aof_last_bgrewrite_status":"ok","aof_last_write_status":"ok"},"Stats":{"total_connections_received":"1882","total_commands_processed":"518348","instantaneous_ops_per_sec":"0","total_net_input_bytes":"19967600","total_net_output_bytes":"20465576","instantaneous_input_kbps":"0.00","instantaneous_output_kbps":"0.00","rejected_connections":"0","sync_full":"0","sync_partial_ok":"0","sync_partial_err":"0","expired_keys":"278","evicted_keys":"0","keyspace_hits":"94924","keyspace_misses":"8","pubsub_channels":"0","pubsub_patterns":"0","latest_fork_usec":"7508","migrate_cached_sockets":"0"},"Replication":{"role":"master","connected_slaves":"0","master_repl_offset":"0","repl_backlog_active":"0","repl_backlog_size":"1048576","repl_backlog_first_byte_offset":"0","repl_backlog_histlen":"0"},"CPU":{"used_cpu_sys":"1178.45","used_cpu_user":"632.13","used_cpu_sys_children":"0.78","used_cpu_user_children":"0.21"},"Cluster":{"cluster_enabled":"0"},"Keyspace":{"db0":"keys=83,expires=0,avg_ttl=0"}}""")
    println(rj)
  }
}
