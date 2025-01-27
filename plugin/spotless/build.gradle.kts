plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("kn-il-spotless-plugin") {
        id = "com.kn.il-spotless"
        displayName = "Kn Il Java Plugin"
        implementationClass = "com.kn.il.plugin.spotless.SpotlessPlugin"
    }
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.25.0")
    implementation(project(":plugin:java"))

    testImplementation(project(":common-test"))
}
