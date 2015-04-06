package com.speedcom.inmem

import com.speedcom.core.bank_account.boundary.{BankAccountNumber, BankAccount}
import com.speedcom.core.profile.boundary.{Profile, ProfileId}
import com.speedcom.core.transaction_history.boundary.TransactionHistory

// OUTER WORLD - MUTABLE ONE
class BankRoot {

  var profiles = Map[ProfileId, Profile](
    ProfileId(1L) -> Profile(ProfileId(1L), "Mateusz", "Maciaszek"),
    ProfileId(2L) -> Profile(ProfileId(2L), "Justyna", "Grudzien")
  )

  var bankAccounts = Map[BankAccountNumber, BankAccount](
    BankAccountNumber("5555"*5) -> BankAccount(ProfileId(1L), BankAccountNumber("5555"*5), "millionaire"),
    BankAccountNumber("3454"*5) -> BankAccount(ProfileId(1L), BankAccountNumber("3454"*5), "normal"),
    BankAccountNumber("1111"*5) -> BankAccount(ProfileId(2L), BankAccountNumber("3454"*5), "millionaire")
  )

  var transactionHistories = Map[BankAccountNumber, TransactionHistory](
    BankAccountNumber("5555"*5) -> TransactionHistory(BankAccountNumber("5555"*5), transactions = List.empty),
    BankAccountNumber("3454"*5) -> TransactionHistory(BankAccountNumber("3454"*5), transactions = List.empty),
    BankAccountNumber("1111"*5) -> TransactionHistory(BankAccountNumber("1111"*5), transactions = List.empty)
  )

}