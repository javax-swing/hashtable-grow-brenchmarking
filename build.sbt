ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "com.javax-swing"

lazy val build = (project in file("."))
  .settings(
    name := "hashtable-grow-benchmarking"
  )
  .enablePlugins(JmhPlugin)
