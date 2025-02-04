plugins {
    id("org.jetbrains.intellij") version "1.17.3"
    kotlin("jvm") version "1.9.0"
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}
intellij {
    version.set("2024.3")
    type.set("IC") // "IC" for Community Edition
    plugins.set(listOf("java")) // Include necessary plugins
}
tasks {
    patchPluginXml {
        version.set(project.version.toString())
        sinceBuild.set("243") // Change to match your IntelliJ version
        untilBuild.set("")    // Leave empty for compatibility
    }
}

