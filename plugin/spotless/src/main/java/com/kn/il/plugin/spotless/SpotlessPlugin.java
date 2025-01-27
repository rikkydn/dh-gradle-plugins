package com.kn.il.plugin.spotless;

import static java.util.Optional.ofNullable;

import com.diffplug.gradle.spotless.JavaExtension;
import com.diffplug.gradle.spotless.SpotlessExtension;
import java.util.Map;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.compile.JavaCompile;

public class SpotlessPlugin implements Plugin<Project> {

  @Override
  public void apply(final Project project) {
    project.apply(Map.of("plugin", "java"));
    project.apply(Map.of("plugin", "com.diffplug.spotless"));

    project.getDependencies().add(
        "implementation", "com.google.googlejavaformat:google-java-format:1.15.0");

    final var spotless = (SpotlessExtension) project.getExtensions().getByName("spotless");
    spotless.java(JavaExtension::googleJavaFormat);

    final var compileJava = (JavaCompile) project.getTasks().getByName("compileJava");
    ofNullable(System.getenv("SPOTLESS_PREFER_CHECK"))
        .filter("true"::equalsIgnoreCase)
        .ifPresentOrElse(
            value -> compileJava.dependsOn(project.getTasks().getByName("spotlessCheck")),
            () -> compileJava.dependsOn(project.getTasks().getByName("spotlessApply"))
        );
  }
}
