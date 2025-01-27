package by.dh.plugin.spotless;

import static by.dh.plugins.common.dictionary.GradleExecutorUtil.execute;
import static by.dh.plugins.common.dictionary.GradleInitUtil.createBuildGradle;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SpotlessPluginTest {

  @TempDir
  private File testProjectDir;

  @BeforeEach
  void init() {
    createBuildGradle(testProjectDir, """
            plugins {
                id("by.dh.java")
                id("by.dh.spotless")
            }
            
            repositories { 
                mavenCentral() 
            }
        """);
  }

  @Test
  void testSpotlessPluginEnabled() {
    final var result = execute(testProjectDir, "tasks");

    assertTrue(result.getOutput().contains("spotlessJava"));
    assertTrue(result.getOutput().contains("spotlessJavaApply"));
    assertTrue(result.getOutput().contains("spotlessJavaCheck"));
  }

  @Test
  void testSpotlessApplyEnabled() {
    final var result = execute(testProjectDir, "test", Map.of("SPOTLESS_PREFER_CHECK", "false"));

    assertFalse(result.getOutput().contains("spotlessJavaCheck"));
    assertTrue(result.getOutput().contains("spotlessJavaApply"));
  }

  @Test
  void testSpotlessCheckEnabled() {
    final var result = execute(testProjectDir, "test", Map.of("SPOTLESS_PREFER_CHECK", "true"));

    assertTrue(result.getOutput().contains("spotlessJavaCheck"));
    assertFalse(result.getOutput().contains("spotlessJavaApply"));
  }
}
