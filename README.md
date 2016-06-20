# BadgeView
在任何View上添加红点
ScreenShort:
----
![github](https://github.com/vvinner/BadgeView/blob/master/screenshorts/screen1.png "github")
Usage:
------
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
Code:
----
      BadgeTextView.
                setBadgeColor(Color.RED).
                setCount(99).
                setShown(true);//Defualt true
