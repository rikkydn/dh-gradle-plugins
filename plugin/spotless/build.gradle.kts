plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("dh-spotless-plugin") {
        id = "by.dh.spotless"
        displayName = "DH Java Plugin"
        implementationClass = "by.dh.plugin.spotless.SpotlessPlugin"
        website = "https://github.com/rikkydn/dh-gradle-plugins"
        vcsUrl = "https://github.com/rikkydn/dh-gradle-plugins"
        description = "Preconfigured spotless plugin"
        tags.set(listOf("spotless"))
    }
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.0.2")
    implementation(project(":plugin:java"))

    testImplementation(project(":common-test"))
}
