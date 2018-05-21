package com.rhdzmota.example.messaging.model

import org.scalatest._

import io.circe.parser.decode
import io.circe.syntax._

import com.rhdzmota.example.messaging.model.implicits.Decoders._
import com.rhdzmota.example.messaging.model.implicits.Encoders._

class EventSpec extends FlatSpec with Matchers {

  "An Event with text messages" should "serialize correctly" in {
    val sender = Sender("<SENDER-ID>")
    val receiver = Receiver("<RECEIVER-ID>")
    val messages = List(
      Text(1, "<TEXT-1>"),
      Text(2, "<TEXT-2>")
    )
    val event = Event(sender, receiver, messages)
    val jsonString =
      """{
        |"sender": {"id": "<SENDER-ID>"},
        |"receiver": {"id": "<RECEIVER-ID>"},
        |"messages": [
        |  {"timestamp": 1, "content": "<TEXT-1>"},
        |  {"timestamp": 2, "content": "<TEXT-2>"}
        |  ]
        |}""".stripMargin
    val result = decode[Event](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => fail(s"failure: ${error.toString}")
      case Right(x)    => 
        x shouldBe event
        x.asJson shouldBe event.asJson
    }
  }

  "An Event with text messages and attachments" should "serialize correctly" in {
    val sender = Sender("<SENDER-ID>")
    val receiver = Receiver("<RECEIVER-ID>")
    val messages = List(
      Text(1, "<TEXT-1>"),
      Text(2, "<TEXT-2>"),
      WithAttachment(3, Some("<IMAGE-CAPTION>"), Image("<IMAGE-URL>")),
      WithAttachment(4, None, Document("<DOCUMENT-URL>", "<DOCUMENT-TITLE>"))
    )
    val event = Event(sender, receiver, messages)
    val jsonString =
      """{
        |"sender": {"id": "<SENDER-ID>"},
        |"receiver": {"id": "<RECEIVER-ID>"},
        |"messages": [
        |  {"timestamp": 1, "content": "<TEXT-1>"},
        |  {"timestamp": 2, "content": "<TEXT-2>"},
        |  {"timestamp": 3, "content": "<IMAGE-CAPTION>", "attachment": {"url": "<IMAGE-URL>"}},
        |  {"timestamp": 4, "attachment": {"url": "<DOCUMENT-URL>", "title": "<DOCUMENT-TITLE>"}}
        |  ]
        |}""".stripMargin
    val result = decode[Event](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => fail(s"failure: ${error.toString}")
      case Right(x)    =>
        x shouldBe event
        x.asJson shouldBe event.asJson
    }
  }
}
