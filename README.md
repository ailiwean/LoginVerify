
# LoginVerify
非侵入式对点击事件进行验证登陆，子线程运行，不影响UI绘制
  
### 使用    
 * **步骤一**： 
   
创建一个验证与执行登陆的实现类MyLogin
```
public class MyLogin implements LoginChunk {
    
    //验证是否登陆
    @Override
    public boolean verifyLogin() {
        return false;
    }

    //去登陆
    @Override
    public void goLogin() {

    }

    //点击间隔
    @Override
    public long breakTime() {
        return 1000;
    }
}
```
如果只想实现屏蔽多次点击效果，就让verifyLogin()返回true即可。

 * **步骤二**：
     
   生成一个拦截器，传入第一步中的实例化对象myLogin
```
        LoginVerify.get(myLogin);
```
然后就可以肆无忌惮添加已存在点击事件的View了

```
.registerView(centerView, centerView2, centerView3, centerView4, centerView5, centerView6, centerView7);
```
当然也提供了一个更舒服的写法使用注解标记

 * **步骤一**： 
```
  @LoginDunk
  View centerView;
```
使用@LoginDunk注解标记一个View

 * **步骤二**：
 
还是传入一个myLogin

```
 AnLoginVerify.bind(myLogin, this);
```
###  引入
都是老司机了， 废话不多说

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
 
```
    implementation 'com.github.ailiwean:LoginVerify:1.1.1'

```

##### 第三方库
###### BaseRecyclerViewAdapterHelper
目前也支持对该库的Item，以及Child点击事件的拦截

```
.registerApapter(BaseQuickAdapter adapter)
```
```
.registerAdapterChild(BaseQuickAdapter adapter)
```

完事。



##### 废话
sorry， 笔者不想喝coffee， 只想要一个star， 右上角戳两下，谢谢🙏🙏
