# GraalVM Bug?

Reproducing:

Used versions:

JVM:
openjdk version "21" 2023-09-19
OpenJDK Runtime Environment GraalVM CE 21+35.1 (build 21+35-jvmci-23.1-b15)
OpenJDK 64-Bit Server VM GraalVM CE 21+35.1 (build 21+35-jvmci-23.1-b15, mixed mode, sharing)

native-image:
native-image 21 2023-09-19
GraalVM Runtime Environment GraalVM CE 21+35.1 (build 21+35-jvmci-23.1-b15)
Substrate VM GraalVM CE 21+35.1 (build 21+35, serial gc)

Building without native:

```
./gradlew :app:build
```

Run it:

``` 
java --module-path ./app/build/libs/app.jar:./dependency/build/libs/dependency.jar -m javatest.app.main/java21.App
```

Result:

``` 
Found a service: MyService!
Called Dependency.hello()
```

Build native:

```
./gradlew :app:nativeBuild
```

Running:

```
app/build/native/nativeBuild/app
```

Result:

``` 
Called Dependency.hello()
```

Building native-image manually (to see if Gradle is the issue):

```
native-image --module-path ./app/build/libs/app.jar:./dependency/build/libs/dependency.jar --module javatest.dependency.main -m javatest.app.main/java21.App
```

Result:

``` 
Called Dependency.hello()
```

In both cases when native build is used the service are never resolved.