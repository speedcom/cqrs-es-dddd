package com.speedcom.core.bank_account.usecases

import com.speedcom.Domain.BankAccount
import com.speedcom.TransactionOperation
import com.speedcom.core.bank_account.boundary.BankAccountFinder

import scalaz._
import Scalaz._

case class UcGetBalance(bankAccountFinder: BankAccountFinder) extends (BankAccount => Float) {
  def apply(bankAccount: BankAccount): Float = {

    val trans = bankAccountFinder.findAccount(bankAccount)

    val m = for {
      th <- TransactionOperation.balance
    } yield th

    val (th, balance) = m.run(trans)

    balance
  }
}
