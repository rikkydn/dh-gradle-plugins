plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("dh-jacoco-plugin") {
        id = "by.dh.jacoco"
        displayName = "DH Jacoco Plugin"
        implementationClass = "by.dh.plugin.jacoco.JacocoPlugin"
        website = "https://github.com/rikkydn/dh-gradle-plugins"
        vcsUrl = "https://github.com/rikkydn/dh-gradle-plugins"
        description = "Preconfigured jacoco plugin"
        tags.set(listOf("jacoco"))
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
    implementation(project(":plugin:java"))
    testImplementation(project(":common-test"))
}