import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version libs.versions.core.kotlin.get()
    kotlin("plugin.serialization") version libs.versions.core.kotlin.get()
    id("io.ktor.plugin") version libs.versions.core.ktor.get()
}

group = "com.ougi.callme"
version = "0.0.1"

application {
    mainClass.set("com.ougi.callme.presentation.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.core.jvm)
    implementation(libs.ktor.netty.jvm)

    implementation(libs.ktor.server.websockets)

    implementation(libs.netty.logback)
    implementation(libs.ktor.client.java)

    implementation(libs.koin)
}
