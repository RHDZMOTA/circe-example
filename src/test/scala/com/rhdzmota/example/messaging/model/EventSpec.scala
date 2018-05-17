package com.rhdzmota.example.messaging.model

import org.scalatest._

import io.circe.parser.decode
import io.circe.generic.auto._
import io.circe.syntax._

class EventSpec extends FlatSpec with Matchers {

  "An Event with text messages" should "serialize correctly" in {
    val sender = Sender("<SENDER-ID>")
    val receiver = Receiver("<RECEIVER-ID>")
    val messages = List(
      Text(1, Some("<TEXT-1>")),
      Text(2, Some("<TEXT-2>"))
    )
    val event = Event(sender, receiver, messages)
    val jsonString = "{" +
      "\"sender\": {\"id\": \"<SENDER-ID>\"}" +
      "\"receiver\": {\"id\": \"<RECEIVER-ID>\"}" +
      "\"messages\": [" +
          "{\"timestamp\": 1, \"content\": \"<TEXT-1>\"}," +
          "{\"timestamp\": 2, \"content\": \"<TEXT-2>\"}" +
        "]" +
      "}"
    decode[Event](jsonString).foreach(x => {
      x shouldBe event
      x.asJson shouldBe event.asJson
    })
  }

  "An Event with text messages and attachments" should "serialize correctly" in {
    val sender = Sender("<SENDER-ID>")
    val receiver = Receiver("<RECEIVER-ID>")
    val messages = List(
      Text(1, Some("<TEXT-1>")),
      Text(2, Some("<TEXT-2>")),
      WithAttachment(3, Some("<IMAGE-CAPTION>"), Some(Image("<IMAGE-URL>"))),
      WithAttachment(4, None, Some(Document("<DOCUMENT-URL>", "<DOCUMENT-TITLE>")))
    )
    val event = Event(sender, receiver, messages)
    val jsonString = "{" +
      "\"sender\": {\"id\": \"<SENDER-ID>\"}" +
      "\"receiver\": {\"id\": \"<RECEIVER-ID>\"}" +
      "\"messages\": [" +
          "{\"timestamp\": 1, \"content\": \"<TEXT-1>\"}," +
          "{\"timestamp\": 2, \"content\": \"<TEXT-2>\"}," +
          "{\"timestamp\": 3, \"content\": \"<IMAGE-CAPTION>\", {\"url\": \"<IMAGE-URL>\"}}," +
          "{\"timestamp\": 4, {\"url\": \"<DOCUMENT-URL>\", \"title\": \"<DOCUMENT-TITLE>\"}}" +
        "]" +
      "}"
    decode[Event](jsonString).foreach(x => {
      x shouldBe event
      x.asJson shouldBe event.asJson
    })
  }

}
