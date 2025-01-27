package org.gradle.kotlin.dsl

import by.dh.plugins.common.dictionary.PluginVarsDictionary.PLUGIN_VERSION

inline val org.gradle.plugin.use.PluginDependenciesSpec.`il-bom`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.kn.il-bom").version(System.getProperty(PLUGIN_VERSION))

inline val org.gradle.plugin.use.PluginDependenciesSpec.`il-java`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.kn.il-java").version(System.getProperty(PLUGIN_VERSION))

inline val org.gradle.plugin.use.PluginDependenciesSpec.`il-spotless`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.kn.il-spotless").version(System.getProperty(PLUGIN_VERSION))

inline val org.gradle.plugin.use.PluginDependenciesSpec.`il-jacoco`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.kn.il-jacoco").version(System.getProperty(PLUGIN_VERSION))

inline val org.gradle.plugin.use.PluginDependenciesSpec.`il-publish`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("com.kn.il-publish").version(System.getProperty(PLUGIN_VERSION))
