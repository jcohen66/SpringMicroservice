package com.example

import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}
import com.example.web.CurrencyController

class RestAPITest extends FlatSpec with Matchers with GivenWhenThen {
  behavior of "A microservice REST API"

  it should "test a rest api call" in {
    Given("the function for rest api")

    val curCtrl = new CurrencyController()

    When("the rest api returns information")

    val result = curCtrl.GetCurrencies()

    Then("it should match the expected output")

    assert(result.length > 0)
  }
}
