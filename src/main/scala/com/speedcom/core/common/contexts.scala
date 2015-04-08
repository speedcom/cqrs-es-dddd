package com.speedcom.core.common

import scala.concurrent.Future

object Contexts {
  type SingleThreaded[T] = T
  type Concurrent[T] = Future[T]
}
