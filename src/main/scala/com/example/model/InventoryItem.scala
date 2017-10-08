package com.example.model

import java.util.UUID

import scala.beans.BeanProperty

case class InventoryItem(@BeanProperty name: String, @BeanProperty id: String = "", @BeanProperty var enabled: Boolean = true) {

  def this() {
    this("", "")
  }

  def this(name: String) {
    this(name, UUID.randomUUID().toString)
  }
}
