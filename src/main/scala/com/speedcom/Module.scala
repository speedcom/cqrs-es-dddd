package com.speedcom

import com.speedcom.core.bank_account.boundary.BankAccountFinder
import com.speedcom.core.bank_account.usecases.{UcGetBalance, UcContributeCash}
import com.speedcom.inmem.{InMemBankAccountFinder, BankRoot}


trait Module {
  // storage
  val bankRoot = new BankRoot
  val bankAccountFinder: BankAccountFinder = new InMemBankAccountFinder(bankRoot)

  // use cases
  val ucContributeCash = UcContributeCash(bankAccountFinder)
  val ucGetBalance = UcGetBalance(bankAccountFinder)
}