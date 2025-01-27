package com.kn.il.plugin.java;

import static by.dh.plugins.common.dictionary.GradleExecutorUtil.execute;
import static by.dh.plugins.common.dictionary.GradleInitUtil.createBuildGradle;
import static by.dh.plugins.common.dictionary.GradleInitUtil.createGradleProperties;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JavaPluginTest {

  @TempDir
  private File testProjectDir;

  @BeforeEach
  void init() {
    createBuildGradle(testProjectDir, """
            plugins {
                id("com.kn.il-java")
            }
        """);
  }

  @Test
  void testJavaPluginEnabled() {
    final var result = execute(testProjectDir, "tasks");

    assertTrue(
        result.getOutput().contains("jar - Assembles a jar archive containing the main classes."));
  }

  @Test
  void testJavaPluginProperties_Java17() {
    createGradleProperties(testProjectDir, Map.of("kn.il.javaVersion", "17"));

    final var result = execute(testProjectDir, "properties");

    assertTrue(result.getOutput().contains("sourceCompatibility: 17"));
    assertTrue(result.getOutput().contains("targetCompatibility: 17"));
  }

  @Test
  void testJavaPluginProperties_Java8() {
    createGradleProperties(testProjectDir, Map.of("kn.il.javaVersion", "1.8"));

    final var result = execute(testProjectDir, "properties");

    assertTrue(result.getOutput().contains("sourceCompatibility: 1.8"));
    assertTrue(result.getOutput().contains("targetCompatibility: 1.8"));
  }

  @Test
  void testJavaPluginJarTask() {
    final var result = execute(testProjectDir, "jar");

    assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"));
  }
}
