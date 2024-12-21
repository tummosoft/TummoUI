package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,32);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _btn1 = RemoteObject.declareNull("com.tummosoft.android.views.xShadowButton");
RemoteObject _btn2 = RemoteObject.declareNull("com.tummosoft.android.views.xShadowButton");
RemoteObject _tuutils = RemoteObject.declareNull("com.tummosoft.android.utils.ResourcesHelper");
RemoteObject _logo = RemoteObject.createImmutable(0);
RemoteObject _clr = RemoteObject.declareNull("com.tummosoft.android.utils.GradientHelper");
RemoteObject _radis = null;
RemoteObject _cl = null;
RemoteObject _btnround = RemoteObject.declareNull("com.tummosoft.android.views.xShadowButton");
RemoteObject _dh = RemoteObject.declareNull("com.tummosoft.android.utils.DrawableHelper");
RemoteObject _img3 = RemoteObject.declareNull("com.tummosoft.android.views.xShadowImageView");
RemoteObject _img4 = RemoteObject.declareNull("com.tummosoft.android.views.xShadowImageView");
RemoteObject _img1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 32;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"Layout\")";
Debug.ShouldStop(1);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 34;BA.debugLine="Dim btn1 As xShadowButton";
Debug.ShouldStop(2);
_btn1 = RemoteObject.createNew ("com.tummosoft.android.views.xShadowButton");Debug.locals.put("btn1", _btn1);
 BA.debugLineNum = 35;BA.debugLine="btn1.initialize(\"btn1\")";
Debug.ShouldStop(4);
_btn1.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("btn1")));
 BA.debugLineNum = 36;BA.debugLine="btn1.Text = \"btn1\"";
Debug.ShouldStop(8);
_btn1.runVoidMethod ("setText",BA.ObjectToString("btn1"));
 BA.debugLineNum = 37;BA.debugLine="btn1.UnpressedColor = \"#9cea1b\"";
Debug.ShouldStop(16);
_btn1.runVoidMethod ("setUnpressedColor",BA.ObjectToString("#9cea1b"));
 BA.debugLineNum = 38;BA.debugLine="btn1.TextColor = \"#ffffff\"";
Debug.ShouldStop(32);
_btn1.runVoidMethod ("setTextColor",BA.ObjectToString("#ffffff"));
 BA.debugLineNum = 39;BA.debugLine="btn1.setPadding(5dip, 10dip, 5dip, 5dip)";
Debug.ShouldStop(64);
_btn1.runVoidMethod ("setPadding",(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 40;BA.debugLine="btn1.Radius = 6dip";
Debug.ShouldStop(128);
_btn1.runVoidMethod ("setRadius",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 6))));
 BA.debugLineNum = 42;BA.debugLine="Activity.AddView(btn1, 20%x,10dip, 40dip, 40dip)";
Debug.ShouldStop(512);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_btn1.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 20)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 44;BA.debugLine="Dim btn2 As xShadowButton";
Debug.ShouldStop(2048);
_btn2 = RemoteObject.createNew ("com.tummosoft.android.views.xShadowButton");Debug.locals.put("btn2", _btn2);
 BA.debugLineNum = 45;BA.debugLine="btn2.initialize(\"btn2\")";
Debug.ShouldStop(4096);
_btn2.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("btn2")));
 BA.debugLineNum = 46;BA.debugLine="btn2.Text = \"btn2\"";
Debug.ShouldStop(8192);
_btn2.runVoidMethod ("setText",BA.ObjectToString("btn2"));
 BA.debugLineNum = 47;BA.debugLine="btn2.UnpressedColor = \"#0075b4\"";
Debug.ShouldStop(16384);
_btn2.runVoidMethod ("setUnpressedColor",BA.ObjectToString("#0075b4"));
 BA.debugLineNum = 48;BA.debugLine="btn2.TextColor = \"#ffffff\"";
Debug.ShouldStop(32768);
_btn2.runVoidMethod ("setTextColor",BA.ObjectToString("#ffffff"));
 BA.debugLineNum = 49;BA.debugLine="btn2.Alpha = 40";
Debug.ShouldStop(65536);
_btn2.runVoidMethod ("setAlpha",BA.numberCast(float.class, 40));
 BA.debugLineNum = 50;BA.debugLine="btn2.PressedColor = \"#f0ea20\"";
Debug.ShouldStop(131072);
_btn2.runVoidMethod ("setPressedColor",BA.ObjectToString("#f0ea20"));
 BA.debugLineNum = 51;BA.debugLine="btn2.setPadding(5dip, 10dip, 5dip, 5dip)";
Debug.ShouldStop(262144);
_btn2.runVoidMethod ("setPadding",(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 52;BA.debugLine="btn2.ShapeType = 0";
Debug.ShouldStop(524288);
_btn2.runVoidMethod ("setShapeType",BA.numberCast(int.class, 0));
 BA.debugLineNum = 53;BA.debugLine="btn2.Gravity = Gravity.CENTER";
Debug.ShouldStop(1048576);
_btn2.runVoidMethod ("setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 54;BA.debugLine="btn2.Elevation = 0.2f";
Debug.ShouldStop(2097152);
_btn2.runVoidMethod ("setElevation",BA.numberCast(float.class, 0.2f));
 BA.debugLineNum = 55;BA.debugLine="btn2.setShadowLayer(8.0f, 2.0f, 2.0f, \"#494942\")";
Debug.ShouldStop(4194304);
_btn2.runVoidMethod ("setShadowLayer",(Object)(BA.numberCast(float.class, 8.0f)),(Object)(BA.numberCast(float.class, 2.0f)),(Object)(BA.numberCast(float.class, 2.0f)),(Object)(RemoteObject.createImmutable("#494942")));
 BA.debugLineNum = 56;BA.debugLine="Activity.AddView(btn2, 20%x, 60dip, 60dip, 60dip)";
Debug.ShouldStop(8388608);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_btn2.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 20)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 58;BA.debugLine="Dim tuUtils As ResourcesHelper";
Debug.ShouldStop(33554432);
_tuutils = RemoteObject.createNew ("com.tummosoft.android.utils.ResourcesHelper");Debug.locals.put("tuUtils", _tuutils);
 BA.debugLineNum = 59;BA.debugLine="tuUtils.initialize";
Debug.ShouldStop(67108864);
_tuutils.runVoidMethod ("initialize",main.processBA);
 BA.debugLineNum = 61;BA.debugLine="Dim logo As Int = tuUtils.getResourceId(\"icon\",\"d";
Debug.ShouldStop(268435456);
_logo = _tuutils.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("icon")),(Object)(RemoteObject.createImmutable("drawable")));Debug.locals.put("logo", _logo);Debug.locals.put("logo", _logo);
 BA.debugLineNum = 63;BA.debugLine="Dim clr As GradientHelper";
Debug.ShouldStop(1073741824);
_clr = RemoteObject.createNew ("com.tummosoft.android.utils.GradientHelper");Debug.locals.put("clr", _clr);
 BA.debugLineNum = 64;BA.debugLine="clr.initialize(clr.Orientation_RIGHT_LEFT)";
Debug.ShouldStop(-2147483648);
_clr.runVoidMethod ("initialize",main.processBA,(Object)(_clr.getField(true,"Orientation_RIGHT_LEFT")));
 BA.debugLineNum = 65;BA.debugLine="Dim radis() As Float  = Array As Float(90.0f,90.0";
Debug.ShouldStop(1);
_radis = RemoteObject.createNewArray("float",new int[] {8},new Object[] {BA.numberCast(float.class, 90.0f),BA.numberCast(float.class, 90.0f),BA.numberCast(float.class, 90.0f),BA.numberCast(float.class, 90.0f),BA.numberCast(float.class, 90.0f),BA.numberCast(float.class, 90.0f),BA.numberCast(float.class, 90.0f),BA.numberCast(float.class, 90.0f)});Debug.locals.put("radis", _radis);Debug.locals.put("radis", _radis);
 BA.debugLineNum = 66;BA.debugLine="clr.Radius = radis";
Debug.ShouldStop(2);
_clr.runVoidMethod ("setRadius",_radis);
 BA.debugLineNum = 67;BA.debugLine="clr.setGradientCenter(5,5)";
Debug.ShouldStop(4);
_clr.runVoidMethod ("setGradientCenter",(Object)(BA.numberCast(int.class, 5)),(Object)(BA.numberCast(int.class, 5)));
 BA.debugLineNum = 68;BA.debugLine="clr.Orientation = clr.Orientation_TL_BR";
Debug.ShouldStop(8);
_clr.runVoidMethod ("setOrientation",_clr.getField(true,"Orientation_TL_BR"));
 BA.debugLineNum = 69;BA.debugLine="Dim cl() As String  = Array As String(\"#00F9E5\",\"";
Debug.ShouldStop(16);
_cl = RemoteObject.createNewArray("String",new int[] {3},new Object[] {BA.ObjectToString("#00F9E5"),BA.ObjectToString("#6772FF"),RemoteObject.createImmutable("#4A54FF")});Debug.locals.put("cl", _cl);Debug.locals.put("cl", _cl);
 BA.debugLineNum = 70;BA.debugLine="clr.Colors = cl";
Debug.ShouldStop(32);
_clr.runVoidMethod ("setColors",_cl);
 BA.debugLineNum = 71;BA.debugLine="clr.setStroke(2dip, \"#494942\")";
Debug.ShouldStop(64);
_clr.runVoidMethod ("setStroke",(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(RemoteObject.createImmutable("#494942")));
 BA.debugLineNum = 74;BA.debugLine="Dim btnRound As xShadowButton";
Debug.ShouldStop(512);
_btnround = RemoteObject.createNew ("com.tummosoft.android.views.xShadowButton");Debug.locals.put("btnRound", _btnround);
 BA.debugLineNum = 75;BA.debugLine="btnRound.initialize(\"btnRound\")";
Debug.ShouldStop(1024);
_btnround.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("btnRound")));
 BA.debugLineNum = 76;BA.debugLine="btnRound.Elevation = 1.6f";
Debug.ShouldStop(2048);
_btnround.runVoidMethod ("setElevation",BA.numberCast(float.class, 1.6f));
 BA.debugLineNum = 77;BA.debugLine="btnRound.Radius = 60dip / 2";
Debug.ShouldStop(4096);
_btnround.runVoidMethod ("setRadius",BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60))),RemoteObject.createImmutable(2)}, "/",0, 0)));
 BA.debugLineNum = 78;BA.debugLine="btnRound.BackgroundDrawable = clr";
Debug.ShouldStop(8192);
_btnround.runVoidMethod ("setBackgroundDrawable",(_clr.getObject()));
 BA.debugLineNum = 80;BA.debugLine="Dim logo As Int = tuUtils.getResourceId(\"icon\",\"d";
Debug.ShouldStop(32768);
_logo = _tuutils.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("icon")),(Object)(RemoteObject.createImmutable("drawable")));Debug.locals.put("logo", _logo);Debug.locals.put("logo", _logo);
 BA.debugLineNum = 81;BA.debugLine="Dim dh As DrawableHelper";
Debug.ShouldStop(65536);
_dh = RemoteObject.createNew ("com.tummosoft.android.utils.DrawableHelper");Debug.locals.put("dh", _dh);
 BA.debugLineNum = 82;BA.debugLine="dh.initialize(\"dh\")";
Debug.ShouldStop(131072);
_dh.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("dh")));
 BA.debugLineNum = 83;BA.debugLine="btnRound.Foreground = dh.Bitmap2Drawable(LoadBitm";
Debug.ShouldStop(262144);
_btnround.runVoidMethod ("setForeground",_dh.runMethod(false,"Bitmap2Drawable",(Object)((main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ho_guom.jpg"))).getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40))))));
 BA.debugLineNum = 84;BA.debugLine="btnRound.ForegroundGravity = Gravity.CENTER_HORIZ";
Debug.ShouldStop(524288);
_btnround.runVoidMethod ("setForegroundGravity",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_HORIZONTAL"),main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL")}, "+",1, 1));
 BA.debugLineNum = 86;BA.debugLine="Activity.AddView(btnRound, 20%x, 160dip, 60dip, 6";
Debug.ShouldStop(2097152);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_btnround.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 20)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 160)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 89;BA.debugLine="Dim img3 As xShadowImageView";
Debug.ShouldStop(16777216);
_img3 = RemoteObject.createNew ("com.tummosoft.android.views.xShadowImageView");Debug.locals.put("img3", _img3);
 BA.debugLineNum = 90;BA.debugLine="img3.initialize(\"btn2\")";
Debug.ShouldStop(33554432);
_img3.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("btn2")));
 BA.debugLineNum = 91;BA.debugLine="img3.ImageBitmap = LoadBitmap(File.DirAssets, \"ho";
Debug.ShouldStop(67108864);
_img3.runVoidMethod ("setImageBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ho_guom.jpg"))).getObject()));
 BA.debugLineNum = 92;BA.debugLine="img3.Elevation = 0.6f";
Debug.ShouldStop(134217728);
_img3.runVoidMethod ("setElevation",BA.numberCast(float.class, 0.6f));
 BA.debugLineNum = 93;BA.debugLine="img3.CropToPadding = True";
Debug.ShouldStop(268435456);
_img3.runVoidMethod ("setCropToPadding",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 94;BA.debugLine="img3.ScaleType = \"CENTER_INSIDE\" '\"MATRIX, CENTER";
Debug.ShouldStop(536870912);
_img3.runVoidMethod ("setScaleType",BA.ObjectToString("CENTER_INSIDE"));
 BA.debugLineNum = 96;BA.debugLine="Activity.AddView(img3, 20%x, 240dip, 60dip, 60dip";
Debug.ShouldStop(-2147483648);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_img3.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 20)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 98;BA.debugLine="Dim img4 As xShadowImageView";
Debug.ShouldStop(2);
_img4 = RemoteObject.createNew ("com.tummosoft.android.views.xShadowImageView");Debug.locals.put("img4", _img4);
 BA.debugLineNum = 99;BA.debugLine="img4.initialize(\"btn2\")";
Debug.ShouldStop(4);
_img4.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("btn2")));
 BA.debugLineNum = 101;BA.debugLine="img4.ImageURI = \"https://cdn.tuoitre.vn/zoom/456_";
Debug.ShouldStop(16);
_img4.runVoidMethod ("setImageURI",BA.ObjectToString("https://cdn.tuoitre.vn/zoom/456_285/471584752817336320/2024/12/3/nghi-tet2-17319175356721565073028-46-0-1296-2000-crop-17332237459741794590416.jpg"));
 BA.debugLineNum = 102;BA.debugLine="img4.ScaleType = \"MATRIX\" '\"MATRIX, CENTER_CROP,C";
Debug.ShouldStop(32);
_img4.runVoidMethod ("setScaleType",BA.ObjectToString("MATRIX"));
 BA.debugLineNum = 104;BA.debugLine="Activity.AddView(img4, 20%x, 320dip, 60dip, 60dip";
Debug.ShouldStop(128);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_img4.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 20)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 320)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 108;BA.debugLine="xSheetDialog.initialize(\"xSheetDialog\")";
Debug.ShouldStop(2048);
main._xsheetdialog.runVoidMethod ("initialize",main.processBA,(Object)(RemoteObject.createImmutable("xSheetDialog")));
 BA.debugLineNum = 109;BA.debugLine="Dim img1 As ImageView";
Debug.ShouldStop(4096);
_img1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");Debug.locals.put("img1", _img1);
 BA.debugLineNum = 110;BA.debugLine="img1.Initialize(\"\")";
Debug.ShouldStop(8192);
_img1.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 111;BA.debugLine="img1.Bitmap = LoadBitmap(File.DirAssets, \"ho_guom";
Debug.ShouldStop(16384);
_img1.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ho_guom.jpg"))).getObject()));
 BA.debugLineNum = 113;BA.debugLine="xSheetDialog.addView(img1)";
Debug.ShouldStop(65536);
main._xsheetdialog.runVoidMethod ("addView",(Object)((_img1.getObject())));
 BA.debugLineNum = 114;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,120);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 120;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 122;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,116);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 116;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 118;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnround_click(RemoteObject _view) throws Exception{
try {
		Debug.PushSubsStack("btnRound_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,124);
if (RapidSub.canDelegate("btnround_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnround_click", _view);}
Debug.locals.put("view", _view);
 BA.debugLineNum = 124;BA.debugLine="Sub btnRound_Click(view As Object)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 126;BA.debugLine="xSheetDialog.Show";
Debug.ShouldStop(536870912);
main._xsheetdialog.runVoidMethod ("Show");
 BA.debugLineNum = 127;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 21;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 24;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 25;BA.debugLine="Dim xSheetDialog As xBottomSheet";
main._xsheetdialog = RemoteObject.createNew ("com.tummosoft.android.views.xBottomSheet");
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}