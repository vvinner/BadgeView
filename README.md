# BadgeView
在任何View上添加统一样式的红点<br>
如果布局是继承自ViewGroup(LinearLayout、RelativeLayout)请为该布局设置Background<br>
'android:background="#00000000"'<br>

ScreenShort:
====
![github](https://github.com/vvinner/BadgeView/blob/master/screenshorts/screen1.png "github")<br>
Usage:
====
```Xml
    <com.porster.badgeview.badgeview.BadgeTextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:drawableTop="@mipmap/ic_launcher"
            android:gravity="center"
            android:text="Hello World"
            app:badge_count="1"
            app:badge_color="#20A6EF"
            app:badge_padding_right="20dp"
            app:badge_padding_top="20dp"
            app:badge_none_show="true"
            />
```
Code:
====
```Java
      BadgeTextView.
                setBadgeColor(Color.RED).
                setCount(99).
                setShown(true);//Defualt true
```
NewApi
====
IBadgeView新增<br>
setPaddingTop<br>
setPaddingRight<br>
函数对应<br>
app:badge_padding_top<br>
app:badge_padding_right<br>
由于该函数不是必须,考虑可自行配置，如果需要,请在View中添加以下函数
```Java
public class BadgeTextView extends TextView implements IBadgeViewImpl{
  ...
  public IBadgeView setPaddingTop(int paddingTop) {
   return mBadgeView.setPaddingTop(paddingTop);
  }
  public IBadgeView setPaddingRight(int paddingRight) {
   return mBadgeView.setPaddingRight(paddingRight);
  }
 }
```
