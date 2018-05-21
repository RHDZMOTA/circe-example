package com.rhdzmota.example.messaging.model.implicits

import com.rhdzmota.example.messaging.model._
import io.circe.generic.extras.semiauto.deriveDecoder
import io.circe.generic.extras.Configuration
import io.circe.Decoder

object Decoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  implicit val decodeCoordinates: Decoder[Coordinates] = deriveDecoder[Coordinates]
  implicit val decodeImage: Decoder[Image] = deriveDecoder[Image]
  implicit val decodeDocument: Decoder[Document] = deriveDecoder[Document]
  implicit val decodeLocation: Decoder[Location] = deriveDecoder[Location]
  implicit val decodeAttachment: Decoder[Attachment] =
    Decoder[Location].map[Attachment](identity)
      .or(Decoder[Document].map[Attachment](identity))
      .or(Decoder[Image].map[Attachment](identity))
  implicit val decodeText: Decoder[Text] = deriveDecoder[Text]
  implicit val decodeWithAttachment: Decoder[WithAttachment] = deriveDecoder[WithAttachment]
  implicit val decodeMessage: Decoder[Message] =
    Decoder[WithAttachment].map[Message](identity)
      .or(Decoder[Text].map[Message](identity))
  implicit val decodeSender: Decoder[Sender] = deriveDecoder[Sender]
  implicit val decodeReceiver: Decoder[Receiver] = deriveDecoder[Receiver]
  implicit  val decodeEvent: Decoder[Event] = deriveDecoder[Event]
}
