import sbt._

object Dependencies {
	import Settings.metaVersion 
	
  def reflect(sv: String) = "org.scala-lang" % "scala-reflect" % sv
  def compiler(sv: String) = "org.scala-lang" % "scala-compiler" % sv
  
  lazy val scalahost = "org.scalameta" % "scalahost" % metaVersion cross CrossVersion.full

  lazy val meta = "org.scalameta" % "scalameta" % metaVersion
 
  lazy val metafoundation = "org.scalameta" %% "scalameta-foundation" % metaVersion
 
  lazy val tql = "com.github.begeric" % "tqlscalameta_2.11" % metaVersion
  
  lazy val scalatest = "org.scalatest" %% "scalatest" % "2.1.3" % "test"
}
