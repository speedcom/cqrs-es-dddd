package com.speedcom.core.common

import scalaz.{OptionT, \/}

object Types {
  type Error[+A] = \/[String, A]
  type Result[A] = OptionT[Error, A]
}