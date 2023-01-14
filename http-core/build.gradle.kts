plugins {
    id("java")
    kotlin("jvm") version "1.7.21"
}

//apply from: "../versions.gradle"

repositories {
    mavenCentral()
}


dependencies {
    implementation("io.github.caldremch:core-logger:1.0.7")
    implementation("io.insert-koin:koin-core:3.2.2")
    testImplementation ("junit:junit:4.13.2")
}


//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "11"
//}
//compileKotlin{
//    kotlinOptions {
//        jvmTarget = "11"
//    }
//}

//java {
//    sourceCompatibility = JavaVersion.VERSION_11
//    targetCompatibility = JavaVersion.VERSION_11
//}

//ext {
//    myLibraryVersion=android_http_version
//    myLibraryName="http-core"
//    myArtifactId="http-core"
//    myLibraryDescription="http-core request framework"
//}

//apply from: "../gradle-maven-kotlin-dsl/mavencentral-with-maven-publish.gradle"
