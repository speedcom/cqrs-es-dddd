package com.speedcom.core.bank_account.usecases

import com.speedcom.Domain.{BankAccount, TransactionHistory}
import com.speedcom.TransactionOperation
import com.speedcom.core.bank_account.boundary.BankAccountFinder

import scalaz._
import Scalaz._

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