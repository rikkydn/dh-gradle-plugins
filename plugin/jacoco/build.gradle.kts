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
}

dependencies {
    implementation(project(":plugin:java"))
    testImplementation(project(":common-test"))
}