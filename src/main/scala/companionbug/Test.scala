package companionbug

import Macro._

@main def testOtherstuff() = { //hello
  import Companion._
  mac(fun("blah"))
}