### 痛点
实际开发过程中，Android项目中往往会有多个gradle plugin，有些可能是三方库的，有些可能是自定义的。自定义插件往往会根据不同功能，封装成不同的插件。对于本地调试gradle plugin，官方方案是把插件代码放在buildsrc目录下，默认只支持同时调试一个plugin。

### buildsrc目录支持多个plugin调试方案
app中的build.gradle

```
//第一个插件
apply plugin: com.keding.plugins.plugin.MyFirstPlugin
//第二个插件
apply plugin: com.keding.plugins.plugin.MySecondPlugin
```

buildsrc目录结构：

```
├── buildsrc
│   ├── build.gradle
│   ├── buildsrc.iml
│   ├── gradle-plugin-first
│   │   ├── gradle-plugin-first.iml
│   │   └── src
│   │       └── main
│   │           └── groovy
│   │               └── com.keding.plugins
│   │                   └── plugin
│   │                       └── MyFirstPlugin.groovy
│   ├── gradle-plugin-second
│   │   ├── gradle-plugin-second.iml
│   │   └── src
│   │       └── main
│   │           └── groovy
│   │               └── com.keding.plugins
│   │                   └── plugin
│   │                       └── MySecondPlugin.groovy
│   └── settings.gradle

```
buildsrc目录下的build.gradle（核心）

```
sourceSets {
    main {
        groovy {
            srcDir 'gradle-plugin-first/src'
            srcDir 'gradle-plugin-second/src'
        }
    }
}
```


plugin代码

```
class MyFirstPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("自定义插件MyFirstPlugin执行了！！！" + " project name:" + project.getName())
    }
}
```

代码执行结果

```
> Configure project :app
自定义插件MyFirstPlugin执行了！！！ project name:app
自定义插件MySecondPlugin执行了！！！ project name:app

BUILD SUCCESSFUL in 9s

```


