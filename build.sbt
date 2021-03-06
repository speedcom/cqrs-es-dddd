name := "cqrs-es-dddd"

scalaVersion := "2.11.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-optimise",
  "-explaintypes",
  "-Xcheckinit",
  "-Xfatal-warnings",
  "-Xlint",
  "-Xverify",
  "-Yclosure-elim",
  "-Ydead-code",
  "-Yinline"
)

triggeredMessage := (_ => Watched.clearScreen)

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.1.1"
