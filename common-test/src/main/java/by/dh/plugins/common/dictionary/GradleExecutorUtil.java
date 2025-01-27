package by.dh.plugins.common.dictionary;

import java.io.File;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;

@Log
@UtilityClass
public class GradleExecutorUtil {

  public static BuildResult execute(final File projectDir, final String command) {
    final var execution = getGradleRunner(projectDir, command).build();
    log.info(execution.getOutput());
    return execution;
  }

  public static BuildResult execute(final File projectDir, final String command, Map<String, String> environment) {
    final var execution = getGradleRunner(projectDir, command)
        .withEnvironment(environment)
        .build();
    log.info(execution.getOutput());
    return execution;
  }

  public static BuildResult executeFailed(final File projectDir, final String command) {
    final var execution = getGradleRunner(projectDir, command).buildAndFail();
    log.info(execution.getOutput());
    return execution;
  }

  private static GradleRunner getGradleRunner(final File projectDir, final String command) {
    return GradleRunner.create()
        .withProjectDir(projectDir)
        .withPluginClasspath()
        .withArguments(command, "--info");
  }

}
