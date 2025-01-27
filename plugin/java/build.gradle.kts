plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("kn-il-java-plugin") {
        id = "com.kn.il-java"
        displayName = "Kn Il Java Plugin"
        implementationClass = "com.kn.il.plugin.java.JavaPlugin"
    }
}

dependencies {
    testImplementation(project(":common-test"))
}