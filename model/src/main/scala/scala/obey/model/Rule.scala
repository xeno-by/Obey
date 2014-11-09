/**
 * 	Models for a Rule.
 * 	@author Adrien Ghosn
 */
package scala.obey.model

import scala.meta.syntactic.ast._
import Tag._
import scala.reflect.runtime.{universe => ru}

/*TODO look at how to extend enums*/
trait Rule {
  /* Identifier to pretty print and identity the rule*/
  val name: String
  /* Tags associated with the rule. Default is empty, override to add new tags */
  val tags: Set[Tag] = Set()
}

trait Msg {
  val message: String
}

/* Rules that simply generate warnings*/
trait RuleWarning extends Rule {
  Keeper.warners :+= this
  
  def warning(t: Tree): Warning

  case class Warning(message: String) extends Msg {
    val rule = this
  }

  def apply(t: Tree): List[Warning]
}

/* Rules that will stop the execution
   TODO enforce stop*/
trait RuleError extends Rule {
  /*TODO change this*/
  def error(t: Tree): Error

  case class Error(message: String) extends Msg {
    val rule = this
    /*TODO maybe exception here or something*/
  }
}

trait RuleFormat extends Rule {
  Keeper.formatters :+= this
}