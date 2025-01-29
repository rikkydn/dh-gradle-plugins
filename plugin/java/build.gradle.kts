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
}

dependencies {
    testImplementation(project(":common-test"))
}