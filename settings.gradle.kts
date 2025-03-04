pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "DH Gradle Plugins"

include("common-test")
include("common-dictionary")

include("plugin:dictionary")
include("plugin:jacoco")
include("plugin:java")
include("plugin:spotless")

