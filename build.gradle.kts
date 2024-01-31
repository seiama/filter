plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.indra)
  alias(libs.plugins.indraCheckstyle)
  alias(libs.plugins.indraPublishing)
  alias(libs.plugins.indraPublishingSonatype)
  alias(libs.plugins.errorprone)
}

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
  annotationProcessor(libs.contractValidator)
  checkstyle(libs.stylecheck)
  errorprone(libs.errorProneCore)
  compileOnlyApi(libs.jetbrainsAnnotations)
  compileOnlyApi(libs.jspecify)
  testImplementation(libs.guavaTestlib)
  testImplementation(platform(libs.junit.bom))
  testImplementation(libs.junit.jupiter.api)
  testRuntimeOnly(libs.junit.jupiter.engine)
}
