plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("dh-java-plugin") {
        id = "by.dh.java"
        displayName = "DH Java Plugin"
        implementationClass = "by.dh.plugin.java.JavaPlugin"
    }
}

dependencies {
    testImplementation(project(":common-test"))
}