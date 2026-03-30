#!/usr/bin/env sh
  #
  # Copyright 2015 the original author or authors.
  #
  # Licensed under the Apache License, Version 2.0 (the "License");
  # you may not use this file except in compliance with the License.
  # You may obtain a copy of the License at
  #
  #      https://www.apache.org/licenses/LICENSE-2.0
  #
  # Unless required by applicable law or agreed to in writing, software
  # distributed under the License is distributed on an "AS IS" BASIS,
  # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  # See the License for the specific language governing permissions and
  # limitations under the License.
  #

  APP_NAME="Gradle"
  APP_BASE_NAME=`basename "$0"`

  DIRNAME="$( cd "$( dirname "$0" )" && pwd )"
  APP_HOME="$DIRNAME"
  MAX_FD="maximum"
  WARN () { echo "$*"; }
  die () { echo; echo "$*"; echo; exit 1; }

  if [ "$(uname)" = "Darwin" ] ; then
      darwin=true
  else
      darwin=false
  fi

  if [ "$darwin" = "true" ] ; then
      JAVA_HOME=`/usr/libexec/java_home -v 1.8+ 2>/dev/null`
  fi

  if [ -z "$JAVA_HOME" ] ; then
      if [ -r /etc/gentoo-release ] ; then
          JAVA_HOME=`java-config --jre-home`
      fi
  fi

  if [ -z "$JAVACMD" ] ; then
      if [ -n "$JAVA_HOME" ] ; then
          JAVACMD="$JAVA_HOME/bin/java"
      else
          JAVACMD="java"
      fi
  fi

  if [ ! -x "$JAVACMD" ] ; then
      die "ERROR: JAVA_HOME is not set correctly and no 'java' command could be found."
  fi

  CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

  exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
  