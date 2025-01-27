package com.kn.il.plugin.publish;

import static by.dh.plugins.common.dictionary.MavenRepositoryDictionary.ILCL_PASSWORD_ENV;
import static by.dh.plugins.common.dictionary.MavenRepositoryDictionary.ILCL_REPOSITORY_URL;
import static by.dh.plugins.common.dictionary.MavenRepositoryDictionary.ILCL_USERNAME_ENV;
import static by.dh.plugins.common.dictionary.PluginVarsDictionary.GIT_URL;
import static by.dh.plugins.common.dictionary.PluginVarsDictionary.GROUP_ID;
import static by.dh.plugins.common.dictionary.PluginVarsDictionary.WIKI_URL;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.net.URI.create;
import static java.util.Optional.ofNullable;

import java.util.Map;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.artifacts.repositories.PasswordCredentials;
import org.gradle.api.publish.PublicationContainer;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.maven.MavenPublication;

public class PublishPlugin implements Plugin<Project> {

  private static final String JAVA_COMPONENT = "java";

  @Override
  @SuppressWarnings("java:S5612")
  public void apply(final Project project) {
    project.apply(Map.of("plugin", "maven-publish"));

    final var publishExtension = project.getExtensions().getByType(PublishingExtension.class);
    publishExtension.publications((PublicationContainer publications) -> {
      final var publication = publications.create("maven", MavenPublication.class);
      publication.setGroupId(getProperty(GROUP_ID));
      ofNullable(getProperty(WIKI_URL))
          .ifPresent(url -> publication.getPom().getUrl().set(url));
      publication.getPom()
          .scm(scm -> ofNullable(getProperty(GIT_URL))
              .ifPresent(url -> scm.getConnection().set(format("scm:git:%s", url))));

      if (project.getComponents().stream()
          .anyMatch(component -> JAVA_COMPONENT.equalsIgnoreCase(component.getName()))) {
        publication.from(project.getComponents().getByName(JAVA_COMPONENT));
      }
    });

    publishExtension.repositories((RepositoryHandler repositories) -> {
      final MavenArtifactRepository mavenRepository = repositories.maven((MavenArtifactRepository maven) -> {
        maven.setName("kn.repository.ilcl");
        maven.setUrl(create(ILCL_REPOSITORY_URL));
        maven.credentials((PasswordCredentials credentials) -> {
          credentials.setUsername(getenv(ILCL_USERNAME_ENV));
          credentials.setPassword(getenv(ILCL_PASSWORD_ENV));
        });
      });
      repositories.add(mavenRepository);
    });
  }
}
