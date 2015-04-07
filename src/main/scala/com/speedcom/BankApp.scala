package com.speedcom

import com.speedcom.core.bank_account.boundary.BankAccountNumber

object BankApp extends App with Module {

  println(ucGetBalance(BankAccountNumber("5555"*5)))
  println(ucGetBalance(BankAccountNumber("1111"*5)))

}