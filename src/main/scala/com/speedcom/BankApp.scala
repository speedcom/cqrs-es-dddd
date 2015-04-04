package com.speedcom


import scalaz._
import Scalaz._

package object Domain {
  type Account = String
  type TransactionHistory = List[Float]
  type Bank = Map[Account, TransactionHistory]
}

package object StateDomain {
  import Domain._
  type Tx[A] = State[TransactionHistory, A]
  type Bx[A] = State[Bank, A]
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

object BankOperation {
  import Domain._
  import StateDomain._

  def findAccount(account: Account): Bank => TransactionHistory = bank => bank(account)

}

// OUTER WORLD - MUTABLE ONE
object BankRoot {
  import Domain._

  var bank: Bank = Map(
    "Matt"   -> List(50f),
    "Justin" -> List(50f, 100f, -40f, 700f))

}

object UcContributeCash {
  import BankRoot._
  import Domain._

  def execute(bankAccount: Account, cash: Float): (TransactionHistory, TransactionHistory) = {

    val oldTh: TransactionHistory = BankOperation.findAccount(bankAccount)(bank)

    val m = for {
      _  <- TransactionOperation.contribute(cash)
      th <- TransactionOperation.balance
    } yield th

    val (newTh, _) = m.run(oldTh)

    (oldTh, newTh)
  }
}

object BankApp extends App {

  val (oldTh, newTh) = UcContributeCash.execute("Matt", 100f)
  println(s"Old Th: $oldTh")
  println(s"New Th: $newTh")
}