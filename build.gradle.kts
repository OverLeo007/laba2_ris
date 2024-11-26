
plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("plugin.lombok") version "2.0.21"
	id("io.freefair.lombok") version "8.10"
}

group = "ru.paskal"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	// https://mvnrepository.com/artifact/jakarta.xml.bind/jakarta.xml.bind-api
//	implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
// https://mvnrepository.com/artifact/org.glassfish.jaxb/jaxb-runtime
//	implementation("org.glassfish.jaxb:jaxb-runtime:4.0.5")

	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-data-rest
//	implementation("org.springdoc:springdoc-openapi-data-rest:1.8.0")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-kotlin
//	runtimeOnly("org.springdoc:springdoc-openapi-kotlin:1.8.0")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	// https://mvnrepository.com/artifact/commons-io/commons-io
	implementation("commons-io:commons-io:2.18.0")


	// https://mvnrepository.com/artifact/org.jooq/jooq-meta
	implementation("org.jooq:jooq-meta:3.19.15")
	// https://mvnrepository.com/artifact/org.jooq/jooq-codegen
	implementation("org.jooq:jooq-codegen:3.19.15")
	// https://mvnrepository.com/artifact/com.auth0/java-jwt
	implementation("com.auth0:java-jwt:4.4.0")
	implementation("org.springframework.boot:spring-boot-starter-security")
	// https://mvnrepository.com/artifact/org.modelmapper/modelmapper
//	implementation("org.modelmapper:modelmapper:3.2.1")
	// https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.1")
	implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
	compileOnly("org.projectlombok:lombok:1.18.34")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

sourceSets {
	main {
		java {
			srcDir("src/main/kotlin")
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


tasks.register<JavaExec>("generateJooq") {
	group = "jooq"
	description = "Generates jOOQ classes"
	classpath = sourceSets["main"].runtimeClasspath
	mainClass.set("org.jooq.codegen.GenerationTool")
	args = listOf("src/main/resources/jooq-config.xml")
}