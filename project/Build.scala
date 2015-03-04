import sbt.Keys._
import sbt._

object build extends Build {
  import Dependencies._
  import PublishSettings._
  import Settings._

  lazy val commonDependencies = Seq(
    libraryDependencies <++= (scalaVersion)(sv => Seq(
      Dependencies.meta,
      Dependencies.scalatest)) //addCompilerPlugin(paradise)
      //addCompilerPlugin(scalahost)
      )

  lazy val plugin = Project(
    id = "plugin",
    base = file("plugin"),
    settings = sharedSettings ++ publishableSettings ++ commonDependencies ++ mergeDependencies ++ List(
      libraryDependencies <++= (scalaVersion)(sv => Seq(
      compiler(sv) % "provided",
      Dependencies.scalahost)),
      resourceDirectory in Compile := baseDirectory.value / "resources")) dependsOn (model)

  lazy val model = Project(
    id = "model",
    base = file("model"),
    settings = sharedSettings ++ publishableSettings ++ commonDependencies ++ List(
       libraryDependencies <++= (scalaVersion)(sv => Seq(
       reflect(sv)))
    ))

  lazy val sbtPlug: Project = Project(
    id = "sbt-plugin",
    base = file("sbt-plugin"),
    settings = sbtPluginSettings ++ publishableSettings ++ List(sbtPlugin := true, name := "sbt-obeyplugin"))
}
