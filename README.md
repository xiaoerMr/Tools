
[![](https://jitpack.io/v/xiaoerMr/Tools.svg)](https://jitpack.io/#xiaoerMr/Tools)

# 1: 使用方式
第一步

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

第二步

```java
dependencies {
	        implementation 'com.github.xiaoerMr:Tools:最新版本号'
	}
```

# 2: Tools目录

默认为 kotlin 语言

|-  event	

	- LiveDataBus( kotlin ) 

	- LiveEventBus( java )

|- file

|- log

|- socket ( 使用时请放在 协程或者子线程中)

|- system 

|- toast

|-  view

|-  comment 常量类
