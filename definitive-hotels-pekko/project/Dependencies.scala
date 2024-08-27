import sbt._

object Dependencies {
  lazy val helenus       = "net.nmoncho"          %% "helenus-core"     % "0.3.0+4-f6b30b5a-SNAPSHOT"
  lazy val helenusPekko  = "net.nmoncho"          %% "helenus-pekko"    % "0.3.0+4-f6b30b5a-SNAPSHOT"
  lazy val ossJavaDriver = "org.apache.cassandra"  % "java-driver-core" % "4.18.1"
  lazy val cassandraUnit = "org.cassandraunit"     % "cassandra-unit"   % "4.3.1.0"
  lazy val scalaTest     = "org.scalatest"        %% "scalatest"        % "3.2.15"
  lazy val jna           = "net.java.dev.jna"      % "jna"              % "5.12.1" // Fixes M1 JNA issue

  lazy val pekkoStream    = "org.apache.pekko" %% "pekko-stream"               % "1.0.2"
  lazy val pekkoConnector = "org.apache.pekko" %% "pekko-connectors-cassandra" % "1.0.1"
  lazy val pekkoTestKit   = "org.apache.pekko" %% "pekko-testkit"              % "1.0.2"
}
