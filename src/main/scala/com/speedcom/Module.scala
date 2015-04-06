package com.speedcom

import com.speedcom.core.bank_account.usecases.{UcGetBalance, UcContributeMoney}
import com.speedcom.core.transaction_history.boundary.TransactionHistoryFinder
import com.speedcom.inmem.{InMemTransactionHistoryFinder, BankRoot}

trait Module {
  // storage
  val bankRoot = new BankRoot
  val transactionHistoryFinder: TransactionHistoryFinder = new InMemTransactionHistoryFinder(bankRoot)

  // use cases
  val ucContributeCash = UcContributeMoney(transactionHistoryFinder)
  val ucGetBalance = UcGetBalance(transactionHistoryFinder)
}