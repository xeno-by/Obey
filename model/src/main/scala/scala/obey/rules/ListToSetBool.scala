package scala.obey.rules

import scala.meta.tql.ScalaMetaFusionTraverser._

import scala.meta.internal.ast._
import scala.obey.model._
import scala.obey.model.utils._

@Tag("List", "Set", "Type") object ListToSetBool extends Rule {
  
  val description = "List to Set evaluated to Boolean"

  /*TODO apply only for val until we talk about it*/
  def message(t: Term.Apply): Message = Message(s"$t gets evaluated to a boolean !", t)

  def apply = {
    (transform{
      case tt @ Term.Apply(t @ Term.Select(Term.Apply(Term.Name("List"), _), Term.Name("toSet")), _) =>
        t andCollect message(tt)
      }).down
  }
} 