vertx-scala-template
====================
Vertx 의 스칼라 샘플 프로젝트 입니다.


Vert.x에서 scala를 사용하기 위해서는 우선 language를 추가해줘야 합니다.

<vertx directory>/conf/langs.properties 에 scala를 추가해 줍니다.

```
# Language run-times
# <runtime_name>=[implementing module name:]<FQCN of verticle factory>
rhino=io.vertx~lang-rhino~2.0.0-final:org.vertx.java.platform.impl.RhinoVerticleFactory
jruby=io.vertx~lang-jruby~2.0.0-final:org.vertx.java.platform.impl.JRubyVerticleFactory
groovy=io.vertx~lang-groovy~2.0.0-final:org.vertx.groovy.platform.impl.GroovyVerticleFactory
jython=io.vertx~lang-jython~2.0.0-final:org.vertx.java.platform.impl.JythonVerticleFactory
scala=io.vertx~lang-scala~0.1.1:org.vertx.scala.platform.impl.ScalaVerticleFactory

# Mapping of file extension to language runtime
# If the main is specified without a runtime prefix the file extension will be used to determine
# which runtime to use
# If a prefix is used e.g. 'groovy:org.foo.MyMainClass' then the prefix will be used to determine the
# runtime instead
.js=rhino
.coffee=rhino
.rb=jruby
.py=jython
.groovy=groovy
.class=java
.java=java
.scala=scala

# The default runtime - if no prefix is specified and the main does not match any of the file extensions above
# then the default will be used
.=java
```

이후 프로젝트 파일에서 gradle tasks를 실행해본다
```
$ gradle tasks
:tasks

------------------------------------------------------------
All tasks runnable from root project
------------------------------------------------------------

Default tasks: assemble

Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles classes 'main'.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles classes 'test'.
uploadArchives - Does a maven deploy of archives artifacts

Build Setup tasks
-----------------
setupBuild - Initializes a new Gradle build. [incubating]
wrapper - Generates Gradle wrapper files. [incubating]

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.
scaladoc - Generates Scaladoc for the main source code.

Help tasks
----------
dependencies - Displays all dependencies declared in root project 'vertx-scala-template'.
dependencyInsight - Displays the insight into a specific dependency in root project 'vertx-scala-template'.
help - Displays a help message
projects - Displays the sub-projects of root project 'vertx-scala-template'.
properties - Displays the properties of root project 'vertx-scala-template'.
tasks - Displays the tasks runnable from root project 'vertx-scala-template'.

IDE tasks
---------
cleanEclipse - Cleans all Eclipse files.
cleanIdea - Cleans IDEA project files (IML, IPR)
eclipse - Generates all Eclipse files.
idea - Generates IDEA project files (IML, IPR, IWS)

Verification tasks
------------------
check - Runs all checks.
test - Runs the unit tests.

Vert.x tasks
------------
modZip - Assembles a vert.x module

Other tasks
-----------
cleanIdeaWorkspace
install - Installs the 'archives' artifacts into the local Maven repository.
runMod - Run the module using all the build dependencies (not using installed vertx
scalaConsole - Starts a Scala REPL with the main runtime class path.
scalaTestConsole - Starts a Scala REPL with the test runtime class path.

Rules
-----
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.
Pattern: upload<ConfigurationName>: Assembles and uploads the artifacts belonging to a configuration.
Pattern: clean<TaskName>: Cleans the output files of a task.

To see all tasks and more detail, run with --all.

BUILD SUCCESSFUL

Total time: 13.874 secs

```

위와같이 gradle설정으로 사용이 가능한 task들을 보여준다.


이제, 프로젝트를 개발하기 위해서 IntelliJ IDEA에 Import 해보자.
File>Import Project를 눌러 프로젝트 파일을 선택한다.
그후 프로젝트 import방식을 설정하는 창이 나오는데, 이때 import project from external model의 gradle을 선택후
계속 다음을 눌러서 프로젝트를 import시킨다.

그러면 IDEA에서 자동적으로 Gradle빌드 정보를 읽어들여 코딩에 필요한 라이브러리들을 자동적으로 다운로드 받아서 디펜던시에 추가해준다.

코딩을 할때에 컴파일이 필요한 것은 src/main/<language>에, 컴파일이 필요하지 않을것은 src/main/resources에 작성한다.



코드 작성을 완료했으면 프로젝트 파일에서 gradle build 명령어로 프로젝트를 빌드해준다.
```
 gradle build
:compileJava UP-TO-DATE
:compileScala
:processResources UP-TO-DATE
:classes
:jar UP-TO-DATE
:javadoc UP-TO-DATE
:javadocJar UP-TO-DATE
:copyMod
:pullInDeps
:modZip
:sourceJar UP-TO-DATE
:signArchives SKIPPED
:assemble
:compileTestJava UP-TO-DATE
:compileTestScala UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test UP-TO-DATE
:check UP-TO-DATE
:build

BUILD SUCCESSFUL
```

컴파일 에러가 없다면 빌드가 성공할 것이다.
이렇게 빌드가 완료되면 <project directory>/build에 저장되게 된다.
이때 build/lib에 보면, zip파일이 생성이 되는데, 이 파일로 모듈을 실행 시킬 수 있다.


```
$ pwd
<project directory>/build/libs

$ ls
mods					scala-template-0.1.0-sources.jar	scala-template-0.1.0.zip
scala-template-0.1.0-javadoc.jar	scala-template-0.1.0.jar

$ vertx runzip scala-template-0.1.0.zip
Find ^(.*\.jar)$ pattern in org.vertx.java.platform.impl.ModuleClassLoader@2ab026a0
Find ^(.*\.jar)$ pattern in /var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib
Found /var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/lang-scala-0.1.1.jar, add to compiler bootstrap
Found /var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/scala-compiler-2.10.3.jar, add to compiler bootstrap
Found /var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/scala-library-2.10.3.jar, add to compiler bootstrap
Found /var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/scala-reflect-2.10.3.jar, add to compiler bootstrap
[init] [search path for source files: ]
[init] [search path for class files: /Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/JObjC.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/System/Library/Java/Extensions/AppleScriptEngine.jar:/System/Library/Java/Extensions/MRJToolkit.jar:/System/Library/Java/Extensions/QTJava.zip:/System/Library/Java/Extensions/dns_sd.jar:/System/Library/Java/Extensions/j3daudio.jar:/System/Library/Java/Extensions/j3dcore.jar:/System/Library/Java/Extensions/j3dutils.jar:/System/Library/Java/Extensions/jai_codec.jar:/System/Library/Java/Extensions/jai_core.jar:/System/Library/Java/Extensions/mlibwrapper_jai.jar:/System/Library/Java/Extensions/vecmath.jar:/Users/infinitu/vert.x-2.0.1/conf:/Users/infinitu/vert.x-2.0.1/lib/hazelcast-2.6.jar:/Users/infinitu/vert.x-2.0.1/lib/jackson-annotations-2.2.2.jar:/Users/infinitu/vert.x-2.0.1/lib/jackson-core-2.2.2.jar:/Users/infinitu/vert.x-2.0.1/lib/jackson-databind-2.2.2.jar:/Users/infinitu/vert.x-2.0.1/lib/netty-all-4.0.7.Final.jar:/Users/infinitu/vert.x-2.0.1/lib/vertx-core-2.0.1-final.jar:/Users/infinitu/vert.x-2.0.1/lib/vertx-platform-2.0.1-final.jar:/var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/lang-scala-0.1.1.jar:/var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/scala-compiler-2.10.3.jar:/var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/scala-library-2.10.3.jar:/var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx/lib/scala-reflect-2.10.3.jar:/var/folders/v6/13_nbq1s5wn0555b582cw9f80000gn/T/vertx-zip-mods/__vertx~58416e9b-3311-45e3-8516-2a68314b3f67~__vertx:.]
```

위는 정상적으로 실행되고 있는 화면이다.
예제에서 만든 http://localhost:8080으로 접속해보면 

```
Hello Mod-Lang-Scala!
```
를 볼 수 있다.
