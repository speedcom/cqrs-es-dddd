package com.speedcom.core.transaction_history.boundary

import com.speedcom.core.bank_account.boundary.BankAccountNumber

case class TransactionId(id: Long) extends AnyVal

case class Money(value: Float) extends AnyVal

case class Transaction(id: TransactionId,
                       withWho: BankAccountNumber,
                       cash: Money)

case class TransactionHistory(bankAccountNumber: BankAccountNumber,
                              transactions: List[Transaction])

trait TransactionHistoryFinder {
  def findByBankAccountNumber(number: BankAccountNumber): Option[TransactionHistory]
}