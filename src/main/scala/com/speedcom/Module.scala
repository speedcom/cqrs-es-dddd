package com.speedcom

import com.speedcom.core.bank_account.boundary.BankAccountFinder
import com.speedcom.core.bank_account.usecases.{UcGetBalance, UcContributeMoney}
import com.speedcom.core.transaction_history.boundary.TransactionHistoryFinder
import com.speedcom.inmem.{InMemBankAccountFinder, InMemTransactionHistoryFinder, BankRoot}

trait Module {
  // storage
  val bankRoot = new BankRoot
  val transactionHistoryFinder: TransactionHistoryFinder = new InMemTransactionHistoryFinder(bankRoot)
  val bankAccountFinder: BankAccountFinder = new InMemBankAccountFinder(bankRoot)

  // use cases
  val ucContributeCash = UcContributeMoney(bankAccountFinder)
  val ucGetBalance = UcGetBalance(transactionHistoryFinder)
}