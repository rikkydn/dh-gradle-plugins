package by.dh.plugin.java;

import static java.util.Optional.ofNullable;

import java.util.Map;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.api.tasks.testing.Test;

public class JavaPlugin implements Plugin<Project> {

  @Override
  public void apply(final Project project) {
    project.apply(Map.of("plugin", "java-library"));

    project.getRepositories().mavenLocal();
    project.getRepositories().maven((MavenArtifactRepository repository) -> {
      repository.setUrl("http://repository.int.kn/repository/maven");
      repository.setAllowInsecureProtocol(true);
    });

    final var java = (JavaPluginExtension) project.getExtensions().getByName("java");
    ofNullable(project.getProperties().get("kn.il.javaVersion"))
        .map(Object::toString)
        .ifPresent((String value) -> {
          java.setSourceCompatibility(value);
          java.setTargetCompatibility(value);
        });

    final var test = (Test) project.getTasks().getByName("test");
    test.useJUnitPlatform();

    final var compileJava = (JavaCompile) project.getTasks().getByName("compileJava");
    compileJava.getOptions().getCompilerArgs().add("-parameters");
  }
}
