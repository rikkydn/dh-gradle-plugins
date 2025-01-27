plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:${property("by.dh.versions.lombok")}")
    annotationProcessor("org.projectlombok:lombok:${property("by.dh.versions.lombok")}")

    implementation(gradleTestKit())
}

tasks.named("publish") {
    enabled = false
}
