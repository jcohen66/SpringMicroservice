package com.example

import java.time.ZoneId
import java.util.Properties

import akka.actor.{ActorRef, ActorSystem}
import com.example.service.EchoService
import com.typesafe.scalalogging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.{ConfigurableEnvironment, PropertiesPropertySource}

@SpringBootApplication
class BarebonesMicroservice {

  val logger = Logger(this.getClass.getName)
  val actorSystem = ActorSystem.create("barebones-actorsystem")
  var actor: ActorRef = _

  @Bean
  def echoService(): EchoService = {
    new EchoService();
  }

  /**
    * Output for /info endpoint from Spring Actuator.
    * @param env
    */

  @Autowired
  def setInfoProperties(env: ConfigurableEnvironment): Unit = {
    val props = new Properties();
    props.put("info.timezone", ZoneId.systemDefault().toString)
    env.getPropertySources().addFirst(new PropertiesPropertySource("extra-info-props", props))
  }

}

object BarebonesMicroservice extends App {

  SpringApplication.run(classOf[BarebonesMicroservice])

}
