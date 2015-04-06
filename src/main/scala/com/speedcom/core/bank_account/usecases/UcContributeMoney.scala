package com.speedcom.core.bank_account.usecases

import com.speedcom.core.bank_account.boundary.{BankAccountNumber, BankAccountFinder, BankAccount}
import com.speedcom.core.transaction_history.boundary.Money

import scalaz._
import Scalaz._

case class UcContributeMoney(bankAccountFinder: BankAccountFinder)
  extends ((BankAccountNumber, Money) => State[BankAccount]) {

  def apply(bankAccountNumber: BankAccountNumber, money: Money): State[BankAccount] = {
    ???
  }
}