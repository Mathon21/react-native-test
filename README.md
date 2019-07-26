# react-native-test-madong
测试react native 封装原生打包上传到npm
参考文献 android http://www.liuchungui.com/blog/2016/05/08/reactnativezhi-yuan-sheng-mo-kuai-kai-fa-bing-fa-bu-androidpian/
参考文献 ios  http://www.liuchungui.com/blog/2016/05/02/reactnativezhi-yuan-sheng-mo-kuai-kai-fa-bing-fa-bu-iospian/
android 有几个坑，新建的module，不能引入，解决办法是，点击app然后新建module
上传npm的时候也有坑，
403报错，先把本地淘宝镜像设置成官方镜像，这个需要梯子工具，
401报错，上传的时候我的npm账号邮箱没有激活，激活一下就好了，换个绑定邮箱也是可以的

使用
npm install react-native-test-madong
react-native link react-native-test-madong

这个事例主要是写了几个原生端的一些方法，让js来调用    
    
