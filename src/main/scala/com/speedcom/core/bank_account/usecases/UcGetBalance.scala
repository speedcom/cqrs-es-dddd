package com.speedcom.core.bank_account.usecases

import com.speedcom.core.bank_account.boundary.BankAccount
import com.speedcom.core.transaction_history.boundary.{Money, TransactionHistoryFinder}

case class UcGetBalance(bankAccountFinder: TransactionHistoryFinder) extends (BankAccount => Money) {
  def apply(bankAccount: BankAccount): Money = ???
}
