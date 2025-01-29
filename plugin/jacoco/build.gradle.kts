plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("dh-jacoco-plugin") {
        id = "by.dh.jacoco"
        displayName = "DH Jacoco Plugin"
        implementationClass = "by.dh.plugin.jacoco.JacocoPlugin"
    }
}

dependencies {
    implementation(project(":plugin:java"))
    testImplementation(project(":common-test"))
}