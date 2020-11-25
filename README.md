
[![](https://jitpack.io/v/xiaoerMr/Tools.svg)](https://jitpack.io/#xiaoerMr/Tools)


## 项目地址
项目地址：  https://github.com/xiaoerMr/Tools
[ 项目地址 ](https://github.com/xiaoerMr/Tools)
## 1: 使用方式


```kotlin
// 第一步
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
// 第二步
dependencies {
	 implementation 'com.github.xiaoerMr:Tools:最新版本号'
} 
```

## 2: Tools目录
### Base
BaseActivity
	封装 “页面加载” 、 “页面错误”、“页面空数据” 三种状态。
###  event	
   事件总线
   	- LiveDataBus( kotlin )
   	- LiveEventBus( java )
###  file
###  http  
使用 Retrofit
（源地址： https://github.com/square/retrofit ）
###  img
使用封装Glide，便于更改其他框架，而不用更改业务代码。
（源地址： https://github.com/bumptech/glide ）
###  other
dp转px
### permission
 封装 PermissionX，便于更改其他框架，而不用更改业务代码。
   （源地址： https://github.com/guolindev/PermissionX ）
### socket 
   **请不要直接在主线程中调用**
 封装 socket 使用。
### system 
 获取进程名称
###  view
 - 封装EditView  
 - 封装SpinnerView 
 -  封装TextView

   示例：![在这里插入图片描述](https://img-blog.csdnimg.cn/20201123113434878.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzUwMjc5MQ==,size_16,color_FFFFFF,t_70#pic_center)
### comment 常量类

 - 常用权限
 -  Context . toast 扩展 
 - Log 封装
