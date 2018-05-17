package com.rhdzmota.example.messaging.model

sealed trait Attachment {
  def url: String
}

case class Image(url: String) extends Attachment
case class Document(url: String, title: String) extends Attachment
case class Location(url: String, coordinates: Coordinates) extends Attachment

case class Coordinates(lat: Double, lon: Double)
