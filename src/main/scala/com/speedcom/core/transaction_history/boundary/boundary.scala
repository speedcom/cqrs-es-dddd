package com.speedcom.core.transaction_history.boundary

import com.speedcom.core.bank_account.boundary.BankAccountNumber

case class Transaction(number_1: BankAccountNumber,
                       number_2: BankAccountNumber,
                       cash: Float)

case class TransactionHistory(bankAccountNumber: BankAccountNumber, transactions: List[Transaction])