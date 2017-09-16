package louis.demo.acitivty;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;

public class MenuDemoActivity extends Activity {
	
	private View menuBtn;
	private View menuView;
	private View menuBackground;
	public static boolean isExpanded; // 菜单是否展开
	
	/**
	 * 实现菜单功能
	 * 
	 * @param activity
	 * @param menuBtn
	 * @param menuView
	 * @param menuBackground
	 */
	public static void setMenuFunc(final Activity activity, final View menuBtn,
			final View menuView, final View menuBackground) {
		isExpanded = false;
		menuBtn.setOnClickListener(new OnClickListener() {

			Animation anim1 = AnimationUtils.loadAnimation(activity,
					R.anim.alpha_translate1);
			Animation anim2 = AnimationUtils.loadAnimation(activity,
					R.anim.alpha_translate2);

			@Override
			public void onClick(View v) {
				if (!isExpanded) {
					menuView.setVisibility(View.VISIBLE);
					menuBackground.setVisibility(View.VISIBLE);
					menuView.startAnimation(anim1);
					isExpanded = true;
					((CheckBox) menuBtn)
							.setButtonDrawable(R.drawable.menu_btn2);
				} else {
					menuView.startAnimation(anim2);
					menuView.setVisibility(View.GONE);
					menuBackground.setVisibility(View.GONE);
					isExpanded = false;
					((CheckBox) menuBtn)
							.setButtonDrawable(R.drawable.menu_btn1);
				}
			}
		});

		menuBackground.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation anim2 = AnimationUtils.loadAnimation(activity,
						R.anim.alpha_translate2);
				menuView.startAnimation(anim2);
				menuView.setVisibility(View.GONE);
				menuBackground.setVisibility(View.GONE);
				isExpanded = false;
				((CheckBox) menuBtn).setButtonDrawable(R.drawable.menu_btn1);
			}
		});
	}

	@Override
	public void onBackPressed() {
		if (MenuDemoActivity.isExpanded) {
			Animation anim2 = AnimationUtils.loadAnimation(MenuDemoActivity.this,
					R.anim.alpha_translate2);
			menuView.startAnimation(anim2);
			menuView.setVisibility(View.GONE);
			isExpanded = false;
			menuBackground.setVisibility(View.GONE);
			((CheckBox) menuBtn).setButtonDrawable(R.drawable.menu_btn1);
		} else {
			AlertDialog.Builder builder = new Builder(MenuDemoActivity.this);
			builder.setMessage("确定要退出吗？");
			builder.setPositiveButton("确定",
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							System.exit(0);
						}
					});
			builder.setNegativeButton("取消",
					new android.content.DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builder.create().show();
		}
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        menuBtn = findViewById(R.id.btn1);
        menuView = findViewById(R.id.linear1);
		menuBackground = findViewById(R.id.bg);
		MenuDemoActivity.setMenuFunc(MenuDemoActivity.this, menuBtn, menuView,
				menuBackground);
    }
}