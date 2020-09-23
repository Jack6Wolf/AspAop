### 一、简介
AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和运行期间动态代理实现程序功能的统一维护的一种技术。
AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。
利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
Android相关AOP常用实现方式： APT、AspectJ、Javassist、ASM、动态代理模式，本库是以静态织入的方式之一AspectJ来进行使用。
（在编译期织入，切面直接以字节码的形式编译到目标字节码文件中，这要求使用特殊的 Java 编译器。）


### 二、使用场景
最常见Java场景应该就是日志记录、检查用户权限、参数校验、事务处理、缓存等等了，
而对于Android来说也是比较广泛的(适用与Java的同样都适用于Android)，例如常见的有记录日志、
检查权限申请、判断用户登录状态、检查网络状态、过滤重复点击、多场景运行、延迟处理等等，可以根据项目实际需要的一些业务场景来自定义你所需要的。


### 三、引入方式

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


### 四、基本使用

1.降级处理

- 方法上
```java
//当前手机内存低于2G不使用LeakCanary、BlockCanary SDK
@NeedDemotion(NeedDemotion.LEVEL_3)
public static void initCheckUtil() {
    //初始化检测ui卡顿sdk
    BlockCanary.install(StarApplication.getInstance().getApplicationContext(), new AppBlockCanaryContext()).start();
    LeakCanary.install(StarApplication.getInstance().getApplication());
    Log.d(TAG, "AppSdkUtil ===== initCheckUtil");
}
```
- 在Application中
```java
//设置当前内存等级
AspAop.init().setMemoryLevel(StarApplication.getInstance().memoryLevel());
AspAop.init().setDemotionCallback(new DemotionCallback() {
    @Override
    public void demotion(MethodSignature methodSignature, Object[] objects) {
        //降级后的回调
    }
});
```

2.延迟
- 方法上
```java
//delayTask方法延迟5s执行
@Delay(delay = 5000)
private int delayTask() {
    Log.e("JACK", "延迟了！");
    return 1;
}
```
- 在Application中
```java
//延迟任务的回调
AspAop.init().setDelayCallback(new DelayCallback() {
     /**
     * @param key       {@link Delay#key()}
     * @param result    任务返回值
     * @param throwable 如果期间发生异常，无异常说明成功执行
     */
    @Override
    public void taskResult(String key, @Nullable Object result, @Nullable Throwable throwable) {
        Log.e("JACK", "delay:" + key + result + throwable);
    }
});
```
 
3.方法耗时日志
- 在方法或构造函数上
```java
@DurationLog
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    test(1);
    test(1, 2);
}

@DurationLog
public person() {
    try {
        Thread.sleep(200);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

//结果
2020-10-08 16:02:37.366 15279-15279/com.jack.aopdemo D/DurationLog: MainActivity#test(Integer)--->:0ms
2020-10-08 16:02:37.375 15279-15279/com.jack.aopdemo D/DurationLog: person#<init>--->:8ms
2020-10-08 16:02:37.575 15279-15279/com.jack.aopdemo D/DurationLog: person#<init>(Integer)--->:200ms
2020-10-08 16:02:37.575 15279-15279/com.jack.aopdemo D/DurationLog: MainActivity#onCreate--->:301ms

```
4.拦截
- 在方法或构造函数上
```
//创建对象提示登录
@Interceptor("needLogin")
public person() {
    try {
        Thread.sleep(200);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

//点击跳转提示登录
@Interceptor("needLogin")
public void click3(View view) {
    startActivity(new Intent(this, MainActivity2.class));
}
```
- 在Application中
```
AspAop.init().setInterceptorCallback(new InterceptorCallback() {
    @Override
    public boolean intercept(@NonNull String key, String methodName) {
        if (key.equals("needLogin")) {
            Toast.makeText(context, "请去登录！", Toast.LENGTH_SHORT).show();
            //拦截
            return true;
        }
        //放行
        return false;
    }
});
```
5.防抖动
- 在点击方法上
```
//重复点击button，间隔一秒后打印Log
findViewById(R.id.but2).setOnClickListener(new View.OnClickListener() {
    @SingleClick(value = 1000, ids = {R.id.but2})
    @Override
    public void onClick(View v) {
        Log.e("JACK", "使用防抖");
    }
});

@SingleClick(value = 1000, ids = {R.id.but1, R.id.but3})
@Override
public void onClick(View v) {
    switch (v.getId()) {
        case R.id.but1:
            Log.e("JACK", "使用防抖");
            break;
        case R.id.but2:
            Log.e("JACK", "没使用防抖");
            break;
        case R.id.but3:
            Log.e("JACK", "使用防抖");
            break;
        default:
            break;
    }
}
```

### 未来规划
- 权限控制
- 无痕埋点
- 全局日志记录
- 性能统计
- ~~异常处理~~（已完成）
- ......
