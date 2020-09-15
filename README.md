# How to

- Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
}
dependencies {
        classpath "com.android.tools.build:gradle:4.0.1"
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
}
```
- Step 2. Add the dependency
```groovy
dependencies {
	        implementation 'com.github.Jack6Wolf:AspAop:1.0.0'
}
```

- Step 3. Add apply plugin
```groovy
apply plugin: 'android-aspectjx'
```