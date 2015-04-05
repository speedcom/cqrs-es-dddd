package com.speedcom.inmem

import com.speedcom.Domain.Bank

// OUTER WORLD - MUTABLE ONE
class BankRoot {

  var bank: Bank = Map(
    "Matt"   -> List(50f),
    "Justin" -> List(50f, 100f, -40f, 700f))

}