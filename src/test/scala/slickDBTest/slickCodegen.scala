package slickDBTest


object codeGeneration extends App{
  slick.codegen.SourceCodeGenerator.main(
    Array("slick.jdbc.MySQLProfile","com.mysql.jdbc.Driver", "jdbc:mysql://104.224.158.43:3306/testEoi", "./", "dao", "bwg", "mysql")
  )
}