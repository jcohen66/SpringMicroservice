package com.example.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import org.springframework.web.context.request.async.DeferredResult
import com.example.service.{EchoResponse, EchoService}
import com.example.concurrent.FutureAdapter._

@RequestMapping(Array("/echo"))
@RestController
class EchoController @Autowired()(service: EchoService){

  private implicit val executionContext = scala.concurrent.ExecutionContext.global

  @RequestMapping(method = Array(RequestMethod.GET))
  def echo(@RequestParam(value = "input", defaultValue = "turd") input: String): String = {
    service.echo(input)
  }

  @RequestMapping(path = Array("/future"), method = Array(RequestMethod.GET))
  def echoFuture(@RequestParam(required = false, name = "input") input: String): DeferredResult[String] = {
    service.echoFuture(input)
  }

  @RequestMapping(path = Array("/response"), method = Array(RequestMethod.GET))
  def echoResponse(@RequestParam(required = false, name = "input") input: String): EchoResponse = {
    service.echoResponse(input)
  }

  @RequestMapping(path = Array("/responseCollection"), method = Array(RequestMethod.GET))
  def echoResponseCollection(@RequestParam(required = false, name = "input") input: String): Map[String, Object] = {
    service.echoResponseCollection(input)
  }

  @RequestMapping(path = Array("/responseFuture"), method = Array(RequestMethod.GET))
  def echoFutureResponse(@RequestParam(required = false, name = "input") input: String): DeferredResult[EchoResponse] = {
    service.echoFutureResponse(input)
  }

  @RequestMapping(path = Array("/failedFuture"), method = Array(RequestMethod.GET))
  def failedFuture(@RequestParam(required = false, name = "input") input: String): DeferredResult[String] = {
    service.failedEcho(input)
  }
}
