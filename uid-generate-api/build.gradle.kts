plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
}

dependencies {
    // projec
    implementation(project(":lib-uid-generator"))
    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // jdbc
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    // mybatis
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3")
    // mysql
    implementation("mysql:mysql-connector-java:8.0.20")
    // for swagger ui using spring fox
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    // flyway
    implementation("org.flywaydb:flyway-core")
    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // dev
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
