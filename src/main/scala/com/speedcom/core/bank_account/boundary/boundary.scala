package com.speedcom.core.bank_account.boundary

import com.speedcom.core.profile.boundary.ProfileId
import com.speedcom.core.transaction_history.boundary.TransactionHistory

case class BankAccountNumber(number: String) extends AnyVal
case class BankAccount(profileId: ProfileId, number: BankAccountNumber, name: String)

trait BankAccountFinder {
  def findAccount(account: BankAccount): TransactionHistory
}
