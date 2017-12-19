package com.templete.squirrel.dbEntity
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Streamingdataset.schema ++ Toto.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Streamingdataset
    *  @param datasetid Database column dataSetId SqlType(BIGINT), PrimaryKey
    *  @param datasetname Database column dataSetName SqlType(VARCHAR), Length(50,true)
    *  @param datasetalias Database column dataSetAlias SqlType(VARCHAR), Length(255,true), Default(None)
    *  @param appid Database column appId SqlType(BIGINT)
    *  @param sourcetype Database column sourceType SqlType(VARCHAR), Length(255,true), Default()
    *  @param category Database column category SqlType(VARCHAR), Length(255,true)
    *  @param indatasetid Database column inDataSetId SqlType(BIGINT), Default(0)
    *  @param daydata Database column dayData SqlType(INT), Default(0)
    *  @param status Database column status SqlType(INT)
    *  @param outtopicname Database column outTopicName SqlType(VARCHAR), Length(255,true)
    *  @param outtopicsign Database column outTopicSign SqlType(INT), Default(1)
    *  @param remark Database column remark SqlType(VARCHAR), Length(2048,true), Default(None)
    *  @param created Database column created SqlType(BIGINT)
    *  @param createdby Database column createdBy SqlType(BIGINT)
    *  @param updated Database column updated SqlType(BIGINT)
    *  @param updatedby Database column updatedBy SqlType(BIGINT) */
  case class StreamingdatasetRow(datasetid: Long, datasetname: String, datasetalias: Option[String] = None, appid: Long, sourcetype: String = "", category: String, indatasetid: Long = 0L, daydata: Int = 0, status: Int, outtopicname: String, outtopicsign: Int = 1, remark: Option[String] = None, created: Long, createdby: Long, updated: Long, updatedby: Long)
  /** GetResult implicit for fetching StreamingdatasetRow objects using plain SQL queries */
  implicit def GetResultStreamingdatasetRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Int]): GR[StreamingdatasetRow] = GR{
    prs => import prs._
      StreamingdatasetRow.tupled((<<[Long], <<[String], <<?[String], <<[Long], <<[String], <<[String], <<[Long], <<[Int], <<[Int], <<[String], <<[Int], <<?[String], <<[Long], <<[Long], <<[Long], <<[Long]))
  }
  /** Table description of table streamingDataSet. Objects of this class serve as prototypes for rows in queries. */
  class Streamingdataset(_tableTag: Tag) extends profile.api.Table[StreamingdatasetRow](_tableTag, Some("testEoi"), "streamingDataSet") {
    def * = (datasetid, datasetname, datasetalias, appid, sourcetype, category, indatasetid, daydata, status, outtopicname, outtopicsign, remark, created, createdby, updated, updatedby) <> (StreamingdatasetRow.tupled, StreamingdatasetRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(datasetid), Rep.Some(datasetname), datasetalias, Rep.Some(appid), Rep.Some(sourcetype), Rep.Some(category), Rep.Some(indatasetid), Rep.Some(daydata), Rep.Some(status), Rep.Some(outtopicname), Rep.Some(outtopicsign), remark, Rep.Some(created), Rep.Some(createdby), Rep.Some(updated), Rep.Some(updatedby)).shaped.<>({r=>import r._; _1.map(_=> StreamingdatasetRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12, _13.get, _14.get, _15.get, _16.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column dataSetId SqlType(BIGINT), PrimaryKey */
    val datasetid: Rep[Long] = column[Long]("dataSetId", O.PrimaryKey)
    /** Database column dataSetName SqlType(VARCHAR), Length(50,true) */
    val datasetname: Rep[String] = column[String]("dataSetName", O.Length(50,varying=true))
    /** Database column dataSetAlias SqlType(VARCHAR), Length(255,true), Default(None) */
    val datasetalias: Rep[Option[String]] = column[Option[String]]("dataSetAlias", O.Length(255,varying=true), O.Default(None))
    /** Database column appId SqlType(BIGINT) */
    val appid: Rep[Long] = column[Long]("appId")
    /** Database column sourceType SqlType(VARCHAR), Length(255,true), Default() */
    val sourcetype: Rep[String] = column[String]("sourceType", O.Length(255,varying=true), O.Default(""))
    /** Database column category SqlType(VARCHAR), Length(255,true) */
    val category: Rep[String] = column[String]("category", O.Length(255,varying=true))
    /** Database column inDataSetId SqlType(BIGINT), Default(0) */
    val indatasetid: Rep[Long] = column[Long]("inDataSetId", O.Default(0L))
    /** Database column dayData SqlType(INT), Default(0) */
    val daydata: Rep[Int] = column[Int]("dayData", O.Default(0))
    /** Database column status SqlType(INT) */
    val status: Rep[Int] = column[Int]("status")
    /** Database column outTopicName SqlType(VARCHAR), Length(255,true) */
    val outtopicname: Rep[String] = column[String]("outTopicName", O.Length(255,varying=true))
    /** Database column outTopicSign SqlType(INT), Default(1) */
    val outtopicsign: Rep[Int] = column[Int]("outTopicSign", O.Default(1))
    /** Database column remark SqlType(VARCHAR), Length(2048,true), Default(None) */
    val remark: Rep[Option[String]] = column[Option[String]]("remark", O.Length(2048,varying=true), O.Default(None))
    /** Database column created SqlType(BIGINT) */
    val created: Rep[Long] = column[Long]("created")
    /** Database column createdBy SqlType(BIGINT) */
    val createdby: Rep[Long] = column[Long]("createdBy")
    /** Database column updated SqlType(BIGINT) */
    val updated: Rep[Long] = column[Long]("updated")
    /** Database column updatedBy SqlType(BIGINT) */
    val updatedby: Rep[Long] = column[Long]("updatedBy")
  }
  /** Collection-like TableQuery object for table Streamingdataset */
  lazy val Streamingdataset = new TableQuery(tag => new Streamingdataset(tag))

  /** Row type of table Toto */
  type TotoRow = HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HCons[Int,HNil]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for TotoRow providing default values if available in the database schema. */
  def TotoRow(a: Int = 0, s: Int = 0, d: Int = 0, f: Int = 0, g: Int = 0, h: Int = 0, j: Int = 0, k: Int = 0, l: Int = 0, q: Int = 0, w: Int = 0, e: Int = 0, r: Int = 0, t: Int = 0, y: Int = 0, u: Int = 0, i: Int = 0, o: Int = 0, p: Int = 0, z: Int = 0, x: Int = 0, c: Int = 0, v: Int = 0, b: Int = 0, n: Int = 0, m: Int = 0): TotoRow = {
    a :: s :: d :: f :: g :: h :: j :: k :: l :: q :: w :: e :: r :: t :: y :: u :: i :: o :: p :: z :: x :: c :: v :: b :: n :: m :: HNil
  }
  /** GetResult implicit for fetching TotoRow objects using plain SQL queries */
  implicit def GetResultTotoRow(implicit e0: GR[Int]): GR[TotoRow] = GR{
    prs => import prs._
      <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: <<[Int] :: HNil
  }
  /** Table description of table toto. Objects of this class serve as prototypes for rows in queries. */
  class Toto(_tableTag: Tag) extends profile.api.Table[TotoRow](_tableTag, Some("testEoi"), "toto") {
    def * = a :: s :: d :: f :: g :: h :: j :: k :: l :: q :: w :: e :: r :: t :: y :: u :: i :: o :: p :: z :: x :: c :: v :: b :: n :: m :: HNil

    /** Database column a SqlType(INT), PrimaryKey, Default(0) */
    val a: Rep[Int] = column[Int]("a", O.PrimaryKey, O.Default(0))
    /** Database column s SqlType(INT), Default(0) */
    val s: Rep[Int] = column[Int]("s", O.Default(0))
    /** Database column d SqlType(INT), Default(0) */
    val d: Rep[Int] = column[Int]("d", O.Default(0))
    /** Database column f SqlType(INT), Default(0) */
    val f: Rep[Int] = column[Int]("f", O.Default(0))
    /** Database column g SqlType(INT), Default(0) */
    val g: Rep[Int] = column[Int]("g", O.Default(0))
    /** Database column h SqlType(INT), Default(0) */
    val h: Rep[Int] = column[Int]("h", O.Default(0))
    /** Database column j SqlType(INT), Default(0) */
    val j: Rep[Int] = column[Int]("j", O.Default(0))
    /** Database column k SqlType(INT), Default(0) */
    val k: Rep[Int] = column[Int]("k", O.Default(0))
    /** Database column l SqlType(INT), Default(0) */
    val l: Rep[Int] = column[Int]("l", O.Default(0))
    /** Database column q SqlType(INT), Default(0) */
    val q: Rep[Int] = column[Int]("q", O.Default(0))
    /** Database column w SqlType(INT), Default(0) */
    val w: Rep[Int] = column[Int]("w", O.Default(0))
    /** Database column e SqlType(INT), Default(0) */
    val e: Rep[Int] = column[Int]("e", O.Default(0))
    /** Database column r SqlType(INT), Default(0) */
    val r: Rep[Int] = column[Int]("r", O.Default(0))
    /** Database column t SqlType(INT), Default(0) */
    val t: Rep[Int] = column[Int]("t", O.Default(0))
    /** Database column y SqlType(INT), Default(0) */
    val y: Rep[Int] = column[Int]("y", O.Default(0))
    /** Database column u SqlType(INT), Default(0) */
    val u: Rep[Int] = column[Int]("u", O.Default(0))
    /** Database column i SqlType(INT), Default(0) */
    val i: Rep[Int] = column[Int]("i", O.Default(0))
    /** Database column o SqlType(INT), Default(0) */
    val o: Rep[Int] = column[Int]("o", O.Default(0))
    /** Database column p SqlType(INT), Default(0) */
    val p: Rep[Int] = column[Int]("p", O.Default(0))
    /** Database column z SqlType(INT), Default(0) */
    val z: Rep[Int] = column[Int]("z", O.Default(0))
    /** Database column x SqlType(INT), Default(0) */
    val x: Rep[Int] = column[Int]("x", O.Default(0))
    /** Database column c SqlType(INT), Default(0) */
    val c: Rep[Int] = column[Int]("c", O.Default(0))
    /** Database column v SqlType(INT), Default(0) */
    val v: Rep[Int] = column[Int]("v", O.Default(0))
    /** Database column b SqlType(INT), Default(0) */
    val b: Rep[Int] = column[Int]("b", O.Default(0))
    /** Database column n SqlType(INT), Default(0) */
    val n: Rep[Int] = column[Int]("n", O.Default(0))
    /** Database column m SqlType(INT), Default(0) */
    val m: Rep[Int] = column[Int]("m", O.Default(0))
  }
  /** Collection-like TableQuery object for table Toto */
  lazy val Toto = new TableQuery(tag => new Toto(tag))
}
