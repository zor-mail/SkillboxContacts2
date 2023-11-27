tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>{
    options.encoding = "UTF-8"
}

plugins {
    id("java")
}

group = "org.skillbox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework:spring-context:6.0.8")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
}

tasks.test {
    useJUnitPlatform()
}
