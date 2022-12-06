# htmx-jetbrains

![Build](https://github.com/hugohomesquita/htmx-jetbrains/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Release Workflow

1. Update `gradle.properties` with the **new plugin version**
2. Update `CHANGELOG.md` to reflect the new changes in the **Unreleased** section
3. Push `main` branch and allow all GitHub Actions to run
4. Go to [releases page](https://github.com/hugohomesquita/htmx-jetbrains/releases) and edit/publish draft release

## Plugin Description

<!-- Plugin description -->
This plugin adds support for the following [htmx](https://github.com/bigskysoftware/htmx) features:

- Auto-complete htmx attributes such as `hx-get`

<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:

  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "HTMX Support"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/hugohomesquita/htmx-jetbrains/releases/latest) and install it
  manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
