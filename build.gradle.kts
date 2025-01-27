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

    group = "com.kn.il.plugins"

    repositories {
        mavenCentral()
    }

//    pluginBundle {
//        website = "https://wiki.int.kn/display/FT/Audit+Outbox+Library"
//        vcsUrl = "https://git.int.kn/scm/fyit/ctc-plugins.git"
//        description = "Preconfigured plugin for CTC development"
//    }

    publishing {
        repositories {
            maven {
                name = "dh.gradle.plugin"
                url = uri("https://maven.pkg.github.com/rikkydn/dh-gradle-plugins")
                credentials {
                    username = System.getenv("DH_GH_USERNAME")
                    password = System.getenv("DH_GH_TOKEN")
                }
            }
        }
    }

    dependencies {
        testCompileOnly("org.projectlombok:lombok:1.18.30")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

        testImplementation(gradleTestKit())
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
}