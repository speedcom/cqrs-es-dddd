package com.speedcom.inmem

import com.speedcom.core.bank_account.boundary.BankAccount
import com.speedcom.core.transaction_history.boundary.{TransactionHistory, TransactionHistoryFinder}

class InMemTransactionHistoryFinder(bankRoot: BankRoot) extends TransactionHistoryFinder {
  override def findByBankAccount(account: BankAccount): TransactionHistory = bankRoot.bank(account)
}
