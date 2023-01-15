plugins.apply(MavenPublishPlugin::class)
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    }
}

fun println(log: String) {
    kotlin.io.println("maven-publish > $log")
}

val myBintrayName: String? by project
val myLibraryVersion: String? by project
val myLibraryDescription: String? by project
val myGitUrl: String? by project
val myAllLicenses: String? by project
val myPublishedGroupId: String? by project
val myLibraryName: String? by project
val myArtifactId: String? by project
val myLicenseName: String? by project
val myLicenseUrl: String? by project
val myDeveloperId: String? by project
val myDeveloperName: String? by project
val myDeveloperEmail: String? by project
val mySiteUrl: String? by project

val isAndroid = project.hasProperty("android")

val properties = java.util.Properties()
if (project.rootProject.file("local.properties").exists()) {
    properties.load(project.rootProject.file("local.properties").inputStream())
}


val myMavenUrl = properties.getProperty( "myMavenUrl")
val myMavenUserName = properties.getProperty( "myMavenUserName")
val myMavenPassword = properties.getProperty( "myMavenPassword")

println("myMavenUrl:$myMavenUrl")
println("myMavenUserName:$myMavenUserName")
println("myMavenPassword:$myMavenPassword")

if (project.hasProperty("android")) {

    println("android")

    val android = project.extensions["android"] as com.android.build.gradle.BaseExtension

    //register sourcesJar for android
    val sourcesJar = tasks.register("sourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs)
    }

    //register task javadoc for android
    val javadoc = tasks.register("javadoc", Javadoc::class) {
        setSource(android.sourceSets.getByName("main").java.srcDirs)
        classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
    }

    tasks.register("androidJavaDocsJar", Jar::class) {
        archiveClassifier.set("javadoc")
        dependsOn(javadoc)
        from(javadoc.get().destinationDir)
    }


} else {

    println("java/kotlin")

    configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }
}

val publicationName = "Publication"
afterEvaluate {
    configure<PublishingExtension> {
        repositories {
            maven {
                myMavenUrl?.apply { url = uri(this) }
                if (myMavenUserName.isNullOrEmpty().not() && myMavenPassword.isNullOrEmpty().not()) {
                    credentials {
                        username = myMavenUserName
                        password = myMavenPassword
                    }
                }

            }
        }

        publications {
            create<MavenPublication>(publicationName) {
                if (isAndroid) {
                    if (components.size > 0) {
                        val androidJavaDocsJar by tasks
                        val sourcesJar by tasks
                        artifact(sourcesJar)
                        artifact(androidJavaDocsJar)
                        from(components["debug"])

                    }
                } else {
                    from(components["java"])
                }
                groupId = myPublishedGroupId
                artifactId = myArtifactId
                version = myLibraryVersion
                pom {
                    name.set(myLibraryName)
                    description.set(myLibraryDescription)
                    url.set(mySiteUrl)
                    licenses {
                        license {
                            name.set(myLicenseName)
                            url.set(myLicenseUrl)
                        }
                    }
                    developers {
                        developer {
                            id.set(myDeveloperId)
                            name.set(myDeveloperName)
                            email.set(myDeveloperEmail)
                        }
                    }
                    scm {
                        connection.set(myGitUrl)
                        developerConnection.set(myGitUrl)
                        url.set(mySiteUrl)
                    }
                }

            }
        }

    }
}
