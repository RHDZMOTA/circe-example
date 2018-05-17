package com.rhdzmota.example.messaging.model

sealed trait Message {
  def timestamp: Int
  def content: Option[String]
}

case class Text(timestamp: Int, content: Option[String]) extends Message
case class WithAttachment(timestamp: Int, content: Option[String], attachment: Option[Attachment]) extends Message
