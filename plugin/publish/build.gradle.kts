plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("kn-il-publish-plugin") {
        id = "com.kn.il-publish"
        displayName = "Kn Il Bom Publish Plugin"
        implementationClass = "com.kn.il.plugin.publish.PublishPlugin"
    }
}

dependencies {
    implementation(project(":common-dictionary"))
    testImplementation(project(":common-test"))
}