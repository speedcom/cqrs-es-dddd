package com.speedcom


import com.speedcom.Domain.{Bank, BankAccount, TransactionHistory}

import scalaz._
import Scalaz._

package object Domain {
  type BankAccount = String
  type TransactionHistory = List[Float]
  type Bank = Map[BankAccount, TransactionHistory]
}

package object StateDomain {
  type Tx[A] = State[TransactionHistory, A]
}

object TransactionOperation {
  import StateDomain.Tx

  def contribute(x: Float): Tx[Unit] = State { th =>
    (th :+ x, ())
  }

  def balance: Tx[Float] = State { th =>
    (th, th.sum)
  }

  def deduct(x: Float): Tx[Float] = State { th =>
    if(th.sum >= x)
      (th :+ (-x), x)
    else
      (th, 0)
  }

  def deposit(x: Float): Tx[(Float, Float)] =
    for {
      _ <- contribute(x)
      b <- balance
    } yield (0f, b)

  def withdraw(x: Float): Tx[(Float, Float)] =
    for {
      y <- deduct(x)
      b <- balance
    } yield (y, b)

  def depositThenWithdraw(d: Float, w: Float): Tx[(Float, Float)] =
    for {
      _  <- deposit(d)
      ww <- withdraw(w)
    } yield ww

}

object BankApp extends App with Module {

  println(s"Balance before operation: ${ucGetBalance("Matt")}")

  val (oldTh, newTh) = ucContributeCash("Matt", 100f)
  println(s"Old Th: $oldTh")
  println(s"New Th: $newTh")

  println(s"Balance after operation: ${ucGetBalance("Matt")}")

}