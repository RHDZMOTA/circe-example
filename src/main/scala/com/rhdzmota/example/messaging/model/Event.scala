package com.rhdzmota.example.messaging.model

case class Event(sender: Sender, receiver: Receiver, messages: List[Message])

case class Sender(id: String)
case class Receiver(id: String)
