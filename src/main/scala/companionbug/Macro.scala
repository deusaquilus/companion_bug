package companionbug

import scala.quoted._

object Companion extends Trait
trait Trait {
  def fun(first: String): String = "anything"
}

object Macro {
  inline def mac(tree: String): String = ${ macImpl('tree) }
  def macImpl(tree: Expr[String])(using qctx: QuoteContext): Expr[String] = {
    import qctx.tasty.{given _, _}

    tree.unseal.underlyingArgument.seal match {
        case vv @ '{ ($s: Trait).fun($arg) } => arg
        case _ => Expr("not matched")
    }
  }
}
