plugins {
    application
    id("org.graalvm.buildtools.native") version "0.9.4"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":dependency"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

nativeBuild {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(21))
    })
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainModule.set("javatest.dependency.main")
    mainClass.set("java21.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
