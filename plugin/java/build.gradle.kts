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
    }
}

dependencies {
    testImplementation(project(":common-test"))
}