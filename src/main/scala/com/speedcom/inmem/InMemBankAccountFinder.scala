package com.speedcom.inmem

import com.speedcom.Domain.{TransactionHistory, BankAccount}
import com.speedcom.core.bank_account.boundary.BankAccountFinder

import scalaz._
import Scalaz._

class InMemBankAccountFinder(bankRoot: BankRoot) extends BankAccountFinder {
  override def findAccount(account: BankAccount): TransactionHistory = bankRoot.bank(account)
}
