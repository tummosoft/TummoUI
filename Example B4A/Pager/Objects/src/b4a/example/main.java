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
	public static final boolean includeTitle = false;
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
public com.tummosoft.android.views.xPager _pager = null;
public com.tummosoft.android.views.xPagerFactory _pagerfactory = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _currentpage = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _mcurpage = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _mnextpage = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _mcurpagecanvas = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _mnextpagecanvas = null;
public anywheresoftware.b4a.objects.collections.List _pages = null;
public anywheresoftware.b4a.objects.collections.List _musics = null;
public static int _screenwidth = 0;
public static int _screenheight = 0;
public static int _currentindex = 0;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _lastbitmap = null;
public static int _lastindex = 0;
public static int _selectindex = 0;
public static int _count = 0;
public static int _lastx = 0;
public static int _lasty = 0;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rct = null;
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"Layout\")";
mostCurrent._activity.LoadLayout("Layout",mostCurrent.activityBA);
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="pager.initialize(\"pager\", 100%x, 100%y)";
mostCurrent._pager.initialize(processBA,"pager",anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="pages.Initialize";
mostCurrent._pages.Initialize();
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="pages.Add(\"pic_1.jpg\")";
mostCurrent._pages.Add((Object)("pic_1.jpg"));
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="pages.Add(\"pic_2.jpg\")";
mostCurrent._pages.Add((Object)("pic_2.jpg"));
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="pages.Add(\"pic_3.jpg\")";
mostCurrent._pages.Add((Object)("pic_3.jpg"));
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="pages.Add(\"pic_4.jpg\")";
mostCurrent._pages.Add((Object)("pic_4.jpg"));
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="pages.Add(\"pic_5.jpg\")";
mostCurrent._pages.Add((Object)("pic_5.jpg"));
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="pages.Add(\"pic_6.jpg\")";
mostCurrent._pages.Add((Object)("pic_6.jpg"));
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="currentPage.Initialize(File.DirAssets, \"pic_1.jpg";
mostCurrent._currentpage.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pic_1.jpg");
RDebugUtils.currentLine=131086;
 //BA.debugLineNum = 131086;BA.debugLine="mCurPage.Initialize(File.DirAssets, \"pic_2.jpg\")";
mostCurrent._mcurpage.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pic_2.jpg");
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="mNextPage.Initialize(File.DirAssets, \"pic_3.jpg\")";
mostCurrent._mnextpage.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pic_3.jpg");
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="bmp.InitializeMutable(100%x, 100%y)";
_bmp.InitializeMutable(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="mNextPageCanvas.Initialize2(bmp)";
mostCurrent._mnextpagecanvas.Initialize2((android.graphics.Bitmap)(_bmp.getObject()));
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="Dim rct As Rect";
_rct = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="rct.Initialize(0, 0, 100%x, 100%y)";
_rct.Initialize((int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="mNextPageCanvas.DrawBitmap(LoadBitmap(File.DirAss";
mostCurrent._mnextpagecanvas.DrawBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pic_2.jpg").getObject()),(android.graphics.Rect)(anywheresoftware.b4a.keywords.Common.Null),(android.graphics.Rect)(_rct.getObject()));
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="mCurPageCanvas.Initialize2(bmp)";
mostCurrent._mcurpagecanvas.Initialize2((android.graphics.Bitmap)(_bmp.getObject()));
RDebugUtils.currentLine=131098;
 //BA.debugLineNum = 131098;BA.debugLine="mCurPageCanvas.DrawBitmap(LoadBitmap(File.DirAsse";
mostCurrent._mcurpagecanvas.DrawBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pic_1.jpg").getObject()),(android.graphics.Rect)(anywheresoftware.b4a.keywords.Common.Null),(android.graphics.Rect)(_rct.getObject()));
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="pager.setBitmaps(mCurPage, mNextPage)";
mostCurrent._pager.setBitmaps((android.graphics.Bitmap)(mostCurrent._mcurpage.getObject()),(android.graphics.Bitmap)(mostCurrent._mnextpage.getObject()));
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="pagerFactory.initialize(\"pagerFactory\")";
mostCurrent._pagerfactory.initialize(processBA,"pagerFactory");
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="Activity.AddView(pager, 0, 0,  100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pager.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131106;
 //BA.debugLineNum = 131106;BA.debugLine="End Sub";
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
public static String  _loadimage(anywheresoftware.b4a.objects.drawable.CanvasWrapper _c,int _index) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadimage", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadimage", new Object[] {_c,_index}));}
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub loadImage(c As Canvas, index As Int)";
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="Dim bmp As Bitmap = LoadBitmap(File.DirAss";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),BA.ObjectToString(mostCurrent._pages.Get(_index)));
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="currentPage = bmp";
mostCurrent._currentpage = _bmp;
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="pagerFactory.onDraw2(c, bmp)";
mostCurrent._pagerfactory.onDraw2(_c,(android.graphics.Bitmap)(_bmp.getObject()));
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="pager.setBitmaps(currentPage, mNextPage)";
mostCurrent._pager.setBitmaps((android.graphics.Bitmap)(mostCurrent._currentpage.getObject()),(android.graphics.Bitmap)(mostCurrent._mnextpage.getObject()));
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="pager.postInvalidate";
mostCurrent._pager.postInvalidate();
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="End Sub";
return "";
}
public static boolean  _pager_ontouch(int _action,float _x,float _y,Object _motionevent) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pager_ontouch", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "pager_ontouch", new Object[] {_action,_x,_y,_motionevent}));}
com.tummosoft.android.views.xPager.xMotionEvent _e = null;
boolean _ret = false;
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub pager_onTouch(Action As Int, X As Float, Y As";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="Dim e As xMotionEvent = MotionEvent";
_e = new com.tummosoft.android.views.xPager.xMotionEvent();
_e = (com.tummosoft.android.views.xPager.xMotionEvent) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new com.tummosoft.android.views.xPager.xMotionEvent(), (android.view.MotionEvent)(_motionevent));
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="Dim ret As Boolean = False";
_ret = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="If (e.Action = e.ACTION_DOWN) Then";
if ((_e.getAction()==_e.ACTION_DOWN)) { 
RDebugUtils.currentLine=393220;
 //BA.debugLineNum = 393220;BA.debugLine="pager.calcCornerXY(e.X, e.Y)";
mostCurrent._pager.calcCornerXY(_e.getX(),_e.getY());
RDebugUtils.currentLine=393221;
 //BA.debugLineNum = 393221;BA.debugLine="lastBitmap = currentPage";
mostCurrent._lastbitmap = mostCurrent._currentpage;
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="lastIndex = currentIndex";
_lastindex = _currentindex;
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="pagerFactory.onDraw2(mCurPageCanvas, currentPage";
mostCurrent._pagerfactory.onDraw2(mostCurrent._mcurpagecanvas,(android.graphics.Bitmap)(mostCurrent._currentpage.getObject()));
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="If (pager.DragToRight) Then";
if ((mostCurrent._pager.DragToRight())) { 
RDebugUtils.currentLine=393225;
 //BA.debugLineNum = 393225;BA.debugLine="If (currentIndex = 0) Then";
if ((_currentindex==0)) { 
RDebugUtils.currentLine=393226;
 //BA.debugLineNum = 393226;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
RDebugUtils.currentLine=393228;
 //BA.debugLineNum = 393228;BA.debugLine="pager.abortAnimation()";
mostCurrent._pager.abortAnimation();
RDebugUtils.currentLine=393229;
 //BA.debugLineNum = 393229;BA.debugLine="currentIndex = currentIndex - 1";
_currentindex = (int) (_currentindex-1);
RDebugUtils.currentLine=393230;
 //BA.debugLineNum = 393230;BA.debugLine="Log(currentIndex)";
anywheresoftware.b4a.keywords.Common.LogImpl("3393230",BA.NumberToString(_currentindex),0);
 }else {
RDebugUtils.currentLine=393232;
 //BA.debugLineNum = 393232;BA.debugLine="If (currentIndex + 1) = count Then";
if ((_currentindex+1)==_count) { 
RDebugUtils.currentLine=393233;
 //BA.debugLineNum = 393233;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
RDebugUtils.currentLine=393235;
 //BA.debugLineNum = 393235;BA.debugLine="pager.abortAnimation()";
mostCurrent._pager.abortAnimation();
RDebugUtils.currentLine=393236;
 //BA.debugLineNum = 393236;BA.debugLine="currentIndex = currentIndex + 1";
_currentindex = (int) (_currentindex+1);
RDebugUtils.currentLine=393237;
 //BA.debugLineNum = 393237;BA.debugLine="loadImage(mNextPageCanvas, currentIndex)";
_loadimage(mostCurrent._mnextpagecanvas,_currentindex);
 };
 }else 
{RDebugUtils.currentLine=393239;
 //BA.debugLineNum = 393239;BA.debugLine="else If (e.Action = e.ACTION_UP) Then";
if ((_e.getAction()==_e.ACTION_UP)) { 
RDebugUtils.currentLine=393240;
 //BA.debugLineNum = 393240;BA.debugLine="If (pager.canDragOver = False) Then";
if ((mostCurrent._pager.canDragOver()==anywheresoftware.b4a.keywords.Common.False)) { 
RDebugUtils.currentLine=393241;
 //BA.debugLineNum = 393241;BA.debugLine="currentIndex = lastIndex";
_currentindex = _lastindex;
RDebugUtils.currentLine=393242;
 //BA.debugLineNum = 393242;BA.debugLine="currentPage = lastBitmap";
mostCurrent._currentpage = mostCurrent._lastbitmap;
 };
 }}
;
RDebugUtils.currentLine=393246;
 //BA.debugLineNum = 393246;BA.debugLine="ret = pager.doTouchEvent(e)";
_ret = mostCurrent._pager.doTouchEvent((android.view.MotionEvent)(_e.getObject()));
RDebugUtils.currentLine=393247;
 //BA.debugLineNum = 393247;BA.debugLine="Return ret";
if (true) return _ret;
RDebugUtils.currentLine=393249;
 //BA.debugLineNum = 393249;BA.debugLine="End Sub";
return false;
}
}