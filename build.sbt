// Scalaプロジェクト全体の設定や必要なライブラリの追加を行うファイルです。
// build.sbtはScala開発の場合はほぼ100%利用します。

name := """study-scala""" // このプログラム全体(プロジェクト)の名前
version := "1" // このプロジェクトのバージョン
scalaVersion := "2.13.8" // 利用するScalaのバージョン
lazy val root = (project in file(".")).enablePlugins(PlayScala) // Play Frameworkプラグインの有効化
libraryDependencies += guice // 必要なライブラリの追加
