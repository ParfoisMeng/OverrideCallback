# NoOverrideCallback
不用继承方法，直接以回调形式启动。现已支持 `startActivityForResult-onActivityResult` 对应逻辑。  [![JitPack](https://jitpack.io/v/ParfoisMeng/NoOverrideCallback.svg)](https://jitpack.io/#ParfoisMeng/NoOverrideCallback)

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
    // 将 startActivityForResult 替换成下面的调用，提供 2 种模式，适应更多情况
    FragmentActivity.start4Callback(otherStart: (fragment: Fragment, code: Int) -> Unit, callback: ((resultCode: Int, data: Intent?) -> Unit)? = null)
    FragmentActivity.start4Callback(intent: Intent, callback: ((resultCode: Int, data: Intent?) -> Unit)? = null)

    // 源码示例
    // intent 对象
    val intent = Intent(this, clz)
    // 调用方式 1 直接传入 intent 对象
    start4Callback(
            intent = intent,
            callback = { resultCode, data ->
                // do onActivityResult
            }
    )
    // 调用方式 2 在 block 中用指定参数构造 startActivityForResult
    start4Callback(
            block = { fragment, requestCode -> fragment.startActivityForResult(intent, requestCode) },
            callback = { resultCode, data ->
                // do onActivityResult
            }
    )
```

### 感谢
- [RxPermissions](https://github.com/tbruyelle/RxPermissions)
- [InlineActivityResult](https://github.com/florent37/InlineActivityResult)

### 更新
* 大幅更新，Kotlin 更好用 - 2.0
* 初版发布 - 1.0.0

### 计划
* 添加更多类似 startActivityForResult 这种不方便使用的 callback 方法

### 支持
劳烦各位大佬给个Star让我出去好装B行嘛！

### 其他
已使用<b>996 License</b>，为程序员发声，为自己发声。

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)
