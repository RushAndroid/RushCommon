# RushCommon
Android Common Library

## Use
***Step 1***. Add the JitPack repository to your build file
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
***Step 2***. Add the dependency
```groovy
dependencies {
    implementation 'com.github.RushAndroid:RushCommon:v1.0.1'
}
```

## Features
1. BaseWidget
    - BaseActivity
    - BaseFragment
    - BaseApplication
    - BaseRecycleAdapter
    - BaseMultiRecycleItemView (multi layout recycle view)
2. ThreadUtil
    - Run
        + RunOnUiThread
        + RunOnBackgroundThread
