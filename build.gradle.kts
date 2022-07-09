import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21" // 1
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.5.21" // 6
    id ("org.jetbrains.kotlin.plugin.noarg") version "1.5.21" // 7
}

noArg {
    annotation("javax.persistence.Entity") // 2
}

allOpen {
    annotation("javax.persistence.Entity") // 3
    annotation("javax.persistence.MappedSuperclass") // 4
    annotation("javax.persistence.Embeddable") // 5
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.projectlombok:lombok:1.18.22")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // DB
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    // Model Mppper
    implementation("org.modelmapper:modelmapper:2.4.4")

    //Swagger
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:2.9.2")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client") // 권한 관련
    implementation("org.springframework.session:spring-session-jdbc") // 권한 관련
    testImplementation("org.springframework.security:spring-security-test") // 권한 관련

    // jwt
    implementation("com.auth0:java-jwt:3.19.2")

    // lombok
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
