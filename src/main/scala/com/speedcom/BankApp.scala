package com.speedcom


import com.speedcom.Domain.{Bank, BankAccount, TransactionHistory}
import com.speedcom.inmem.{InMemBankAccountFinder, BankRoot}

import scalaz._
import Scalaz._

package object Domain {
  type BankAccount = String
  type TransactionHistory = List[Float]
  type Bank = Map[BankAccount, TransactionHistory]
}

package object StateDomain {
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

trait BankAccountFinder {
  def findAccount(account: BankAccount): TransactionHistory
}

case class UcContributeCash(bankAccountFinder: BankAccountFinder)
  extends ((BankAccount, Float) => (TransactionHistory, TransactionHistory)) {

  def apply(bankAccount: BankAccount, cash: Float): (TransactionHistory, TransactionHistory) = {

    val oldTh: TransactionHistory = bankAccountFinder.findAccount(bankAccount)

    val m = for {
      _  <- TransactionOperation.contribute(cash)
      th <- TransactionOperation.balance
    } yield th

    val (newTh, _) = m.run(oldTh)

    (oldTh, newTh)
  }
}

case class UcGetBalance(bankAccountFinder: BankAccountFinder) extends (BankAccount => Float) {
  def apply(bankAccount: BankAccount): Float = {

    val trans = bankAccountFinder.findAccount(bankAccount)

    val m = for {
      th <- TransactionOperation.balance
    } yield th

    val (th, sum) = m.run(trans)

    sum

  }
}


object BankApp extends App with Module {

  println(s"Balance before operation: ${ucGetBalance("Matt")}")

  val (oldTh, newTh) = ucContributeCash("Matt", 100f)
  println(s"Old Th: $oldTh")
  println(s"New Th: $newTh")

  println(s"Balance after operation: ${ucGetBalance("Matt")}")

}