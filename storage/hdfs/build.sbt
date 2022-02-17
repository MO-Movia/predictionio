/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import PIOBuild._

name := "apache-predictionio-data-hdfs"

libraryDependencies ++= Seq(
  "org.apache.hadoop"        % "hadoop-common"            % hadoopVersion.value
    exclude("commons-beanutils", "*")
    exclude("jakarta.activation", "*"),
  "org.apache.hadoop"        % "hadoop-hdfs"              % hadoopVersion.value,
  "org.apache.predictionio" %% "apache-predictionio-data" % version.value % "provided",
  "org.scalatest"           %% "scalatest"                % "3.2.10" % "test")

// making sure the needed netty version gets pulled for this subproject
dependencyOverrides ++= Seq(
  "io.netty"                 % "netty-all"                % "4.1.42.Final"
)

// jackson (and a couple other libraries) causes problems during deduplication phase of module-info.class -
// files apparently not needed, so discard them to avoid error
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.first
  case PathList("module-info.class") => MergeStrategy.discard
  case PathList("META-INF", "versions", xs @ _, "module-info.class") => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
parallelExecution in Test := false

pomExtra := childrenPomExtra.value

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter {_.data.getName.contains("slf4j-log4j12")}
}

// skip test in assembly
test in assembly := {}

assemblyOutputPath in assembly := baseDirectory.value.getAbsoluteFile.getParentFile.getParentFile /
  "assembly" / "src" / "universal" / "lib" / "spark" /
  ("pio-data-hdfs-assembly-" + version.value + ".jar")
