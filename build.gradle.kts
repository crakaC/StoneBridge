plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("kapt") version "1.8.10"
    application
    id("app.cash.sqldelight") version "2.0.0-alpha05"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.dagger:dagger:2.45")
    kapt("com.google.dagger:dagger-compiler:2.45")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.0-alpha05")
    implementation("app.cash.sqldelight:coroutines-extensions:2.0.0-alpha05")
    testImplementation("io.kotest:kotest-assertions:4.0.7")
    testImplementation(kotlin("test"))

    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation ("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("Only needed to run tests in a version of IntelliJ IDEA that bundles older versions")
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("org.example.stonebridge.AppKt")
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("org.example.stonebridge")
        }
    }
}