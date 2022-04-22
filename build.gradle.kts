plugins {
  val indraVersion = "3.0.1"
  id("com.diffplug.spotless") version "6.15.0"
  id("net.kyori.indra") version indraVersion
  id("net.kyori.indra.checkstyle") version indraVersion
  id("net.kyori.indra.publishing") version indraVersion
  id("net.kyori.indra.publishing.sonatype") version indraVersion
  id("net.ltgt.errorprone") version "3.0.1"
}

group = "com.seiama"
description = "A powerful alternative to Predicate that adds the possibility of abstention"
version = "1.0.0-SNAPSHOT"

indra {
  github("seiama", "filter") {
    ci(true)
  }

  mitLicense()

  javaVersions {
    target(17)
  }

  configurePublications {
    pom {
      developers {
        developer {
          id.set("kashike")
          name.set("Riley Park")
          timezone.set("America/Vancouver")
        }
      }
    }
  }
}

indraSonatype {
  useAlternateSonatypeOSSHost("s01")
}

spotless {
  java {
    endWithNewline()
    importOrderFile(rootProject.file(".spotless/seiama.importorder"))
    indentWithSpaces(2)
    licenseHeaderFile(rootProject.file("license_header.txt"))
    trimTrailingWhitespace()
  }
}

tasks.named<Jar>(JavaPlugin.JAR_TASK_NAME) {
  indraGit.applyVcsInformationToManifest(manifest)
}

repositories {
  mavenCentral()
}

dependencies {
  checkstyle("ca.stellardrift:stylecheck:0.2.0")
  errorprone("com.google.errorprone:error_prone_core:2.18.0")
  compileOnlyApi("org.jetbrains:annotations:24.0.0")
  testImplementation("com.google.guava:guava-testlib:31.1-jre")
  testImplementation(platform("org.junit:junit-bom:5.9.2"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
