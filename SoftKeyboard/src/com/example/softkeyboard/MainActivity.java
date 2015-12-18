package com.example.softkeyboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/*
android:windowSoftInputMode="stateAlwaysHidden|adjustPan" 的值填写
 【A】stateUnspecified：软键盘的状态并没有指定，系统将选择一个合适的状态或依赖于主题的设置

【B】stateUnchanged：当这个activity出现时，软键盘将一直保持在上一个activity里的状态，无论是隐藏还是显示

【C】stateHidden：用户选择activity时，软键盘总是被隐藏

【D】stateAlwaysHidden：当该Activity主窗口获取焦点时，软键盘也总是被隐藏的

【E】stateVisible：软键盘通常是可见的

【F】stateAlwaysVisible：用户选择activity时，软键盘总是显示的状态

【G】adjustUnspecified：默认设置，通常由系统自行决定是隐藏还是显示

【H】adjustResize：该Activity总是调整屏幕的大小以便留出软键盘的空间

【I】adjustPan：当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖和用户能总是看到输入内容的部分
 * 
 */

public class MainActivity extends Activity implements View.OnLayoutChangeListener {

  // Activity最外层的Layout视图
  private View activityRootView;
  // 屏幕高度
  private int screenHeight = 0;
  // 软件盘弹起后所占高度阀值
  private int keyHeight = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    activityRootView = findViewById(R.id.root_layout);
    // 获取屏幕高度
    screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
    // 阀值设置为屏幕高度的1/3
    keyHeight = screenHeight / 3;

    activityRootView.addOnLayoutChangeListener(this);
  }

  @Override
  public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

    // old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值

    // System.out.println(oldLeft + " " + oldTop +" " + oldRight + " " +
    // oldBottom);
    // System.out.println(left + " " + top +" " + right + " " + bottom);

    // 现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
    if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {

      Toast.makeText(MainActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

    } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {

      Toast.makeText(MainActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();

    }

  }

}
