package com.kn.il.plugin.common;

import static java.io.File.separator;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GradleInitUtil {

  @SneakyThrows
  public static void createBuildGradle(final File projectDir, final String content) {
    final var fileName = projectDir.getAbsolutePath() + separator + "build.gradle.kts";
    try (var writer = new BufferedWriter(new FileWriter(fileName, UTF_8))) {
      writer.write(content);
    }
  }

  @SneakyThrows
  public static void createSettingsGradle(final File projectDir, final String content) {
    final var settingsName = projectDir.getAbsolutePath() + separator + "settings.gradle.kts";
    try (var settingsWriter = new BufferedWriter(new FileWriter(settingsName, UTF_8))) {
      settingsWriter.write(content);
    }
  }

  @SneakyThrows
  public static void createGradleProperties(
      final File projectDir, final Map<String, Object> properties) {
    final var propertiesName = projectDir.getAbsolutePath() + separator + "gradle.properties";
    final var propertiesWriter = new BufferedWriter(new FileWriter(propertiesName, UTF_8));
    properties.forEach((key, value) -> writeLine(propertiesWriter, key + " = " + value + "\n"));
    propertiesWriter.close();
  }

  @SneakyThrows
  public static void createJavaClass(
      final File projectDir, final String packageName, final String className,
      final String content) {
    final var pathToFile = String.join(separator, packageName.split("\\."));
    final var fileName =
        projectDir.getAbsolutePath() + separator + pathToFile + separator + className + ".java";
    new File(projectDir.getAbsolutePath() + separator + pathToFile).mkdirs();
    try (var fileWriter = new BufferedWriter(new FileWriter(fileName, UTF_8))) {
      fileWriter.write(content);
    }
  }

  @SneakyThrows
  private static void writeLine(final BufferedWriter writer, final String line) {
    writer.write(line);
  }

}
