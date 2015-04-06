package com.speedcom

import com.speedcom.core.bank_account.boundary.TransactionHistoryFinder
import com.speedcom.core.bank_account.usecases.{UcGetBalance, UcContributeCash}
import com.speedcom.inmem.{InMemTransactionHistoryFinder, BankRoot}


trait Module {
  // storage
  val bankRoot = new BankRoot
  val bankAccountFinder: TransactionHistoryFinder = new InMemTransactionHistoryFinder(bankRoot)

  // use cases
  val ucContributeCash = UcContributeCash(bankAccountFinder)
  val ucGetBalance = UcGetBalance(bankAccountFinder)
}