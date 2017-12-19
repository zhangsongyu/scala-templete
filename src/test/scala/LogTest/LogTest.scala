package LogTest

import com.typesafe.scalalogging.Logger
import org.scalatest.WordSpec

class LogTest extends WordSpec {
  private[this] val logger = Logger(this.getClass)

  "log will be print" in {
    logger.info("server ready ...... ")
  }
}
