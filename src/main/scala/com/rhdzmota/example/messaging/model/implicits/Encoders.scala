package com.rhdzmota.example.messaging.model.implicits

import com.rhdzmota.example.messaging.model._
import io.circe.generic.extras.semiauto.deriveEncoder
import io.circe.generic.extras.Configuration
import io.circe.Encoder
import io.circe.syntax._

object Encoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  implicit val encodeCoordinates: Encoder[Coordinates] = deriveEncoder[Coordinates]
  implicit val encodeImage: Encoder[Image] = deriveEncoder[Image]
  implicit val encodeDocument: Encoder[Document] = deriveEncoder[Document]
  implicit val encodeLocation: Encoder[Location] = deriveEncoder[Location]
  implicit val encodeAttachment: Encoder[Attachment] =
    Encoder.instance {
      case image @ Image(_) => image.asJson
      case document @ Document(_, _) => document.asJson
      case location @ Location(_, _) => location.asJson
    }
  implicit val encodeText: Encoder[Text] = deriveEncoder[Text]
  implicit val encodeWithAttachment: Encoder[WithAttachment] = deriveEncoder[WithAttachment]
  implicit val encodeMessage: Encoder[Message] =
    Encoder.instance {
      case text @ Text(_, _) => text.asJson
      case withAttachment @ WithAttachment(_, _, _) => withAttachment.asJson
    }
  implicit val encodeSender: Encoder[Sender] = deriveEncoder[Sender]
  implicit val encodeReceiver: Encoder[Receiver] = deriveEncoder[Receiver]
  implicit val encodeEvent: Encoder[Event] = deriveEncoder[Event]
}
