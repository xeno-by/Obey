import org.scalatest.FunSuite
import scala.meta.syntactic.ast._
import scala.meta._
import scala.obey.rules._
import scala.obey.model._
import scala.reflect.runtime.{ universe => ru }
import scala.obey.tools.Utils._
import scala.obey.tools.Enrichment._
import tqlscalameta.ScalaMetaTraverser._

class ListRulesTest extends FunSuite {

val x =
    q"""
		val c = List(1,3,4,5).toSet()
		val v = List(1,2,3,4).toSet
	"""
  test("List rules simple test") {
  	assert(ListToSet.apply(x).result.size == 1)
  	assert(ListToSetBool.apply(x).result.size == 1)
  }

  test("List rules medium test") {
  	assert(ListToSet.apply(ListToSet.apply(x).tree.getOrElse(x)).result.isEmpty)
  	assert(ListToSetBool.apply(ListToSetBool.apply(x).tree.getOrElse(x)).result.isEmpty)
  }
}