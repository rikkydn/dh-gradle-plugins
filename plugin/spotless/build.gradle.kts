plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("dh-spotless-plugin") {
        id = "by.dh.spotless"
        displayName = "DH Java Plugin"
        implementationClass = "by.dh.plugin.spotless.SpotlessPlugin"
    }
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.0.2")
    implementation(project(":plugin:java"))

    testImplementation(project(":common-test"))
}
