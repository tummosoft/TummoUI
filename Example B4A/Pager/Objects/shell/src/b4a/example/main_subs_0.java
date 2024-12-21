package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,40);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _bmp = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
RemoteObject _rct = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 40;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(128);
 BA.debugLineNum = 41;BA.debugLine="Activity.LoadLayout(\"Layout\")";
Debug.ShouldStop(256);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 43;BA.debugLine="pager.initialize(\"pager\", 100%x, 100%y)";
Debug.ShouldStop(1024);
main.mostCurrent._pager.runVoidMethod ("initialize",main.processBA,(Object)(BA.ObjectToString("pager")),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 45;BA.debugLine="pages.Initialize";
Debug.ShouldStop(4096);
main.mostCurrent._pages.runVoidMethod ("Initialize");
 BA.debugLineNum = 46;BA.debugLine="pages.Add(\"pic_1.jpg\")";
Debug.ShouldStop(8192);
main.mostCurrent._pages.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("pic_1.jpg"))));
 BA.debugLineNum = 47;BA.debugLine="pages.Add(\"pic_2.jpg\")";
Debug.ShouldStop(16384);
main.mostCurrent._pages.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("pic_2.jpg"))));
 BA.debugLineNum = 48;BA.debugLine="pages.Add(\"pic_3.jpg\")";
Debug.ShouldStop(32768);
main.mostCurrent._pages.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("pic_3.jpg"))));
 BA.debugLineNum = 49;BA.debugLine="pages.Add(\"pic_4.jpg\")";
Debug.ShouldStop(65536);
main.mostCurrent._pages.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("pic_4.jpg"))));
 BA.debugLineNum = 50;BA.debugLine="pages.Add(\"pic_5.jpg\")";
Debug.ShouldStop(131072);
main.mostCurrent._pages.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("pic_5.jpg"))));
 BA.debugLineNum = 51;BA.debugLine="pages.Add(\"pic_6.jpg\")";
Debug.ShouldStop(262144);
main.mostCurrent._pages.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("pic_6.jpg"))));
 BA.debugLineNum = 53;BA.debugLine="currentPage.Initialize(File.DirAssets, \"pic_1.jpg";
Debug.ShouldStop(1048576);
main.mostCurrent._currentpage.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("pic_1.jpg")));
 BA.debugLineNum = 54;BA.debugLine="mCurPage.Initialize(File.DirAssets, \"pic_2.jpg\")";
Debug.ShouldStop(2097152);
main.mostCurrent._mcurpage.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("pic_2.jpg")));
 BA.debugLineNum = 55;BA.debugLine="mNextPage.Initialize(File.DirAssets, \"pic_3.jpg\")";
Debug.ShouldStop(4194304);
main.mostCurrent._mnextpage.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("pic_3.jpg")));
 BA.debugLineNum = 57;BA.debugLine="Dim bmp As Bitmap";
Debug.ShouldStop(16777216);
_bmp = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");Debug.locals.put("bmp", _bmp);
 BA.debugLineNum = 58;BA.debugLine="bmp.InitializeMutable(100%x, 100%y)";
Debug.ShouldStop(33554432);
_bmp.runVoidMethod ("InitializeMutable",(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 60;BA.debugLine="mNextPageCanvas.Initialize2(bmp)";
Debug.ShouldStop(134217728);
main.mostCurrent._mnextpagecanvas.runVoidMethod ("Initialize2",(Object)((_bmp.getObject())));
 BA.debugLineNum = 61;BA.debugLine="Dim rct As Rect";
Debug.ShouldStop(268435456);
_rct = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");Debug.locals.put("rct", _rct);
 BA.debugLineNum = 62;BA.debugLine="rct.Initialize(0, 0, 100%x, 100%y)";
Debug.ShouldStop(536870912);
_rct.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 63;BA.debugLine="mNextPageCanvas.DrawBitmap(LoadBitmap(File.DirAss";
Debug.ShouldStop(1073741824);
main.mostCurrent._mnextpagecanvas.runVoidMethod ("DrawBitmap",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("pic_2.jpg"))).getObject())),(Object)((main.mostCurrent.__c.getField(false,"Null"))),(Object)((_rct.getObject())));
 BA.debugLineNum = 65;BA.debugLine="mCurPageCanvas.Initialize2(bmp)";
Debug.ShouldStop(1);
main.mostCurrent._mcurpagecanvas.runVoidMethod ("Initialize2",(Object)((_bmp.getObject())));
 BA.debugLineNum = 66;BA.debugLine="mCurPageCanvas.DrawBitmap(LoadBitmap(File.DirAsse";
Debug.ShouldStop(2);
main.mostCurrent._mcurpagecanvas.runVoidMethod ("DrawBitmap",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("pic_1.jpg"))).getObject())),(Object)((main.mostCurrent.__c.getField(false,"Null"))),(Object)((_rct.getObject())));
 BA.debugLineNum = 68;BA.debugLine="pager.setBitmaps(mCurPage, mNextPage)";
Debug.ShouldStop(8);
main.mostCurrent._pager.runVoidMethod ("setBitmaps",(Object)((main.mostCurrent._mcurpage.getObject())),(Object)((main.mostCurrent._mnextpage.getObject())));
 BA.debugLineNum = 70;BA.debugLine="pagerFactory.initialize(\"pagerFactory\")";
Debug.ShouldStop(32);
main.mostCurrent._pagerfactory.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("pagerFactory")));
 BA.debugLineNum = 72;BA.debugLine="Activity.AddView(pager, 0, 0,  100%x, 100%y)";
Debug.ShouldStop(128);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._pager.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 74;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,80);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 80;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 82;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,76);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 76;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(2048);
 BA.debugLineNum = 78;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Dim pager As xPager";
main.mostCurrent._pager = RemoteObject.createNew ("com.tummosoft.android.views.xPager");
 //BA.debugLineNum = 23;BA.debugLine="Private pagerFactory As xPagerFactory";
main.mostCurrent._pagerfactory = RemoteObject.createNew ("com.tummosoft.android.views.xPagerFactory");
 //BA.debugLineNum = 24;BA.debugLine="Private currentPage, mCurPage, mNextPage As Bitma";
main.mostCurrent._currentpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
main.mostCurrent._mcurpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
main.mostCurrent._mnextpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private mCurPageCanvas, mNextPageCanvas As Canvas";
main.mostCurrent._mcurpagecanvas = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper");
main.mostCurrent._mnextpagecanvas = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private pages As List";
main.mostCurrent._pages = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 27;BA.debugLine="Private musics As List";
main.mostCurrent._musics = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 28;BA.debugLine="Private screenWidth As Int";
main._screenwidth = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 29;BA.debugLine="Private screenHeight As Int";
main._screenheight = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 30;BA.debugLine="Private currentIndex As Int  = 0";
main._currentindex = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 31;BA.debugLine="Dim lastBitmap As Bitmap = Null";
main.mostCurrent._lastbitmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
main.mostCurrent._lastbitmap = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), main.mostCurrent.__c.getField(false,"Null"));
 //BA.debugLineNum = 32;BA.debugLine="Private lastIndex As Int = 0";
main._lastindex = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 33;BA.debugLine="Private selectIndex As Int = 0";
main._selectindex = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 34;BA.debugLine="Dim count As Int = 5";
main._count = BA.numberCast(int.class, 5);
 //BA.debugLineNum = 36;BA.debugLine="Dim lastX As Int";
main._lastx = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 37;BA.debugLine="Dim lastY As Int";
main._lasty = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _loadimage(RemoteObject _c,RemoteObject _index) throws Exception{
try {
		Debug.PushSubsStack("loadImage (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,84);
if (RapidSub.canDelegate("loadimage")) { return b4a.example.main.remoteMe.runUserSub(false, "main","loadimage", _c, _index);}
RemoteObject _bmp = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
Debug.locals.put("c", _c);
Debug.locals.put("index", _index);
 BA.debugLineNum = 84;BA.debugLine="Sub loadImage(c As Canvas, index As Int)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 86;BA.debugLine="Dim bmp As Bitmap = LoadBitmap(File.DirAss";
Debug.ShouldStop(2097152);
_bmp = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
_bmp = main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString(main.mostCurrent._pages.runMethod(false,"Get",(Object)(_index)))));Debug.locals.put("bmp", _bmp);Debug.locals.put("bmp", _bmp);
 BA.debugLineNum = 87;BA.debugLine="currentPage = bmp";
Debug.ShouldStop(4194304);
main.mostCurrent._currentpage = _bmp;
 BA.debugLineNum = 88;BA.debugLine="pagerFactory.onDraw2(c, bmp)";
Debug.ShouldStop(8388608);
main.mostCurrent._pagerfactory.runVoidMethod ("onDraw2",(Object)(_c),(Object)((_bmp.getObject())));
 BA.debugLineNum = 89;BA.debugLine="pager.setBitmaps(currentPage, mNextPage)";
Debug.ShouldStop(16777216);
main.mostCurrent._pager.runVoidMethod ("setBitmaps",(Object)((main.mostCurrent._currentpage.getObject())),(Object)((main.mostCurrent._mnextpage.getObject())));
 BA.debugLineNum = 90;BA.debugLine="pager.postInvalidate";
Debug.ShouldStop(33554432);
main.mostCurrent._pager.runVoidMethod ("postInvalidate");
 BA.debugLineNum = 92;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pager_ontouch(RemoteObject _action,RemoteObject _x,RemoteObject _y,RemoteObject _motionevent) throws Exception{
try {
		Debug.PushSubsStack("pager_onTouch (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,95);
if (RapidSub.canDelegate("pager_ontouch")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pager_ontouch", _action, _x, _y, _motionevent);}
RemoteObject _e = RemoteObject.declareNull("com.tummosoft.android.views.xPager.xMotionEvent");
RemoteObject _ret = RemoteObject.createImmutable(false);
Debug.locals.put("Action", _action);
Debug.locals.put("X", _x);
Debug.locals.put("Y", _y);
Debug.locals.put("MotionEvent", _motionevent);
 BA.debugLineNum = 95;BA.debugLine="Sub pager_onTouch(Action As Int, X As Float, Y As";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="Dim e As xMotionEvent = MotionEvent";
Debug.ShouldStop(-2147483648);
_e = RemoteObject.createNew ("com.tummosoft.android.views.xPager.xMotionEvent");
_e = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("com.tummosoft.android.views.xPager.xMotionEvent"), _motionevent);Debug.locals.put("e", _e);Debug.locals.put("e", _e);
 BA.debugLineNum = 97;BA.debugLine="Dim ret As Boolean = False";
Debug.ShouldStop(1);
_ret = main.mostCurrent.__c.getField(true,"False");Debug.locals.put("ret", _ret);Debug.locals.put("ret", _ret);
 BA.debugLineNum = 98;BA.debugLine="If (e.Action = e.ACTION_DOWN) Then";
Debug.ShouldStop(2);
if ((RemoteObject.solveBoolean("=",_e.runMethod(true,"getAction"),BA.numberCast(double.class, _e.getField(true,"ACTION_DOWN"))))) { 
 BA.debugLineNum = 99;BA.debugLine="pager.calcCornerXY(e.X, e.Y)";
Debug.ShouldStop(4);
main.mostCurrent._pager.runVoidMethod ("calcCornerXY",(Object)(_e.runMethod(true,"getX")),(Object)(_e.runMethod(true,"getY")));
 BA.debugLineNum = 100;BA.debugLine="lastBitmap = currentPage";
Debug.ShouldStop(8);
main.mostCurrent._lastbitmap = main.mostCurrent._currentpage;
 BA.debugLineNum = 101;BA.debugLine="lastIndex = currentIndex";
Debug.ShouldStop(16);
main._lastindex = main._currentindex;
 BA.debugLineNum = 102;BA.debugLine="pagerFactory.onDraw2(mCurPageCanvas, currentPage";
Debug.ShouldStop(32);
main.mostCurrent._pagerfactory.runVoidMethod ("onDraw2",(Object)(main.mostCurrent._mcurpagecanvas),(Object)((main.mostCurrent._currentpage.getObject())));
 BA.debugLineNum = 103;BA.debugLine="If (pager.DragToRight) Then";
Debug.ShouldStop(64);
if ((main.mostCurrent._pager.runMethod(true,"DragToRight")).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 104;BA.debugLine="If (currentIndex = 0) Then";
Debug.ShouldStop(128);
if ((RemoteObject.solveBoolean("=",main._currentindex,BA.numberCast(double.class, 0)))) { 
 BA.debugLineNum = 105;BA.debugLine="Return False";
Debug.ShouldStop(256);
if (true) return main.mostCurrent.__c.getField(true,"False");
 };
 BA.debugLineNum = 107;BA.debugLine="pager.abortAnimation()";
Debug.ShouldStop(1024);
main.mostCurrent._pager.runVoidMethod ("abortAnimation");
 BA.debugLineNum = 108;BA.debugLine="currentIndex = currentIndex - 1";
Debug.ShouldStop(2048);
main._currentindex = RemoteObject.solve(new RemoteObject[] {main._currentindex,RemoteObject.createImmutable(1)}, "-",1, 1);
 BA.debugLineNum = 109;BA.debugLine="Log(currentIndex)";
Debug.ShouldStop(4096);
main.mostCurrent.__c.runVoidMethod ("LogImpl","3393230",BA.NumberToString(main._currentindex),0);
 }else {
 BA.debugLineNum = 111;BA.debugLine="If (currentIndex + 1) = count Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",(RemoteObject.solve(new RemoteObject[] {main._currentindex,RemoteObject.createImmutable(1)}, "+",1, 1)),BA.numberCast(double.class, main._count))) { 
 BA.debugLineNum = 112;BA.debugLine="Return False";
Debug.ShouldStop(32768);
if (true) return main.mostCurrent.__c.getField(true,"False");
 };
 BA.debugLineNum = 114;BA.debugLine="pager.abortAnimation()";
Debug.ShouldStop(131072);
main.mostCurrent._pager.runVoidMethod ("abortAnimation");
 BA.debugLineNum = 115;BA.debugLine="currentIndex = currentIndex + 1";
Debug.ShouldStop(262144);
main._currentindex = RemoteObject.solve(new RemoteObject[] {main._currentindex,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 116;BA.debugLine="loadImage(mNextPageCanvas, currentIndex)";
Debug.ShouldStop(524288);
_loadimage(main.mostCurrent._mnextpagecanvas,main._currentindex);
 };
 }else 
{ BA.debugLineNum = 118;BA.debugLine="else If (e.Action = e.ACTION_UP) Then";
Debug.ShouldStop(2097152);
if ((RemoteObject.solveBoolean("=",_e.runMethod(true,"getAction"),BA.numberCast(double.class, _e.getField(true,"ACTION_UP"))))) { 
 BA.debugLineNum = 119;BA.debugLine="If (pager.canDragOver = False) Then";
Debug.ShouldStop(4194304);
if ((RemoteObject.solveBoolean("=",main.mostCurrent._pager.runMethod(true,"canDragOver"),main.mostCurrent.__c.getField(true,"False")))) { 
 BA.debugLineNum = 120;BA.debugLine="currentIndex = lastIndex";
Debug.ShouldStop(8388608);
main._currentindex = main._lastindex;
 BA.debugLineNum = 121;BA.debugLine="currentPage = lastBitmap";
Debug.ShouldStop(16777216);
main.mostCurrent._currentpage = main.mostCurrent._lastbitmap;
 };
 }}
;
 BA.debugLineNum = 125;BA.debugLine="ret = pager.doTouchEvent(e)";
Debug.ShouldStop(268435456);
_ret = main.mostCurrent._pager.runMethod(true,"doTouchEvent",(Object)((_e.getObject())));Debug.locals.put("ret", _ret);
 BA.debugLineNum = 126;BA.debugLine="Return ret";
Debug.ShouldStop(536870912);
if (true) return _ret;
 BA.debugLineNum = 128;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}