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

name := "apache-predictionio-core"

libraryDependencies ++= Seq(
  "com.github.scopt"       %% "scopt"            % "3.5.0",
  "com.google.code.gson"    % "gson"             % "2.5",
  "com.twitter"            %% "chill-bijection"  % "0.10.0",
  "de.javakaffee"           % "kryo-serializers" % "0.37",
  "net.jodah"               % "typetools"        % "0.3.1",
  "org.apache.spark"       %% "spark-core"       % sparkVersion.value % "provided",
  "org.json4s"             %% "json4s-ext"       % json4sVersion.value,
  "org.scalaj"             %% "scalaj-http"      % "2.4.2",
  "org.slf4j"               % "slf4j-log4j12"    % "1.7.18",
  "org.scalatest"          %% "scalatest"        % "3.2.10" % "test",
  "org.specs2"             %% "specs2-core"           % "4.13.0" % "test",
  "org.scalamock"          %% "scalamock-scalatest-support" % "3.5.0" % "test",
  "com.h2database"           % "h2"             % "1.4.196" % "test"
)

parallelExecution in Test := false

pomExtra := childrenPomExtra.value
