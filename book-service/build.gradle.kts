import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
	configurations["classpath"].resolutionStrategy.eachDependency {
		if (requested.group == "org.jooq") {
			useVersion("3.16.1")
		}
	}
}


plugins {
	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.6.10"
	id("nu.studer.jooq") version "7.1.1"
}

group = "com.tr"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	// spring
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	// kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	// postgresql
	runtimeOnly("org.postgresql:postgresql")
	// jooq
	compileOnly("org.jooq:jooq:3.16.4")
	compileOnly("org.jooq:jooq-meta:3.16.3")
	compileOnly("org.jooq:jooq-codegen:3.16.3")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
	jooqGenerator("org.postgresql:postgresql:42.3.2")
	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

noArg {
	annotation("javax.persistence.Entity")
	invokeInitializers = true
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.getByName<BootJar>("bootJar") {
	this.archiveFileName.set("book-service.jar")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jooq {
	version.set("3.16.4")
	edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)

	configurations {
		create("main") {
			generateSchemaSourceOnCompilation.set(true)

			jooqConfiguration.apply {
				jdbc.apply {
					driver = "org.postgresql.Driver"
					url = "jdbc:postgresql://localhost:5432/books"
					user = "postgres"
					password = "admin"
				}

				generator.apply {
					name = "org.jooq.codegen.KotlinGenerator"

					database.apply {
						name = "org.jooq.meta.postgres.PostgresDatabase"
						inputSchema = "public"
					}
					generate.apply {
						isDeprecated = false
						isRecords = true
						isImmutablePojos = true
						isFluentSetters = true
					}
					target.apply {
						packageName = "com.tr.domain.generated"
					}
					strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
				}
			}
		}
	}
}
