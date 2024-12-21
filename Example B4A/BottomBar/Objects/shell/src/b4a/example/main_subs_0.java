package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,30);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _w = RemoteObject.createImmutable(0);
RemoteObject _left = RemoteObject.createImmutable(0);
RemoteObject _tuutils = RemoteObject.declareNull("com.tummosoft.android.utils.ResourcesHelper");
RemoteObject _bottombar = RemoteObject.declareNull("com.tummosoft.android.views.xNavigationBar");
RemoteObject _iconhome = RemoteObject.createImmutable(0);
RemoteObject _iconhistory = RemoteObject.createImmutable(0);
RemoteObject _iconfave = RemoteObject.createImmutable(0);
RemoteObject _iconbook = RemoteObject.createImmutable(0);
RemoteObject _iconbg = RemoteObject.createImmutable(0);
RemoteObject _draw = RemoteObject.declareNull("com.tummosoft.android.utils.DrawableHelper");
RemoteObject _clr2 = RemoteObject.declareNull("com.tummosoft.android.utils.GradientHelper");
RemoteObject _cl2 = null;
int _i = 0;
RemoteObject _buttonbar = RemoteObject.declareNull("com.tummosoft.android.views.xButtonImage");
RemoteObject _pnlwrapper = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 31;BA.debugLine="Activity.LoadLayout(\"xConstraintLayout\")";
Debug.ShouldStop(1073741824);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("xConstraintLayout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 33;BA.debugLine="Dim w As Int = 100%x / 4";
Debug.ShouldStop(1);
_w = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),RemoteObject.createImmutable(4)}, "/",0, 0));Debug.locals.put("w", _w);Debug.locals.put("w", _w);
 BA.debugLineNum = 34;BA.debugLine="Dim left As Int = (w - 15dip) / 2";
Debug.ShouldStop(2);
_left = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_w,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))}, "-",1, 1)),RemoteObject.createImmutable(2)}, "/",0, 0));Debug.locals.put("left", _left);Debug.locals.put("left", _left);
 BA.debugLineNum = 36;BA.debugLine="Dim tuUtils As ResourcesHelper";
Debug.ShouldStop(8);
_tuutils = RemoteObject.createNew ("com.tummosoft.android.utils.ResourcesHelper");Debug.locals.put("tuUtils", _tuutils);
 BA.debugLineNum = 37;BA.debugLine="tuUtils.initialize";
Debug.ShouldStop(16);
_tuutils.runVoidMethod ("initialize",main.processBA);
 BA.debugLineNum = 39;BA.debugLine="Dim bottombar As xNavigationBar";
Debug.ShouldStop(64);
_bottombar = RemoteObject.createNew ("com.tummosoft.android.views.xNavigationBar");Debug.locals.put("bottombar", _bottombar);
 BA.debugLineNum = 40;BA.debugLine="bottombar.initialize(\"bottombar\")";
Debug.ShouldStop(128);
_bottombar.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("bottombar")));
 BA.debugLineNum = 41;BA.debugLine="Dim iconhome As Int = tuUtils.getResourceId(\"home";
Debug.ShouldStop(256);
_iconhome = _tuutils.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("home")),(Object)(RemoteObject.createImmutable("drawable")));Debug.locals.put("iconhome", _iconhome);Debug.locals.put("iconhome", _iconhome);
 BA.debugLineNum = 42;BA.debugLine="Dim iconhistory As Int = tuUtils.getResourceId(\"c";
Debug.ShouldStop(512);
_iconhistory = _tuutils.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("clock")),(Object)(RemoteObject.createImmutable("drawable")));Debug.locals.put("iconhistory", _iconhistory);Debug.locals.put("iconhistory", _iconhistory);
 BA.debugLineNum = 43;BA.debugLine="Dim iconfave As Int = tuUtils.getResourceId(\"fave";
Debug.ShouldStop(1024);
_iconfave = _tuutils.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("fave")),(Object)(RemoteObject.createImmutable("drawable")));Debug.locals.put("iconfave", _iconfave);Debug.locals.put("iconfave", _iconfave);
 BA.debugLineNum = 44;BA.debugLine="Dim iconbook As Int = tuUtils.getResourceId(\"book";
Debug.ShouldStop(2048);
_iconbook = _tuutils.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("book")),(Object)(RemoteObject.createImmutable("drawable")));Debug.locals.put("iconbook", _iconbook);Debug.locals.put("iconbook", _iconbook);
 BA.debugLineNum = 45;BA.debugLine="Dim iconbg As Int = tuUtils.getResourceId(\"bg\",\"d";
Debug.ShouldStop(4096);
_iconbg = _tuutils.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("bg")),(Object)(RemoteObject.createImmutable("drawable")));Debug.locals.put("iconbg", _iconbg);Debug.locals.put("iconbg", _iconbg);
 BA.debugLineNum = 47;BA.debugLine="Dim draw As DrawableHelper";
Debug.ShouldStop(16384);
_draw = RemoteObject.createNew ("com.tummosoft.android.utils.DrawableHelper");Debug.locals.put("draw", _draw);
 BA.debugLineNum = 48;BA.debugLine="draw.initialize(\"draw\")";
Debug.ShouldStop(32768);
_draw.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("draw")));
 BA.debugLineNum = 50;BA.debugLine="Dim clr2 As GradientHelper";
Debug.ShouldStop(131072);
_clr2 = RemoteObject.createNew ("com.tummosoft.android.utils.GradientHelper");Debug.locals.put("clr2", _clr2);
 BA.debugLineNum = 51;BA.debugLine="clr2.initialize(clr2.Orientation_BOTTOM_TOP)";
Debug.ShouldStop(262144);
_clr2.runVoidMethod ("initialize",main.processBA,(Object)(_clr2.getField(true,"Orientation_BOTTOM_TOP")));
 BA.debugLineNum = 52;BA.debugLine="clr2.setGradientCenter(5,5)";
Debug.ShouldStop(524288);
_clr2.runVoidMethod ("setGradientCenter",(Object)(BA.numberCast(int.class, 5)),(Object)(BA.numberCast(int.class, 5)));
 BA.debugLineNum = 53;BA.debugLine="clr2.Orientation = clr2.Orientation_TL_BR";
Debug.ShouldStop(1048576);
_clr2.runVoidMethod ("setOrientation",_clr2.getField(true,"Orientation_TL_BR"));
 BA.debugLineNum = 54;BA.debugLine="Dim cl2() As String  = Array As String(\"#FAC54B\",";
Debug.ShouldStop(2097152);
_cl2 = RemoteObject.createNewArray("String",new int[] {3},new Object[] {BA.ObjectToString("#FAC54B"),BA.ObjectToString("#FED26C"),RemoteObject.createImmutable("#C85637")});Debug.locals.put("cl2", _cl2);Debug.locals.put("cl2", _cl2);
 BA.debugLineNum = 55;BA.debugLine="clr2.Colors = cl2";
Debug.ShouldStop(4194304);
_clr2.runVoidMethod ("setColors",_cl2);
 BA.debugLineNum = 56;BA.debugLine="bottombar.BackgroundDrawable = clr2";
Debug.ShouldStop(8388608);
_bottombar.runVoidMethod ("setBackgroundDrawable",(_clr2.getObject()));
 BA.debugLineNum = 58;BA.debugLine="For i=0 To 3";
Debug.ShouldStop(33554432);
{
final int step22 = 1;
final int limit22 = 3;
_i = 0 ;
for (;(step22 > 0 && _i <= limit22) || (step22 < 0 && _i >= limit22) ;_i = ((int)(0 + _i + step22))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 59;BA.debugLine="Dim buttonBar As xButtonImage";
Debug.ShouldStop(67108864);
_buttonbar = RemoteObject.createNew ("com.tummosoft.android.views.xButtonImage");Debug.locals.put("buttonBar", _buttonbar);
 BA.debugLineNum = 60;BA.debugLine="buttonBar.initialize(\"buttonBar\")";
Debug.ShouldStop(134217728);
_buttonbar.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("buttonBar")));
 BA.debugLineNum = 61;BA.debugLine="buttonBar.TagName = \"Item \" & i";
Debug.ShouldStop(268435456);
_buttonbar.runMethod(true,"setTagName",RemoteObject.concat(RemoteObject.createImmutable("Item "),RemoteObject.createImmutable(_i)));
 BA.debugLineNum = 62;BA.debugLine="If (i = 0) Then";
Debug.ShouldStop(536870912);
if ((RemoteObject.solveBoolean("=",RemoteObject.createImmutable(_i),BA.numberCast(double.class, 0)))) { 
 BA.debugLineNum = 63;BA.debugLine="buttonBar.ImageResourceID = iconhome";
Debug.ShouldStop(1073741824);
_buttonbar.runVoidMethod ("setImageResourceID",_iconhome);
 }else 
{ BA.debugLineNum = 64;BA.debugLine="else if (i = 1) Then";
Debug.ShouldStop(-2147483648);
if ((RemoteObject.solveBoolean("=",RemoteObject.createImmutable(_i),BA.numberCast(double.class, 1)))) { 
 BA.debugLineNum = 65;BA.debugLine="buttonBar.BackgroundDrawable = draw.getDrawable";
Debug.ShouldStop(1);
_buttonbar.runVoidMethod ("setBackgroundDrawable",_draw.runMethod(false,"getDrawable",(Object)(_iconhistory)));
 }else 
{ BA.debugLineNum = 66;BA.debugLine="else if (i = 2) Then";
Debug.ShouldStop(2);
if ((RemoteObject.solveBoolean("=",RemoteObject.createImmutable(_i),BA.numberCast(double.class, 2)))) { 
 BA.debugLineNum = 67;BA.debugLine="buttonBar.BackgroundDrawable = draw.getDrawable";
Debug.ShouldStop(4);
_buttonbar.runVoidMethod ("setBackgroundDrawable",_draw.runMethod(false,"getDrawable",(Object)(_iconfave)));
 }else 
{ BA.debugLineNum = 68;BA.debugLine="else if (i = 3) Then";
Debug.ShouldStop(8);
if ((RemoteObject.solveBoolean("=",RemoteObject.createImmutable(_i),BA.numberCast(double.class, 3)))) { 
 BA.debugLineNum = 69;BA.debugLine="buttonBar.BackgroundDrawable = draw.getDrawable";
Debug.ShouldStop(16);
_buttonbar.runVoidMethod ("setBackgroundDrawable",_draw.runMethod(false,"getDrawable",(Object)(_iconbook)));
 }}}}
;
 BA.debugLineNum = 72;BA.debugLine="Dim pnlWrapper As Panel";
Debug.ShouldStop(128);
_pnlwrapper = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlWrapper", _pnlwrapper);
 BA.debugLineNum = 73;BA.debugLine="pnlWrapper.Initialize(\"pnlWrapper\")";
Debug.ShouldStop(256);
_pnlwrapper.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlWrapper")));
 BA.debugLineNum = 74;BA.debugLine="pnlWrapper.AddView(buttonBar, left,5dip,15dip, 1";
Debug.ShouldStop(512);
_pnlwrapper.runVoidMethod ("AddView",(Object)((_buttonbar.getObject())),(Object)(_left),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))));
 BA.debugLineNum = 75;BA.debugLine="pnlWrapper.Color = Colors.Transparent";
Debug.ShouldStop(1024);
_pnlwrapper.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 77;BA.debugLine="bottombar.AddView(pnlWrapper)";
Debug.ShouldStop(4096);
_bottombar.runVoidMethod ("AddView",(Object)((_pnlwrapper.getObject())));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 81;BA.debugLine="bottombar.setPadding(0, 10dip, 0dip, 10dip)";
Debug.ShouldStop(65536);
_bottombar.runVoidMethod ("setPadding",(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 0)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))));
 BA.debugLineNum = 83;BA.debugLine="bottombar.Build()";
Debug.ShouldStop(262144);
_bottombar.runVoidMethod ("Build");
 BA.debugLineNum = 85;BA.debugLine="Activity.AddView(bottombar,0,100%y-35dip, 100%x,";
Debug.ShouldStop(1048576);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_bottombar.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 35)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 35)))));
 BA.debugLineNum = 86;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,92);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 92;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 94;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,88);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 88;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 90;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _buttonbar_click(RemoteObject _view) throws Exception{
try {
		Debug.PushSubsStack("buttonBar_click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,96);
if (RapidSub.canDelegate("buttonbar_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","buttonbar_click", _view);}
RemoteObject _v = RemoteObject.declareNull("com.tummosoft.android.views.xButtonImage");
Debug.locals.put("view", _view);
 BA.debugLineNum = 96;BA.debugLine="Sub buttonBar_click(view As Object)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 97;BA.debugLine="Dim v As xButtonImage = view";
Debug.ShouldStop(1);
_v = RemoteObject.createNew ("com.tummosoft.android.views.xButtonImage");
_v = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("com.tummosoft.android.views.xButtonImage"), _view);Debug.locals.put("v", _v);Debug.locals.put("v", _v);
 BA.debugLineNum = 98;BA.debugLine="xui.MsgboxAsync(\"\" & v.TagName, \"B4X\")";
Debug.ShouldStop(2);
main._xui.runVoidMethod ("MsgboxAsync",main.processBA,(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable(""),_v.runMethod(true,"getTagName")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("B4X"))));
 BA.debugLineNum = 99;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 26;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

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
 //BA.debugLineNum = 20;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}