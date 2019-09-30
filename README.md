# LoginVerify
非侵入式对点击事件进行验证登陆，延迟反射，避免拉长UI响应。 极度轻量。

##### 存在意义
  每个APP几乎都会有一个登陆功能，当然与之对应的某些按钮点击需要验证登陆并跳转到登陆，如果是使用传统的继承OnClick等点击事件类，使用起来总感觉有点不大舒服并且后边有所改动，就需要拿出我们看家本领，复制粘贴了。
  
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

}

```
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
可以绑定的类型有Activity，Fragment， 其他Object对象，  其中Activity及Fragment都会在界面绘制完成后才会进行反射操作，这样也保证了UI绘制的及时性。

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
	        implementation 'com.github.ailiwean:LoginVerify:Tag'

```
完事。



##### 废话
sorry， 笔者不想喝coffee， 只想要一个star， 右上角戳两下，谢谢🙏🙏
