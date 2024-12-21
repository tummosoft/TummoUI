package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static com.tummosoft.android.views.xBottomSheet _xsheetdialog = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
com.tummosoft.android.views.xShadowButton _btn1 = null;
com.tummosoft.android.views.xShadowButton _btn2 = null;
com.tummosoft.android.utils.ResourcesHelper _tuutils = null;
int _logo = 0;
com.tummosoft.android.utils.GradientHelper _clr = null;
float[] _radis = null;
String[] _cl = null;
com.tummosoft.android.views.xShadowButton _btnround = null;
com.tummosoft.android.utils.DrawableHelper _dh = null;
com.tummosoft.android.views.xShadowImageView _img3 = null;
com.tummosoft.android.views.xShadowImageView _img4 = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"Layout\")";
mostCurrent._activity.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="Dim btn1 As xShadowButton";
_btn1 = new com.tummosoft.android.views.xShadowButton();
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="btn1.initialize(\"btn1\")";
_btn1.initialize(processBA,"btn1");
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="btn1.Text = \"btn1\"";
_btn1.setText("btn1");
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="btn1.UnpressedColor = \"#9cea1b\"";
_btn1.setUnpressedColor("#9cea1b");
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="btn1.TextColor = \"#ffffff\"";
_btn1.setTextColor("#ffffff");
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="btn1.setPadding(5dip, 10dip, 5dip, 5dip)";
_btn1.setPadding(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="btn1.Radius = 6dip";
_btn1.setRadius(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)));
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="Activity.AddView(btn1, 20%x,10dip, 40dip, 40dip)";
mostCurrent._activity.AddView((android.view.View)(_btn1.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="Dim btn2 As xShadowButton";
_btn2 = new com.tummosoft.android.views.xShadowButton();
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="btn2.initialize(\"btn2\")";
_btn2.initialize(processBA,"btn2");
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="btn2.Text = \"btn2\"";
_btn2.setText("btn2");
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="btn2.UnpressedColor = \"#0075b4\"";
_btn2.setUnpressedColor("#0075b4");
RDebugUtils.currentLine=131088;
 //BA.debugLineNum = 131088;BA.debugLine="btn2.TextColor = \"#ffffff\"";
_btn2.setTextColor("#ffffff");
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="btn2.Alpha = 40";
_btn2.setAlpha((float) (40));
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="btn2.PressedColor = \"#f0ea20\"";
_btn2.setPressedColor("#f0ea20");
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="btn2.setPadding(5dip, 10dip, 5dip, 5dip)";
_btn2.setPadding(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="btn2.ShapeType = 0";
_btn2.setShapeType((int) (0));
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="btn2.Gravity = Gravity.CENTER";
_btn2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="btn2.Elevation = 0.2f";
_btn2.setElevation((float) (0.2f));
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="btn2.setShadowLayer(8.0f, 2.0f, 2.0f, \"#494942\")";
_btn2.setShadowLayer((float) (8.0f),(float) (2.0f),(float) (2.0f),"#494942");
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="Activity.AddView(btn2, 20%x, 60dip, 60dip, 60dip)";
mostCurrent._activity.AddView((android.view.View)(_btn2.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=131098;
 //BA.debugLineNum = 131098;BA.debugLine="Dim tuUtils As ResourcesHelper";
_tuutils = new com.tummosoft.android.utils.ResourcesHelper();
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="tuUtils.initialize";
_tuutils.initialize(processBA);
RDebugUtils.currentLine=131101;
 //BA.debugLineNum = 131101;BA.debugLine="Dim logo As Int = tuUtils.getResourceId(\"icon\",\"d";
_logo = _tuutils.getResourceId("icon","drawable");
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="Dim clr As GradientHelper";
_clr = new com.tummosoft.android.utils.GradientHelper();
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="clr.initialize(clr.Orientation_RIGHT_LEFT)";
_clr.initialize(processBA,_clr.Orientation_RIGHT_LEFT);
RDebugUtils.currentLine=131105;
 //BA.debugLineNum = 131105;BA.debugLine="Dim radis() As Float  = Array As Float(90.0f,90.0";
_radis = new float[]{(float) (90.0f),(float) (90.0f),(float) (90.0f),(float) (90.0f),(float) (90.0f),(float) (90.0f),(float) (90.0f),(float) (90.0f)};
RDebugUtils.currentLine=131106;
 //BA.debugLineNum = 131106;BA.debugLine="clr.Radius = radis";
_clr.setRadius(_radis);
RDebugUtils.currentLine=131107;
 //BA.debugLineNum = 131107;BA.debugLine="clr.setGradientCenter(5,5)";
_clr.setGradientCenter((int) (5),(int) (5));
RDebugUtils.currentLine=131108;
 //BA.debugLineNum = 131108;BA.debugLine="clr.Orientation = clr.Orientation_TL_BR";
_clr.setOrientation(_clr.Orientation_TL_BR);
RDebugUtils.currentLine=131109;
 //BA.debugLineNum = 131109;BA.debugLine="Dim cl() As String  = Array As String(\"#00F9E5\",\"";
_cl = new String[]{"#00F9E5","#6772FF","#4A54FF"};
RDebugUtils.currentLine=131110;
 //BA.debugLineNum = 131110;BA.debugLine="clr.Colors = cl";
_clr.setColors(_cl);
RDebugUtils.currentLine=131111;
 //BA.debugLineNum = 131111;BA.debugLine="clr.setStroke(2dip, \"#494942\")";
_clr.setStroke(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),"#494942");
RDebugUtils.currentLine=131114;
 //BA.debugLineNum = 131114;BA.debugLine="Dim btnRound As xShadowButton";
_btnround = new com.tummosoft.android.views.xShadowButton();
RDebugUtils.currentLine=131115;
 //BA.debugLineNum = 131115;BA.debugLine="btnRound.initialize(\"btnRound\")";
_btnround.initialize(processBA,"btnRound");
RDebugUtils.currentLine=131116;
 //BA.debugLineNum = 131116;BA.debugLine="btnRound.Elevation = 1.6f";
_btnround.setElevation((float) (1.6f));
RDebugUtils.currentLine=131117;
 //BA.debugLineNum = 131117;BA.debugLine="btnRound.Radius = 60dip / 2";
_btnround.setRadius((int) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))/(double)2));
RDebugUtils.currentLine=131118;
 //BA.debugLineNum = 131118;BA.debugLine="btnRound.BackgroundDrawable = clr";
_btnround.setBackgroundDrawable((android.graphics.drawable.Drawable)(_clr.getObject()));
RDebugUtils.currentLine=131120;
 //BA.debugLineNum = 131120;BA.debugLine="Dim logo As Int = tuUtils.getResourceId(\"icon\",\"d";
_logo = _tuutils.getResourceId("icon","drawable");
RDebugUtils.currentLine=131121;
 //BA.debugLineNum = 131121;BA.debugLine="Dim dh As DrawableHelper";
_dh = new com.tummosoft.android.utils.DrawableHelper();
RDebugUtils.currentLine=131122;
 //BA.debugLineNum = 131122;BA.debugLine="dh.initialize(\"dh\")";
_dh.initialize(processBA,"dh");
RDebugUtils.currentLine=131123;
 //BA.debugLineNum = 131123;BA.debugLine="btnRound.Foreground = dh.Bitmap2Drawable(LoadBitm";
_btnround.setForeground(_dh.Bitmap2Drawable((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ho_guom.jpg").getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
RDebugUtils.currentLine=131124;
 //BA.debugLineNum = 131124;BA.debugLine="btnRound.ForegroundGravity = Gravity.CENTER_HORIZ";
_btnround.setForegroundGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
RDebugUtils.currentLine=131126;
 //BA.debugLineNum = 131126;BA.debugLine="Activity.AddView(btnRound, 20%x, 160dip, 60dip, 6";
mostCurrent._activity.AddView((android.view.View)(_btnround.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (160)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=131129;
 //BA.debugLineNum = 131129;BA.debugLine="Dim img3 As xShadowImageView";
_img3 = new com.tummosoft.android.views.xShadowImageView();
RDebugUtils.currentLine=131130;
 //BA.debugLineNum = 131130;BA.debugLine="img3.initialize(\"btn2\")";
_img3.initialize(processBA,"btn2");
RDebugUtils.currentLine=131131;
 //BA.debugLineNum = 131131;BA.debugLine="img3.ImageBitmap = LoadBitmap(File.DirAssets, \"ho";
_img3.setImageBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ho_guom.jpg").getObject()));
RDebugUtils.currentLine=131132;
 //BA.debugLineNum = 131132;BA.debugLine="img3.Elevation = 0.6f";
_img3.setElevation((float) (0.6f));
RDebugUtils.currentLine=131133;
 //BA.debugLineNum = 131133;BA.debugLine="img3.CropToPadding = True";
_img3.setCropToPadding(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131134;
 //BA.debugLineNum = 131134;BA.debugLine="img3.ScaleType = \"CENTER_INSIDE\" '\"MATRIX, CENTER";
_img3.setScaleType("CENTER_INSIDE");
RDebugUtils.currentLine=131136;
 //BA.debugLineNum = 131136;BA.debugLine="Activity.AddView(img3, 20%x, 240dip, 60dip, 60dip";
mostCurrent._activity.AddView((android.view.View)(_img3.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=131138;
 //BA.debugLineNum = 131138;BA.debugLine="Dim img4 As xShadowImageView";
_img4 = new com.tummosoft.android.views.xShadowImageView();
RDebugUtils.currentLine=131139;
 //BA.debugLineNum = 131139;BA.debugLine="img4.initialize(\"btn2\")";
_img4.initialize(processBA,"btn2");
RDebugUtils.currentLine=131141;
 //BA.debugLineNum = 131141;BA.debugLine="img4.ImageURI = \"https://cdn.tuoitre.vn/zoom/456_";
_img4.setImageURI("https://cdn.tuoitre.vn/zoom/456_285/471584752817336320/2024/12/3/nghi-tet2-17319175356721565073028-46-0-1296-2000-crop-17332237459741794590416.jpg");
RDebugUtils.currentLine=131142;
 //BA.debugLineNum = 131142;BA.debugLine="img4.ScaleType = \"MATRIX\" '\"MATRIX, CENTER_CROP,C";
_img4.setScaleType("MATRIX");
RDebugUtils.currentLine=131144;
 //BA.debugLineNum = 131144;BA.debugLine="Activity.AddView(img4, 20%x, 320dip, 60dip, 60dip";
mostCurrent._activity.AddView((android.view.View)(_img4.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (320)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=131148;
 //BA.debugLineNum = 131148;BA.debugLine="xSheetDialog.initialize(\"xSheetDialog\")";
_xsheetdialog.initialize(processBA,"xSheetDialog");
RDebugUtils.currentLine=131149;
 //BA.debugLineNum = 131149;BA.debugLine="Dim img1 As ImageView";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=131150;
 //BA.debugLineNum = 131150;BA.debugLine="img1.Initialize(\"\")";
_img1.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=131151;
 //BA.debugLineNum = 131151;BA.debugLine="img1.Bitmap = LoadBitmap(File.DirAssets, \"ho_guom";
_img1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ho_guom.jpg").getObject()));
RDebugUtils.currentLine=131153;
 //BA.debugLineNum = 131153;BA.debugLine="xSheetDialog.addView(img1)";
_xsheetdialog.addView((android.view.View)(_img1.getObject()));
RDebugUtils.currentLine=131154;
 //BA.debugLineNum = 131154;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="End Sub";
return "";
}
public static String  _btnround_click(Object _view) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnround_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnround_click", new Object[] {_view}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub btnRound_Click(view As Object)";
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="xSheetDialog.Show";
_xsheetdialog.Show();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
}