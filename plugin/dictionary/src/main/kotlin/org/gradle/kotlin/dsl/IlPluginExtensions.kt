package org.gradle.kotlin.dsl

import by.dh.plugins.common.dictionary.PluginVarsDictionary.PLUGIN_VERSION

inline val org.gradle.plugin.use.PluginDependenciesSpec.`dh-java`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("by.dh.java").version(System.getProperty(PLUGIN_VERSION))

inline val org.gradle.plugin.use.PluginDependenciesSpec.`dh-spotless`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("by.dh.spotless").version(System.getProperty(PLUGIN_VERSION))

inline val org.gradle.plugin.use.PluginDependenciesSpec.`dh-jacoco`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("by.dh.jacoco").version(System.getProperty(PLUGIN_VERSION))

inline val org.gradle.plugin.use.PluginDependenciesSpec.`dh-publish`: org.gradle.plugin.use.PluginDependencySpec
    get() = id("by.dh.publish").version(System.getProperty(PLUGIN_VERSION))
