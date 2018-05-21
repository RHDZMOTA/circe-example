package com.rhdzmota.example.messaging.model

sealed trait Message {
  def timestamp: Int
}

case class Text(timestamp: Int, content: String) extends Message
case class WithAttachment(timestamp: Int, content: Option[String], attachment: Attachment) extends Message
