# gRPC issues

### Background

This small sample project is created to reproduce an errors in gRPC with Java/Kotlin.

The original issues were encountered in an Open Source [ostelco](https://github.com/ostelco/ostelco-core) project I am 
part of.

I have reduced the relevant part from that original project to a simplified, domain independent, generic sandbox which
is convenient to understand, reproduce the issues and debug.

### Issues

I have encountered two issues/bugs in `gRPC for Java`.

##### Issue 1
In a _nested_ `message`, setting `uint64`  to value greater than `55903` causes [ByteString] of [Message] to be 
invalid `UTF-8`.

##### Issue 2
Generated Java code does not compile if I have `repeated uint64` field in the `message`.  

### Setup

 - macOS
 - OpenJDK 11.0.2
 
### Versions

 - Gradle 5.2.1 _(See gradle/wrapper/gradle-wrapper.properties)_
 - Kotlin 1.3.21
 - Gradle Protocol Buffers Plugin - `id "com.google.protobuf" version "0.8.8"`
 - protoc - `com.google.protobuf:protoc:3.7.0`
 - gRPC Java dependencies - `io.grpc:grpc-*:1.19.0`

### Building the project using Gradle.

Run from cli - `./gradlew clean build -info`

### How to reproduce issue

##### Issue 1

 * The issue is reproduced with the JUnit tests in - `src/test/kotlin` - `dev.vihang.issues.grpc.GrpcByteStringUtf8Test`.
 * Gradle build the project to find a passing test printing `55903 generates VALID UTF-8 ByteString` and a failing test 
 case printing `55904 generates INVALID UTF-8 ByteString`.
 * Test for value `55903` passes, but fails for `55904`.
 * Try setting value lower than `55903` and it would still pass.
 * Similarly, test fails for any values more than `55903`. 


##### Issue 2
 * This issue is reproduced by uncommenting `message Message2` in the file - `src/main/proto/grpc.proto`.
 * Gradle build the project to find generated code failing to compile.

### Important files

 * [grpc.proto](src/main/proto/grpc.proto)
 * [GrpcByteStringUtf8Test.kt](src/test/kotlin/dev/vihang/issues/grpc/GrpcByteStringUtf8Test.kt)
 * [Generated gRPC/Protobuf code](build/generated/source/proto/main/java)

