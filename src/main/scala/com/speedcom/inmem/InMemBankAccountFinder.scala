package com.speedcom.inmem

import com.speedcom.BankAccountFinder
import com.speedcom.Domain.{TransactionHistory, BankAccount}

class InMemBankAccountFinder(bankRoot: BankRoot) extends BankAccountFinder {
  def findAccount(account: BankAccount): TransactionHistory = bankRoot.bank(account)
}
