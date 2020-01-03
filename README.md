# NoOverrideCallback
拒绝覆盖方法，直接接口回调！onActivityResult / onRequestPermissionsResult / ... ，调用方法传入Callback多直接！  [![JitPack](https://jitpack.io/v/ParfoisMeng/NoOverrideCallback.svg)](https://jitpack.io/#ParfoisMeng/NoOverrideCallback)

- - - - - 

### 使用
 - 引用类库 *请将last-version替换为最新版本号 [![](https://jitpack.io/v/ParfoisMeng/NoOverrideCallback.svg)](https://jitpack.io/#ParfoisMeng/NoOverrideCallback)
```
    // 1.添加jitpack仓库
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    // 2.添加项目依赖（last-version替换为最新版本号）
    dependencies {
        implementation 'com.github.ParfoisMeng:NoOverrideCallback:last-version'
    }
```

- 代码
```
    // 将 activity.startActivityForResult 替换成下面的方式
    NoOverrideCallback.with(this)
            .startActivity4Callback(new Intent(), new ActivityResultListener() {
                @Override
                public void onActivityResult(int resultCode, @Nullable Intent data) {
                    // TODO 正常情况
                }

                @Override
                public void onFailed(@Nullable Throwable throwable) {
                    // TODO 异常情况
                }
            });

    // startActivity4Callback 的 Kotlin 优化写法如下：
    startActivity4Callback(
            intent: Intent,
            onActivityResultMethod: ((resultCode: Int, data: Intent?) -> Unit)? = null,
            onFailedMethod: ((throwable: Throwable?) -> Unit)? = null
    )

    // 示例请查看源码
```

### 感谢
- [RxPermissions](https://github.com/tbruyelle/RxPermissions)
- [InlineActivityResult](https://github.com/florent37/InlineActivityResult)

### 更新
* 初版发布 - 1.0.0

### 计划
* 添加更多类似 startActivityForResult 这种不方便使用的 callback 方法

### 支持
劳烦各位大佬给个Star让我出去好装B行嘛！

### 其他
已使用<b>996 License</b>，为程序员发声，为自己发声。

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
