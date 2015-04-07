package com.speedcom.core.bank_account.usecases

import com.speedcom.core.bank_account.boundary.{BankAccountNumber, BankAccount}
import com.speedcom.core.transaction_history.boundary.{Money, TransactionHistoryFinder}

case class UcGetBalance(transactionHistoryFinder: TransactionHistoryFinder)
  extends (BankAccountNumber => Option[Money]) {

  def apply(number: BankAccountNumber): Option[Money] = for {
    th <- transactionHistoryFinder.findByBankAccountNumber(number)
    sum = th.transactions.foldLeft(0f) { case (balance, t) => balance + t.cash.value }
  } yield Money(sum)
}
