import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "itmo.programming"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.thoughtworks.xstream:xstream:1.4.21")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.2")
}

application {
    mainClass.set("Main")
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<ShadowJar>("shadowJar") {
    // По умолчанию плагин добавит суффикс -all к имени файла
    // Если хотите свой вариант, раскомментируйте:
    // archiveClassifier.set("uber")

    // Чтобы в манифест JAR автоматически записался Main-Class:
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}