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
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
int _w = 0;
int _left = 0;
com.tummosoft.android.utils.ResourcesHelper _tuutils = null;
com.tummosoft.android.views.xNavigationBar _bottombar = null;
int _iconhome = 0;
int _iconhistory = 0;
int _iconfave = 0;
int _iconbook = 0;
int _iconbg = 0;
com.tummosoft.android.utils.DrawableHelper _draw = null;
com.tummosoft.android.utils.GradientHelper _clr2 = null;
String[] _cl2 = null;
int _i = 0;
com.tummosoft.android.views.xButtonImage _buttonbar = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlwrapper = null;
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"xConstraintLayout\")";
mostCurrent._activity.LoadLayout("xConstraintLayout",mostCurrent.activityBA);
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="Dim w As Int = 100%x / 4";
_w = (int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)/(double)4);
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="Dim left As Int = (w - 15dip) / 2";
_left = (int) ((_w-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)))/(double)2);
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="Dim tuUtils As ResourcesHelper";
_tuutils = new com.tummosoft.android.utils.ResourcesHelper();
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="tuUtils.initialize";
_tuutils.initialize(processBA);
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="Dim bottombar As xNavigationBar";
_bottombar = new com.tummosoft.android.views.xNavigationBar();
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="bottombar.initialize(\"bottombar\")";
_bottombar.initialize(processBA,"bottombar");
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="Dim iconhome As Int = tuUtils.getResourceId(\"home";
_iconhome = _tuutils.getResourceId("home","drawable");
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="Dim iconhistory As Int = tuUtils.getResourceId(\"c";
_iconhistory = _tuutils.getResourceId("clock","drawable");
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="Dim iconfave As Int = tuUtils.getResourceId(\"fave";
_iconfave = _tuutils.getResourceId("fave","drawable");
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="Dim iconbook As Int = tuUtils.getResourceId(\"book";
_iconbook = _tuutils.getResourceId("book","drawable");
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="Dim iconbg As Int = tuUtils.getResourceId(\"bg\",\"d";
_iconbg = _tuutils.getResourceId("bg","drawable");
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="Dim draw As DrawableHelper";
_draw = new com.tummosoft.android.utils.DrawableHelper();
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="draw.initialize(\"draw\")";
_draw.initialize(processBA,"draw");
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="Dim clr2 As GradientHelper";
_clr2 = new com.tummosoft.android.utils.GradientHelper();
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="clr2.initialize(clr2.Orientation_BOTTOM_TOP)";
_clr2.initialize(processBA,_clr2.Orientation_BOTTOM_TOP);
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="clr2.setGradientCenter(5,5)";
_clr2.setGradientCenter((int) (5),(int) (5));
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="clr2.Orientation = clr2.Orientation_TL_BR";
_clr2.setOrientation(_clr2.Orientation_TL_BR);
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="Dim cl2() As String  = Array As String(\"#FAC54B\",";
_cl2 = new String[]{"#FAC54B","#FED26C","#C85637"};
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="clr2.Colors = cl2";
_clr2.setColors(_cl2);
RDebugUtils.currentLine=131098;
 //BA.debugLineNum = 131098;BA.debugLine="bottombar.BackgroundDrawable = clr2";
_bottombar.setBackgroundDrawable((android.graphics.drawable.Drawable)(_clr2.getObject()));
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="For i=0 To 3";
{
final int step22 = 1;
final int limit22 = (int) (3);
_i = (int) (0) ;
for (;_i <= limit22 ;_i = _i + step22 ) {
RDebugUtils.currentLine=131101;
 //BA.debugLineNum = 131101;BA.debugLine="Dim buttonBar As xButtonImage";
_buttonbar = new com.tummosoft.android.views.xButtonImage();
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="buttonBar.initialize(\"buttonBar\")";
_buttonbar.initialize(processBA,"buttonBar");
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="buttonBar.TagName = \"Item \" & i";
_buttonbar.setTagName("Item "+BA.NumberToString(_i));
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="If (i = 0) Then";
if ((_i==0)) { 
RDebugUtils.currentLine=131105;
 //BA.debugLineNum = 131105;BA.debugLine="buttonBar.ImageResourceID = iconhome";
_buttonbar.setImageResourceID(_iconhome);
 }else 
{RDebugUtils.currentLine=131106;
 //BA.debugLineNum = 131106;BA.debugLine="else if (i = 1) Then";
if ((_i==1)) { 
RDebugUtils.currentLine=131107;
 //BA.debugLineNum = 131107;BA.debugLine="buttonBar.BackgroundDrawable = draw.getDrawable";
_buttonbar.setBackgroundDrawable(_draw.getDrawable(_iconhistory));
 }else 
{RDebugUtils.currentLine=131108;
 //BA.debugLineNum = 131108;BA.debugLine="else if (i = 2) Then";
if ((_i==2)) { 
RDebugUtils.currentLine=131109;
 //BA.debugLineNum = 131109;BA.debugLine="buttonBar.BackgroundDrawable = draw.getDrawable";
_buttonbar.setBackgroundDrawable(_draw.getDrawable(_iconfave));
 }else 
{RDebugUtils.currentLine=131110;
 //BA.debugLineNum = 131110;BA.debugLine="else if (i = 3) Then";
if ((_i==3)) { 
RDebugUtils.currentLine=131111;
 //BA.debugLineNum = 131111;BA.debugLine="buttonBar.BackgroundDrawable = draw.getDrawable";
_buttonbar.setBackgroundDrawable(_draw.getDrawable(_iconbook));
 }}}}
;
RDebugUtils.currentLine=131114;
 //BA.debugLineNum = 131114;BA.debugLine="Dim pnlWrapper As Panel";
_pnlwrapper = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=131115;
 //BA.debugLineNum = 131115;BA.debugLine="pnlWrapper.Initialize(\"pnlWrapper\")";
_pnlwrapper.Initialize(mostCurrent.activityBA,"pnlWrapper");
RDebugUtils.currentLine=131116;
 //BA.debugLineNum = 131116;BA.debugLine="pnlWrapper.AddView(buttonBar, left,5dip,15dip, 1";
_pnlwrapper.AddView((android.view.View)(_buttonbar.getObject()),_left,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)));
RDebugUtils.currentLine=131117;
 //BA.debugLineNum = 131117;BA.debugLine="pnlWrapper.Color = Colors.Transparent";
_pnlwrapper.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=131119;
 //BA.debugLineNum = 131119;BA.debugLine="bottombar.AddView(pnlWrapper)";
_bottombar.AddView((android.view.View)(_pnlwrapper.getObject()));
 }
};
RDebugUtils.currentLine=131123;
 //BA.debugLineNum = 131123;BA.debugLine="bottombar.setPadding(0, 10dip, 0dip, 10dip)";
_bottombar.setPadding((int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
RDebugUtils.currentLine=131125;
 //BA.debugLineNum = 131125;BA.debugLine="bottombar.Build()";
_bottombar.Build();
RDebugUtils.currentLine=131127;
 //BA.debugLineNum = 131127;BA.debugLine="Activity.AddView(bottombar,0,100%y-35dip, 100%x,";
mostCurrent._activity.AddView((android.view.View)(_bottombar.getObject()),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
RDebugUtils.currentLine=131128;
 //BA.debugLineNum = 131128;BA.debugLine="End Sub";
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
public static String  _buttonbar_click(Object _view) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "buttonbar_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "buttonbar_click", new Object[] {_view}));}
com.tummosoft.android.views.xButtonImage _v = null;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub buttonBar_click(view As Object)";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="Dim v As xButtonImage = view";
_v = new com.tummosoft.android.views.xButtonImage();
_v = (com.tummosoft.android.views.xButtonImage) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new com.tummosoft.android.views.xButtonImage(), (android.widget.ImageButton)(_view));
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="xui.MsgboxAsync(\"\" & v.TagName, \"B4X\")";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence(""+_v.getTagName()),BA.ObjectToCharSequence("B4X"));
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="End Sub";
return "";
}
}