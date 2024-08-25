# Godot Firebase Analytics for Android

This Plugin is developed for one of our game [Carrom Karrom: Carrom Board](https://play.google.com/store/apps/details?id=com.bloggernepal.carrom), you can check the game for live demo. Our requirements may not be enough for your need but, you can modify to fit your need check [DEVELOPMENT.md](DEVELOPMENT.md)

With this plugin you can

Add Firebase Analytics to your game. [only for android]


### Setup Firebase

1. Setup Firebase
2. Add android (use the package name) [tips: can add multiple add debug as well]
3. you can download the `google-services.json`

### Setup Project
1. If you have not already, install the android build tool
2. Build and run the project on android
3. Download the `GodotFirebaseAnalytics-vx.x.x.aar` and `GodotFirebaseAnalytics.gdap` from [release](https://github.com/InfoDevkota/godot-firebase-analytics-for-android/releases)
4. Place the `GodotFirebaseAnalytics-vx.x.x.aar` and `GodotFirebaseAnalytics.gdap` in `android/plugins`
5. copy the downloaded `google-services.json` to `android/build/`
6. In Godot Menu, Project -> Export... -> Select Android, Check on Use Custom Build and check on `Godot Firebase Analytics`.
7. update `android/build/build.gradle` to configure to use `google-services.json`. Check below for details.
8. You can build and test the game.

## Loading configs from google-services.json
To load the configs from `google-services.json`. This is necessary step. We need to edit the build.gradle in the godot android. On your `[[godot-project]]/android/build/build.gradle` inside `dependencies` of `buildscript` add
```
classpath 'com.google.gms:google-services:4.3.+'
```

Sample to avoid confusion
```
    dependencies {
        classpath libraries.androidGradlePlugin
        classpath libraries.kotlinGradlePlugin
        classpath 'com.google.gms:google-services:4.3.+'
//CHUNK_BUILDSCRIPT_DEPENDENCIES_BEGIN
//CHUNK_BUILDSCRIPT_DEPENDENCIES_END

    }
```

Then in the last of the file add.

```
apply plugin: 'com.google.gms.google-services'
```

You can refrence the [godot/](godot/) directory plugins and android build configs sample.

### Some Helpful commands
```
# to view the logcat
$ adb logcat

# to clear the logcat
$ adb logcat -c

# to look for certain tags
$ adb logcat -s godot

# plugin registration
$ adb logcat -s GodotPluginRegistry

```



## Available APIs

| Method Name | Parameters | Return Type | Description                                           |
|-------------|------------|-------------|-------------------------------------------------------|
| firstTest   | -          | -           | Just a test function to make sure the plugin is configured correctly. Upon called this emitts the `on_first_test` signal  |
| initializeFirebase   | -          | -           | This function is used to initalize the firebase. The firebase usually initilize itself. We just call it just to confirm  |
| log_event   | string, Dictionary (event_name, parameter)  | -           | This function is used to log event with string name with optional additional properties, can be empty dictionary, but must be a dictionary  |

## Available Signals
| signal | callback parameters | Description |
|--------|---------------------|-------------|
| on_first_test | boolean | always true |

## use in godot
A gdscript is included in [godot/scripts/Firebase.gd](godot/scripts/Firebase.gd) take a reference of that.

## Future of the plugin
This plugin is released as it is, assuming it will provide some headstart while writing your own.

But if you find the plugin usefull and want to have some additional apis, let us know through the github issue, we can implement that.

## Thanks to
While developing this plugin I took lots of refrences from plugins these people has maintained for godot.
- [Shin-NiL](https://github.com/Shin-NiL)
- [Constantin Gisca](https://github.com/cgisca)
- [Randy Tan](https://github.com/oneseedfruit)
- [Mitch](https://github.com/finepointcgi)

## Development
### Dependncies version
When we use BOM we don't need to specify version number for other dependent libraries.

But we need them when creating the `.gdap` We can find the associated version from https://firebase.google.com/support/release-notes/android#2024-04-11. If version or any library changed, it will be specified, else no version changed for that particula library for that `bom` so look for previous version release note to get the library version.
