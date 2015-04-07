package com.speedcom.inmem

import com.speedcom.core.bank_account.boundary.{BankAccountNumber, BankAccount}
import com.speedcom.core.profile.boundary.{Profile, ProfileId}
import com.speedcom.core.transaction_history.boundary.{Money, TransactionId, Transaction, TransactionHistory}

// OUTER WORLD - MUTABLE ONE
class BankRoot {

  var profiles = Seq(
    Profile(ProfileId(1L), "Mateusz", "Maciaszek"),
    Profile(ProfileId(2L), "Justyna", "Grudzien")
  )

  var bankAccounts = Seq(
    BankAccount(ProfileId(1L), BankAccountNumber("5555"*5), "millionaire"),
    BankAccount(ProfileId(1L), BankAccountNumber("3454"*5), "normal"),
    BankAccount(ProfileId(2L), BankAccountNumber("1111"*5), "millionaire")
  )

  var transactionHistories = Seq(
    TransactionHistory(BankAccountNumber("5555"*5), transactions = List(Transaction(TransactionId(1L), BankAccountNumber("1111"*5), Money(10000)))),
    TransactionHistory(BankAccountNumber("3454"*5), transactions = List.empty),
    TransactionHistory(BankAccountNumber("1111"*5), transactions = List(Transaction(TransactionId(1L), BankAccountNumber("3454"*5), Money(-10000))))
  )

}