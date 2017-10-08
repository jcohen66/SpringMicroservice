package com.example.web

import org.json4s.DefaultFormats
import org.json4s.jackson.Json
import org.springframework.web.bind.annotation._

import scala.beans.BeanProperty
import scala.collection.mutable

case class Currency(@BeanProperty var code: String = "", @BeanProperty var description: String = "") {
  def this() = this("USD", "United States Dollar")
}

@RestController
@RequestMapping(Array("/currencies"))
class CurrencyController {

  var cur = mutable.MutableList[Currency] (
    Currency("USD", "United States Dollar"),
    Currency("CAD", "Canadian Dollar")
  )

  @RequestMapping(value = Array("all"), method = Array(RequestMethod.GET))
  def GetCurrencies() = {
    Json(DefaultFormats).write(cur)
  }

  @RequestMapping(value = Array("/{code"), method = Array(RequestMethod.GET) )
  def GetCurrency(@PathVariable("code") code: String) = {
    Json(DefaultFormats).write(cur.filter(x => x.code == code))
  }

  @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST))
  def AddCurrency(@RequestBody curr: Currency) = {
    Json(DefaultFormats).write(cur += curr)
  }

  @RequestMapping(value = Array("/delete/{code}"))
  def DeleteCurrency(@PathVariable("code") code: String) = {
    cur = cur.filter(x => (x.code != code))
    Json(DefaultFormats).write(cur)
  }
}
