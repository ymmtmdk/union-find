lazy val root = (project in file("."))
  .settings(
    name := "union-find",
    organization := "org.ymmtmdk",
    autoScalaLibrary := false,
    crossPaths := false,
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )
  )
