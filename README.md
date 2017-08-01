# GJJDrawable
反编译apk实现酷炫的自定义进度条
# 详细的逆向分析
一.[《Mac下反编译apk》](http://www.jianshu.com/p/432afc55be58)

二.[《反编译apk实现炫酷的进度条动画》](http://www.jianshu.com/p/1481c2267ad6)

之前看到一个也是反编译实现滚筒效果 [androidWheelView](https://github.com/weidongjian/androidWheelView)

# 使用
```
private void start() {
        this.gjjDrawable = new GJJDrawable(this);
        this.gjjDrawable.a(new GJJDrawable.OnProgressListener() {
            public void a(int paramAnonymousInt) {
                progressInt = paramAnonymousInt;
                progress.setText(String.valueOf(paramAnonymousInt));
            }

            public void a(boolean paramAnonymousBoolean) {
                if (gjjDrawable.isRunning())
                    gjjDrawable.a();
            }
        });
        ((ImageView) findViewById(R.id.progress_animation)).setImageDrawable(gjjDrawable);

        gjjDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ( progressInt >= 10) {
                    gjjDrawable.a(1.0f);
                }
            }
        },2000);
    }
  ```
