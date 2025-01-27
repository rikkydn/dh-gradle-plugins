plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("kn-il-jacoco-plugin") {
        id = "com.kn.il-jacoco"
        displayName = "Kn Il Jacoco Plugin"
        implementationClass = "com.kn.il.plugin.jacoco.JacocoPlugin"
    }
}

dependencies {
    implementation(project(":plugin:java"))
    testImplementation(project(":common-test"))
}