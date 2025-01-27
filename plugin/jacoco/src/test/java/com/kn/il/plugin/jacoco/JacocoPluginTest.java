package com.kn.il.plugin.jacoco;

import static by.dh.plugins.common.dictionary.GradleExecutorUtil.execute;
import static by.dh.plugins.common.dictionary.GradleExecutorUtil.executeFailed;
import static by.dh.plugins.common.dictionary.GradleInitUtil.createBuildGradle;
import static by.dh.plugins.common.dictionary.GradleInitUtil.createGradleProperties;
import static by.dh.plugins.common.dictionary.GradleInitUtil.createJavaClass;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JacocoPluginTest {

  @TempDir
  private File testProjectDir;

  @BeforeEach
  void init() {
    createBuildGradle(testProjectDir, """
            plugins {
                id("com.kn.il-bom")
                id("com.kn.il-jacoco")
            }
            
            dependencies {
              testImplementation("org.junit.jupiter:junit-jupiter-engine")
            }
        """);
  }

  @Test
  void testJacocoPluginEnabled() {
    //given
    createGradleProperties(testProjectDir, Map.of("kn.il.jacocoCoverage", "1.0"));

    //when
    final var result = execute(testProjectDir, "tasks");

    //then
    assertTrue(result.getOutput().contains("jacocoTestReport"));
    assertTrue(result.getOutput().contains("jacocoTestCoverageVerification"));
  }

  @Test
  void testJacocoPluginProperties_Coverage() {
    //given
    createGradleProperties(testProjectDir, Map.of("kn.il.jacocoCoverage", "0.5"));
    createJavaClass(testProjectDir, "src.main.java.com.kn.test", "SampleClass", """
        package com.kn.test;
                
        public class SampleClass {
          public void sample() {
            System.out.println("Test");
          }
        }
        """);
    createJavaClass(testProjectDir, "src.test.java.com.kn.test", "SampleClassTest", """
        package com.kn.test;
                
        import org.junit.jupiter.api.Test;
                
        public class SampleClassTest {
                
          @Test
          public void sample() {
          }
        }
        """);

    //when
    final var result = executeFailed(testProjectDir, "test");

    //then
    assertTrue(result.getOutput()
        .contains("instructions covered ratio is 0.0, but expected minimum is 0.5"));
    assertTrue(result.getOutput()
        .contains("BUILD FAILED"));
  }
}
