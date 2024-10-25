plugins {
    id("java")
    id("com.gradleup.shadow") version("8.3.3")
}

group = "de.johannes"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.sf.jopt-simple:jopt-simple:4.7")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "de.johannes.Main"
    }
}