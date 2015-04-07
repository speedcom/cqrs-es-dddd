package com.speedcom.inmem

import com.speedcom.core.bank_account.boundary.{BankAccountNumber, BankAccount}
import com.speedcom.core.transaction_history.boundary.{TransactionHistory, TransactionHistoryFinder}

class InMemTransactionHistoryFinder(bankRoot: BankRoot) extends TransactionHistoryFinder {

  override def findByBankAccountNumber(bankAccountNumber: BankAccountNumber): Option[TransactionHistory] = {
    bankRoot
      .transactionHistories
      .find(_.bankAccountNumber.number == bankAccountNumber.number)
  }
}
