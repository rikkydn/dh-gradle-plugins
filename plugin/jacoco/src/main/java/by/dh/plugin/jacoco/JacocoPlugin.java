package by.dh.plugin.jacoco;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.Map;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.testing.Test;
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension;
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification;
import org.gradle.testing.jacoco.tasks.JacocoReport;
import org.gradle.testing.jacoco.tasks.JacocoReportsContainer;

public class JacocoPlugin implements Plugin<Project> {

  @Override
  public void apply(final Project project) {
    project.apply(Map.of("plugin", "java"));
    project.apply(Map.of("plugin", "jacoco"));

    final var jacocoExtension = (JacocoPluginExtension) project.getExtensions().getByName("jacoco");
    jacocoExtension.setToolVersion("0.8.12");

    final var test = (Test) project.getTasks().getByName("test");
    test.finalizedBy(project.getTasks().getByName("jacocoTestReport"));

    final var jacocoTestReport = (JacocoReport) project.getTasks().getByName("jacocoTestReport");
    jacocoTestReport.dependsOn(project.getTasks().getByName("test"));
    jacocoTestReport.reports((JacocoReportsContainer reports) -> {
      reports.getXml().getRequired().set(true);
      reports.getHtml().getRequired().set(true);
    });
    jacocoTestReport.finalizedBy(project.getTasks().getByName("jacocoTestCoverageVerification"));

    final var jacocoTestCoverageVerification =
        (JacocoCoverageVerification) project.getTasks().getByName("jacocoTestCoverageVerification");
    jacocoTestCoverageVerification.violationRules(
        rules -> rules.rule(
            rule -> rule.limit(
                limit -> limit.setMinimum(BigDecimal.valueOf(Double.parseDouble(
                    requireNonNull(project.property("by.dh.plugins.jacoco.coverage")).toString()))))));
  }
}
