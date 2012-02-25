import com.typesafe.startscript.StartScriptPlugin

name := "winko"

version := "0.3.0-SNAPSHOT"

scalaVersion := "2.9.1"

libraryDependencies += "net.sourceforge.tuio" % "tuio" % "1.4"

libraryDependencies += "net.debasishg" %% "sjson" % "0.15"

seq(coffeeSettings: _*)

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

(CoffeeKeys.bare in (Compile, CoffeeKeys.coffee)) := true

