package com.speedcom.core.bank_account.boundary

import com.speedcom.Domain.{TransactionHistory, BankAccount}

trait BankAccountFinder {
  def findAccount(account: BankAccount): TransactionHistory
}
