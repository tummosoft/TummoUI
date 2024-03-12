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
public com.tummosoft.layout.xLinearLayout _layout = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button5 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public com.tummosoft.animation.xAnimation _anim = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button7 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button8 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button9 = null;
public static float _radius = 0f;
public static float _centerx = 0f;
public static float _centery = 0f;
public com.tummosoft.control.image.xImageView _ximageview1 = null;
public anywheresoftware.b4a.objects.SocketWrapper _socket = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"layout2\")";
mostCurrent._activity.LoadLayout("layout2",mostCurrent.activityBA);
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="anim.initialize(\"anim\")";
mostCurrent._anim.initialize(mostCurrent.activityBA,"anim");
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="xImageView1.initialize2(\"xImageView1\")";
mostCurrent._ximageview1.initialize2(mostCurrent.activityBA,"xImageView1");
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="Activity.AddView(xImageView1, 0,0,200dip,200dip)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._ximageview1.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)));
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="xImageView1.HasCircle = True";
mostCurrent._ximageview1.setHasCircle(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="xImageView1.Position = \"CENTER\" 'FULL CENTER_CROP";
mostCurrent._ximageview1.setPosition("CENTER");
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="xImageView1.BitmapError = LoadBitmap(File.DirAsse";
mostCurrent._ximageview1.setBitmapError((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sign-red-error-icon-1.png").getObject()));
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="xImageView1.getImageFromURL(\"https://cdn.tuoitre.";
mostCurrent._ximageview1.getImageFromURL("https://cdn.tuoitre.vn/zoom/456_285/471584752817336320/2024/3/7/thu-tuong-le-don-3-17097703635511079333768-306-703-1116-2000-crop-1709770847142527180620.jpeg");
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="End Sub";
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
public static String  _anim_onanimationupdate(float _angle) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "anim_onanimationupdate", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "anim_onanimationupdate", new Object[] {_angle}));}
float _x = 0f;
float _y = 0f;
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub anim_onAnimationUpdate(angle As Float)";
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="Dim x As Float = centerX + radius * Cos(angle)";
_x = (float) (_centerx+_radius*anywheresoftware.b4a.keywords.Common.Cos(_angle));
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="Dim y As Float = centerY + radius * Sin(angle)";
_y = (float) (_centery+_radius*anywheresoftware.b4a.keywords.Common.Sin(_angle));
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="Label1.Left = x - Label1.Width / 2.0";
mostCurrent._label1.setLeft((int) (_x-mostCurrent._label1.getWidth()/(double)2.0));
RDebugUtils.currentLine=655366;
 //BA.debugLineNum = 655366;BA.debugLine="Label1.top = y - Label1.Height / 2.0";
mostCurrent._label1.setTop((int) (_y-mostCurrent._label1.getHeight()/(double)2.0));
RDebugUtils.currentLine=655368;
 //BA.debugLineNum = 655368;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button1_click", null));}
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub Button1_Click";
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="End Sub";
return "";
}
public static String  _button2_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button2_click", null));}
float _target = 0f;
float _theend = 0f;
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Private Sub Button2_Click";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="radius = 100";
_radius = (float) (100);
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="centerX = (100%x + Label1.Width) / 2.0";
_centerx = (float) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)+mostCurrent._label1.getWidth())/(double)2.0);
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="centerY = (100%y + Label1.Height) / 2.0";
_centery = (float) ((anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)+mostCurrent._label1.getHeight())/(double)2.0);
RDebugUtils.currentLine=589829;
 //BA.debugLineNum = 589829;BA.debugLine="Dim target As Float = 0";
_target = (float) (0);
RDebugUtils.currentLine=589830;
 //BA.debugLineNum = 589830;BA.debugLine="Dim theend As Float = 360";
_theend = (float) (360);
RDebugUtils.currentLine=589831;
 //BA.debugLineNum = 589831;BA.debugLine="anim.startAccelerateDecelerateInterpolator(Label1";
mostCurrent._anim.startAccelerateDecelerateInterpolator((android.view.View)(mostCurrent._label1.getObject()),(int) (2000),"rotation",_target,_theend);
RDebugUtils.currentLine=589832;
 //BA.debugLineNum = 589832;BA.debugLine="End Sub";
return "";
}
public static String  _button3_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button3_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button3_click", null));}
float _target = 0f;
float _theend = 0f;
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Private Sub Button3_Click";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Dim target As Float = 200";
_target = (float) (200);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="Dim theend As Float = 0";
_theend = (float) (0);
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="anim.startAccelerateInterpolator(Label1, 2000, \"t";
mostCurrent._anim.startAccelerateInterpolator((android.view.View)(mostCurrent._label1.getObject()),(int) (2000),"translationX",_target,_theend);
RDebugUtils.currentLine=720900;
 //BA.debugLineNum = 720900;BA.debugLine="End Sub";
return "";
}
public static String  _button4_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button4_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button4_click", null));}
float _target = 0f;
float _theend = 0f;
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Private Sub Button4_Click";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="Dim target As Float = 200";
_target = (float) (200);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="Dim theend As Float = 0";
_theend = (float) (0);
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="anim.startAnticipateInterpolator(Label1, 2000, \"t";
mostCurrent._anim.startAnticipateInterpolator((android.view.View)(mostCurrent._label1.getObject()),(int) (2000),"translationX",_target,_theend);
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="End Sub";
return "";
}
public static String  _button5_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button5_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button5_click", null));}
float _target = 0f;
float _theend = 0f;
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Private Sub Button5_Click";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="Dim target As Float = 200";
_target = (float) (200);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="Dim theend As Float = 0";
_theend = (float) (0);
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="anim.startBounceInterpolator(Label1, 2000, \"trans";
mostCurrent._anim.startBounceInterpolator((android.view.View)(mostCurrent._label1.getObject()),(int) (2000),"translationX",_target,_theend);
RDebugUtils.currentLine=851972;
 //BA.debugLineNum = 851972;BA.debugLine="End Sub";
return "";
}
public static String  _button6_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button6_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button6_click", null));}
float _target = 0f;
float _theend = 0f;
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Private Sub Button6_Click";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="Dim target As Float = 200";
_target = (float) (200);
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="Dim theend As Float = 0";
_theend = (float) (0);
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="anim.startCycleInterpolator(Label1, 2000, \"transl";
mostCurrent._anim.startCycleInterpolator((android.view.View)(mostCurrent._label1.getObject()),(int) (2000),"translationX",_target,_theend);
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="End Sub";
return "";
}
public static String  _button7_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button7_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button7_click", null));}
float _target = 0f;
float _theend = 0f;
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Private Sub Button7_Click";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="Dim target As Float = 200";
_target = (float) (200);
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="Dim theend As Float = 0";
_theend = (float) (0);
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="anim.startDecelerateInterpolator(Label1, 2000, \"t";
mostCurrent._anim.startDecelerateInterpolator((android.view.View)(mostCurrent._label1.getObject()),(int) (2000),"translationX",_target,_theend);
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="End Sub";
return "";
}
public static String  _button8_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button8_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button8_click", null));}
float _target = 0f;
float _theend = 0f;
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Private Sub Button8_Click";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Dim target As Float = 200";
_target = (float) (200);
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="Dim theend As Float = 0";
_theend = (float) (0);
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="anim.startLinearInterpolator(Label1, 2000, \"trans";
mostCurrent._anim.startLinearInterpolator((android.view.View)(mostCurrent._label1.getObject()),(int) (2000),"translationX",_target,_theend);
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="End Sub";
return "";
}
public static String  _button9_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "button9_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "button9_click", null));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Private Sub Button9_Click";
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="End Sub";
return "";
}
public static String  _test1() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "test1", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "test1", null));}
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.LabelWrapper _lb2 = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub test1()";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="Dim layout As xLinearLayout";
mostCurrent._layout = new com.tummosoft.layout.xLinearLayout();
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="layout.initialize(\"layout\", layout.ROW_VERTICAL,";
mostCurrent._layout.initialize(processBA,"layout",mostCurrent._layout.ROW_VERTICAL,mostCurrent._layout.COL_CENTER_VERTICAL);
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="Activity.AddView(layout, 0,0,100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._layout.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=458757;
 //BA.debugLineNum = 458757;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="lbl.Text = \"Hello, wellcome to Vietnam\"";
_lbl.setText(BA.ObjectToCharSequence("Hello, wellcome to Vietnam"));
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="lbl.Color = Colors.Red";
_lbl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=458761;
 //BA.debugLineNum = 458761;BA.debugLine="lbl.Gravity = Gravity.CENTER";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=458762;
 //BA.debugLineNum = 458762;BA.debugLine="layout.AddView(lbl,0, 20dip, 0, 0, 50%x, 50dip)";
mostCurrent._layout.AddView((android.view.View)(_lbl.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="Dim lb2 As Label";
_lb2 = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=458765;
 //BA.debugLineNum = 458765;BA.debugLine="lb2.Initialize(\"\")";
_lb2.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=458766;
 //BA.debugLineNum = 458766;BA.debugLine="lb2.Text = \"Xin chao cac ban\"";
_lb2.setText(BA.ObjectToCharSequence("Xin chao cac ban"));
RDebugUtils.currentLine=458767;
 //BA.debugLineNum = 458767;BA.debugLine="lb2.Color = Colors.Green";
_lb2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=458768;
 //BA.debugLineNum = 458768;BA.debugLine="lb2.Gravity = Gravity.CENTER";
_lb2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=458769;
 //BA.debugLineNum = 458769;BA.debugLine="layout.AddView(lb2,0, 0, 0, 0, 100%x, 50dip)";
mostCurrent._layout.AddView((android.view.View)(_lb2.getObject()),(int) (0),(int) (0),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=458771;
 //BA.debugLineNum = 458771;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=458772;
 //BA.debugLineNum = 458772;BA.debugLine="img.Initialize(\"\")";
_img.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=458773;
 //BA.debugLineNum = 458773;BA.debugLine="img.Bitmap = LoadBitmap(File.DirAssets, \"img2.jpg";
_img.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"img2.jpg").getObject()));
RDebugUtils.currentLine=458774;
 //BA.debugLineNum = 458774;BA.debugLine="img.Gravity = Gravity.FILL";
_img.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=458775;
 //BA.debugLineNum = 458775;BA.debugLine="layout.AddView(img,0, 0, 0, 0, 100dip, 100dip)";
mostCurrent._layout.AddView((android.view.View)(_img.getObject()),(int) (0),(int) (0),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=458776;
 //BA.debugLineNum = 458776;BA.debugLine="End Sub";
return "";
}
public static String  _test2() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "test2", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "test2", null));}
com.tummosoft.animation.line.xLineView _line = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.ListViewWrapper _lst = null;
int _i = 0;
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub test2()";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Activity.LoadLayout(\"layout3\")";
mostCurrent._activity.LoadLayout("layout3",mostCurrent.activityBA);
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="layout.initialize(\"layout\", layout.ROW_HORIZONTAL";
mostCurrent._layout.initialize(processBA,"layout",mostCurrent._layout.ROW_HORIZONTAL,mostCurrent._layout.COL_CENTER_VERTICAL);
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="layout.Color = Colors.Magenta";
mostCurrent._layout.setColor(anywheresoftware.b4a.keywords.Common.Colors.Magenta);
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="Dim line As xLineView";
_line = new com.tummosoft.animation.line.xLineView();
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="line.initialize(5dip)";
_line.initialize(processBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=393227;
 //BA.debugLineNum = 393227;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=393228;
 //BA.debugLineNum = 393228;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=393229;
 //BA.debugLineNum = 393229;BA.debugLine="layout.AddView(lbl, 0, 0, 0, 0, 100%x, 100%y)";
mostCurrent._layout.AddView((android.view.View)(_lbl.getObject()),(int) (0),(int) (0),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=393230;
 //BA.debugLineNum = 393230;BA.debugLine="lbl.Text = \"BOTTOM MENU\"";
_lbl.setText(BA.ObjectToCharSequence("BOTTOM MENU"));
RDebugUtils.currentLine=393231;
 //BA.debugLineNum = 393231;BA.debugLine="lbl.Color = Colors.Red";
_lbl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=393232;
 //BA.debugLineNum = 393232;BA.debugLine="lbl.Gravity = Gravity.CENTER";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
RDebugUtils.currentLine=393235;
 //BA.debugLineNum = 393235;BA.debugLine="Dim lst As ListView";
_lst = new anywheresoftware.b4a.objects.ListViewWrapper();
RDebugUtils.currentLine=393236;
 //BA.debugLineNum = 393236;BA.debugLine="lst.Initialize(\"\")";
_lst.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=393237;
 //BA.debugLineNum = 393237;BA.debugLine="layout.AddView(lst, 0, 5dip, 0, 0, 100%x, 100%y)";
mostCurrent._layout.AddView((android.view.View)(_lst.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=393238;
 //BA.debugLineNum = 393238;BA.debugLine="lst.Color = Colors.DarkGray";
_lst.setColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
RDebugUtils.currentLine=393239;
 //BA.debugLineNum = 393239;BA.debugLine="For i=0 To 10";
{
final int step16 = 1;
final int limit16 = (int) (10);
_i = (int) (0) ;
for (;_i <= limit16 ;_i = _i + step16 ) {
RDebugUtils.currentLine=393240;
 //BA.debugLineNum = 393240;BA.debugLine="lst.AddTwoLines(\"This is LinearLayout with anima";
_lst.AddTwoLines(BA.ObjectToCharSequence("This is LinearLayout with animation"),BA.ObjectToCharSequence("item loadding..."));
 }
};
RDebugUtils.currentLine=393245;
 //BA.debugLineNum = 393245;BA.debugLine="Activity.AddView(layout, 0,0,100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._layout.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=393246;
 //BA.debugLineNum = 393246;BA.debugLine="End Sub";
return "";
}
public static String  _ximageview1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "ximageview1_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "ximageview1_click", null));}
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub xImageView1_Click";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Log(\"Image has clicked\")";
anywheresoftware.b4a.keywords.Common.LogImpl("6524289","Image has clicked",0);
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="End Sub";
return "";
}
}