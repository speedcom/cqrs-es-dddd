package com.speedcom.inmem

import com.speedcom.core.bank_account.boundary.{BankAccount, BankAccountNumber, BankAccountFinder}
import com.speedcom.core.profile.boundary.ProfileId

class InMemBankAccountFinder(bankRoot: BankRoot) extends BankAccountFinder {
  
  override def findByProfileId(profileId: ProfileId): Option[BankAccount] = 
    bankRoot
      .bankAccounts
      .find(_.profileId.id == profileId.id)

  override def findByNumber(bankAccountNumber: BankAccountNumber): Option[BankAccount] =
    bankRoot
      .bankAccounts
      .find(_.bankAccountNumber.number == bankAccountNumber.number)
}
