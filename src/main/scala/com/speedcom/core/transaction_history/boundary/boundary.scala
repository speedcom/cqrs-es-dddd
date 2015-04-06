package com.speedcom.core.transaction_history.boundary

import com.speedcom.core.bank_account.boundary.{BankAccount, BankAccountNumber}

case class TransactionId(id: Long) extends AnyVal

case class Money(value: Float) extends AnyVal

case class Transaction(id: TransactionId,
                       number_1: BankAccountNumber,
                       number_2: BankAccountNumber,
                       cash: Money)

case class TransactionHistory(bankAccountNumber: BankAccountNumber,
                              transactions: List[Transaction])

trait TransactionHistoryFinder {
  def findByBankAccount(account: BankAccount): Option[TransactionHistory]
}