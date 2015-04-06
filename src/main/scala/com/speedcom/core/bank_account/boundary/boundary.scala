package com.speedcom.core.bank_account.boundary

import com.speedcom.core.profile.boundary.ProfileId

case class BankAccountNumber(number: String) extends AnyVal
case class BankAccount(profileId: ProfileId,
                       number: BankAccountNumber,
                       name: String)