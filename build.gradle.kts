plugins {
    `maven-publish`
    `java-gradle-plugin`
    jacoco
    id("com.gradle.plugin-publish") version "1.3.0"
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "java-gradle-plugin")
    apply(plugin = "com.gradle.plugin-publish")

    group = "by.dh.plugins"

    repositories {
        mavenCentral()
    }

    publishing {
        repositories {
            maven {
                name = "dh.gradle.plugin"
                url = uri("https://maven.pkg.github.com/rikkydn/dh-gradle-plugins")
            }
        }
    }

    dependencies {
        testCompileOnly("org.projectlombok:lombok:${property("by.dh.versions.lombok")}")
        testAnnotationProcessor("org.projectlombok:lombok:${property("by.dh.versions.lombok")}")

        testImplementation(gradleTestKit())
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
}