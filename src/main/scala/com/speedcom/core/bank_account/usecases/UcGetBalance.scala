package com.speedcom.core.bank_account.usecases

import com.speedcom.Domain.BankAccount
import com.speedcom.TransactionOperation
import com.speedcom.core.bank_account.boundary.TransactionHistoryFinder

import scalaz._
import Scalaz._

case class UcGetBalance(bankAccountFinder: TransactionHistoryFinder) extends (BankAccount => Float) {
  def apply(bankAccount: BankAccount): Float = {

    val trans = bankAccountFinder.findByBankAccount(bankAccount)

    val m = for {
      th <- TransactionOperation.balance
    } yield th

    val (th, balance) = m.run(trans)

    balance
  }
}
