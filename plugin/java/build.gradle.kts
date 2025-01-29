plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("dh-java-plugin") {
        id = "by.dh.java"
        displayName = "DH Java Plugin"
        implementationClass = "by.dh.plugin.java.JavaPlugin"
        website = "https://github.com/rikkydn/dh-gradle-plugins"
        vcsUrl = "https://github.com/rikkydn/dh-gradle-plugins"
        description = "Preconfigured java plugin"
        tags.set(listOf("java"))
    }
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
    testImplementation(project(":common-test"))
}