package com.templete.squirrel.util

/*
 * Copyright 2015 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import akka.http.javadsl.marshallers.jackson.Jackson
import akka.http.scaladsl.marshalling._
import akka.http.scaladsl.model.ContentTypeRange
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import akka.util.ByteString
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.json4s.jackson.{ Json4sScalaModule}

import scala.collection.immutable.Seq
import scala.reflect.ClassTag

/**
  * Automatic to and from JSON marshalling/unmarshalling usung an in-scope Jackon's ObjectMapper
  */
object JacksonSupport extends JacksonSupport {

  val defaultObjectMapper: ObjectMapper =
    new ObjectMapper().registerModule(Json4sScalaModule)
}

/**
  * JSON marshalling/unmarshalling using an in-scope Jackson's ObjectMapper
  */
trait JacksonSupport {
  import JacksonSupport._

  def unmarshallerContentTypes: Seq[ContentTypeRange] =
    List(`application/json`)

  private val jsonStringUnmarshaller =
    Unmarshaller.byteStringUnmarshaller
      .forContentTypes(unmarshallerContentTypes: _*)
      .mapWithCharset {
        case (ByteString.empty, _) => throw Unmarshaller.NoContentException
        case (data, charset)       => data.decodeString(charset.nioCharset.name)
      }

  /**
    * HTTP entity => `A`
    */
  implicit def unmarshaller[A](
                                implicit ct: ClassTag[A],
                                objectMapper: ObjectMapper = defaultObjectMapper
                              ): FromEntityUnmarshaller[A] =
    jsonStringUnmarshaller.map(
      data => objectMapper.readValue(data, ct.runtimeClass).asInstanceOf[A]
    )

  /**
    * `A` => HTTP entity
    */
  implicit def marshaller[Object](
                                   implicit objectMapper: ObjectMapper = defaultObjectMapper
                                 ): ToEntityMarshaller[Object] =
    Jackson.marshaller[Object](objectMapper)
}
