package com.example.common

object BoolHelpers {
  implicit def optionToBool(opt: Option[_]): Boolean = opt.isDefined
}
