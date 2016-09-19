name := "Banking-System-Prototype"
version := "1.0"
scalaVersion := "2.11.8"
	
resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

mainClass := Some("Main")

libraryDependencies ++= Seq(
		"org.scalafx" %% "scalafxml-core-sfx8" % "0.2.2",
		"org.scalafx" %% "scalafx" % "8.0.92-R10"
		)