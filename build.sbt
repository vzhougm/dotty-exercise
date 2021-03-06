val zioVersion = "1.0.3"

lazy val root = project
  .in(file("."))
  .settings(
    inThisBuild(
      List(
        name := "dotty-exercise",
        organization := "cool.scalax",
        version := "0.0.1",
        scalaVersion := "3.0.0-M3"
      )
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"               % zioVersion,
      "dev.zio" %% "zio-test"          % zioVersion % Test,
      "dev.zio" %% "zio-test-sbt"      % zioVersion % Test,
      "dev.zio" %% "zio-test-junit"    % zioVersion % Test,
      "dev.zio" %% "zio-test-magnolia" % zioVersion % Test
    ),
    libraryDependencies := libraryDependencies.value.map(_.withDottyCompat(scalaVersion.value)),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )
