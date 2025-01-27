package org.gradle.kotlin.dsl;

@SuppressWarnings("all")
public class IlDependencies {

  public static void lombok(final DependencyHandlerScope scope) {
    scope.add("compileOnly", "org.projectlombok:lombok");
    scope.add("annotationProcessor", "org.projectlombok:lombok");
    scope.add("testCompileOnly", "org.projectlombok:lombok");
    scope.add("testAnnotationProcessor", "org.projectlombok:lombok");
  }

  public static void feign(final DependencyHandlerScope scope) {
    scope.add("implementation", "io.github.openfeign:feign-core");
    scope.add("implementation", "io.github.openfeign:feign-jackson");
    scope.add("implementation", "io.github.openfeign:feign-slf4j");
    scope.add("implementation", "io.github.openfeign:feign-spring4");
    scope.add("implementation", "io.github.openfeign:feign-okhttp");
  }

  public static void springStarter(final DependencyHandlerScope scope) {
    scope.add("implementation", "org.springframework.boot:spring-boot-starter");
    scope.add("testImplementation", "org.springframework.boot:spring-boot-starter-test");
  }

  public static void junit(final DependencyHandlerScope scope) {
    scope.add("testImplementation", "org.junit.jupiter:junit-jupiter-engine");
    scope.add("testImplementation", "org.mockito:mockito-core");
    scope.add("testImplementation", "org.mockito:mockito-junit-jupiter");
  }
}
