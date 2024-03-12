package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,46);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 46;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 47;BA.debugLine="Activity.LoadLayout(\"layout2\")";
Debug.ShouldStop(16384);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layout2")),main.mostCurrent.activityBA);
 BA.debugLineNum = 48;BA.debugLine="anim.initialize(\"anim\")";
Debug.ShouldStop(32768);
main.mostCurrent._anim.runVoidMethod ("initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("anim")));
 BA.debugLineNum = 49;BA.debugLine="xImageView1.initialize2(\"xImageView1\")";
Debug.ShouldStop(65536);
main.mostCurrent._ximageview1.runVoidMethod ("initialize2",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("xImageView1")));
 BA.debugLineNum = 50;BA.debugLine="Activity.AddView(xImageView1, 0,0,200dip,200dip)";
Debug.ShouldStop(131072);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._ximageview1.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))));
 BA.debugLineNum = 51;BA.debugLine="xImageView1.HasCircle = True";
Debug.ShouldStop(262144);
main.mostCurrent._ximageview1.runVoidMethod ("setHasCircle",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 52;BA.debugLine="xImageView1.Position = \"CENTER\" 'FULL CENTER_CROP";
Debug.ShouldStop(524288);
main.mostCurrent._ximageview1.runVoidMethod ("setPosition",BA.ObjectToString("CENTER"));
 BA.debugLineNum = 54;BA.debugLine="xImageView1.BitmapError = LoadBitmap(File.DirAsse";
Debug.ShouldStop(2097152);
main.mostCurrent._ximageview1.runVoidMethod ("setBitmapError",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("sign-red-error-icon-1.png"))).getObject()));
 BA.debugLineNum = 55;BA.debugLine="xImageView1.getImageFromURL(\"https://cdn.tuoitre.";
Debug.ShouldStop(4194304);
main.mostCurrent._ximageview1.runVoidMethod ("getImageFromURL",(Object)(RemoteObject.createImmutable("https://cdn.tuoitre.vn/zoom/456_285/471584752817336320/2024/3/7/thu-tuong-le-don-3-17097703635511079333768-306-703-1116-2000-crop-1709770847142527180620.jpeg")));
 BA.debugLineNum = 57;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,63);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 63;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 65;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,59);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 59;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 61;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _anim_onanimationupdate(RemoteObject _angle) throws Exception{
try {
		Debug.PushSubsStack("anim_onAnimationUpdate (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,144);
if (RapidSub.canDelegate("anim_onanimationupdate")) { return b4a.example.main.remoteMe.runUserSub(false, "main","anim_onanimationupdate", _angle);}
RemoteObject _x = RemoteObject.createImmutable(0f);
RemoteObject _y = RemoteObject.createImmutable(0f);
Debug.locals.put("angle", _angle);
 BA.debugLineNum = 144;BA.debugLine="Sub anim_onAnimationUpdate(angle As Float)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 146;BA.debugLine="Dim x As Float = centerX + radius * Cos(angle)";
Debug.ShouldStop(131072);
_x = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {main._centerx,main._radius,main.mostCurrent.__c.runMethod(true,"Cos",(Object)(BA.numberCast(double.class, _angle)))}, "+*",1, 0));Debug.locals.put("x", _x);Debug.locals.put("x", _x);
 BA.debugLineNum = 147;BA.debugLine="Dim y As Float = centerY + radius * Sin(angle)";
Debug.ShouldStop(262144);
_y = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {main._centery,main._radius,main.mostCurrent.__c.runMethod(true,"Sin",(Object)(BA.numberCast(double.class, _angle)))}, "+*",1, 0));Debug.locals.put("y", _y);Debug.locals.put("y", _y);
 BA.debugLineNum = 149;BA.debugLine="Label1.Left = x - Label1.Width / 2.0";
Debug.ShouldStop(1048576);
main.mostCurrent._label1.runMethod(true,"setLeft",BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_x,main.mostCurrent._label1.runMethod(true,"getWidth"),RemoteObject.createImmutable(2.0)}, "-/",1, 0)));
 BA.debugLineNum = 150;BA.debugLine="Label1.top = y - Label1.Height / 2.0";
Debug.ShouldStop(2097152);
main.mostCurrent._label1.runMethod(true,"setTop",BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {_y,main.mostCurrent._label1.runMethod(true,"getHeight"),RemoteObject.createImmutable(2.0)}, "-/",1, 0)));
 BA.debugLineNum = 152;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,67);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
 BA.debugLineNum = 67;BA.debugLine="Sub Button1_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 70;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button2_click() throws Exception{
try {
		Debug.PushSubsStack("Button2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,134);
if (RapidSub.canDelegate("button2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button2_click");}
RemoteObject _target = RemoteObject.createImmutable(0f);
RemoteObject _theend = RemoteObject.createImmutable(0f);
 BA.debugLineNum = 134;BA.debugLine="Private Sub Button2_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 135;BA.debugLine="radius = 100";
Debug.ShouldStop(64);
main._radius = BA.numberCast(float.class, 100);
 BA.debugLineNum = 136;BA.debugLine="centerX = (100%x + Label1.Width) / 2.0";
Debug.ShouldStop(128);
main._centerx = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent._label1.runMethod(true,"getWidth")}, "+",1, 1)),RemoteObject.createImmutable(2.0)}, "/",0, 0));
 BA.debugLineNum = 137;BA.debugLine="centerY = (100%y + Label1.Height) / 2.0";
Debug.ShouldStop(256);
main._centery = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent._label1.runMethod(true,"getHeight")}, "+",1, 1)),RemoteObject.createImmutable(2.0)}, "/",0, 0));
 BA.debugLineNum = 139;BA.debugLine="Dim target As Float = 0";
Debug.ShouldStop(1024);
_target = BA.numberCast(float.class, 0);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 140;BA.debugLine="Dim theend As Float = 360";
Debug.ShouldStop(2048);
_theend = BA.numberCast(float.class, 360);Debug.locals.put("theend", _theend);Debug.locals.put("theend", _theend);
 BA.debugLineNum = 141;BA.debugLine="anim.startAccelerateDecelerateInterpolator(Label1";
Debug.ShouldStop(4096);
main.mostCurrent._anim.runVoidMethod ("startAccelerateDecelerateInterpolator",(Object)((main.mostCurrent._label1.getObject())),(Object)(BA.numberCast(int.class, 2000)),(Object)(BA.ObjectToString("rotation")),(Object)(_target),(Object)(_theend));
 BA.debugLineNum = 142;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button3_click() throws Exception{
try {
		Debug.PushSubsStack("Button3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,154);
if (RapidSub.canDelegate("button3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button3_click");}
RemoteObject _target = RemoteObject.createImmutable(0f);
RemoteObject _theend = RemoteObject.createImmutable(0f);
 BA.debugLineNum = 154;BA.debugLine="Private Sub Button3_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 155;BA.debugLine="Dim target As Float = 200";
Debug.ShouldStop(67108864);
_target = BA.numberCast(float.class, 200);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 156;BA.debugLine="Dim theend As Float = 0";
Debug.ShouldStop(134217728);
_theend = BA.numberCast(float.class, 0);Debug.locals.put("theend", _theend);Debug.locals.put("theend", _theend);
 BA.debugLineNum = 157;BA.debugLine="anim.startAccelerateInterpolator(Label1, 2000, \"t";
Debug.ShouldStop(268435456);
main.mostCurrent._anim.runVoidMethod ("startAccelerateInterpolator",(Object)((main.mostCurrent._label1.getObject())),(Object)(BA.numberCast(int.class, 2000)),(Object)(BA.ObjectToString("translationX")),(Object)(_target),(Object)(_theend));
 BA.debugLineNum = 158;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button4_click() throws Exception{
try {
		Debug.PushSubsStack("Button4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,160);
if (RapidSub.canDelegate("button4_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button4_click");}
RemoteObject _target = RemoteObject.createImmutable(0f);
RemoteObject _theend = RemoteObject.createImmutable(0f);
 BA.debugLineNum = 160;BA.debugLine="Private Sub Button4_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 161;BA.debugLine="Dim target As Float = 200";
Debug.ShouldStop(1);
_target = BA.numberCast(float.class, 200);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 162;BA.debugLine="Dim theend As Float = 0";
Debug.ShouldStop(2);
_theend = BA.numberCast(float.class, 0);Debug.locals.put("theend", _theend);Debug.locals.put("theend", _theend);
 BA.debugLineNum = 163;BA.debugLine="anim.startAnticipateInterpolator(Label1, 2000, \"t";
Debug.ShouldStop(4);
main.mostCurrent._anim.runVoidMethod ("startAnticipateInterpolator",(Object)((main.mostCurrent._label1.getObject())),(Object)(BA.numberCast(int.class, 2000)),(Object)(BA.ObjectToString("translationX")),(Object)(_target),(Object)(_theend));
 BA.debugLineNum = 164;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button5_click() throws Exception{
try {
		Debug.PushSubsStack("Button5_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,166);
if (RapidSub.canDelegate("button5_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button5_click");}
RemoteObject _target = RemoteObject.createImmutable(0f);
RemoteObject _theend = RemoteObject.createImmutable(0f);
 BA.debugLineNum = 166;BA.debugLine="Private Sub Button5_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 167;BA.debugLine="Dim target As Float = 200";
Debug.ShouldStop(64);
_target = BA.numberCast(float.class, 200);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 168;BA.debugLine="Dim theend As Float = 0";
Debug.ShouldStop(128);
_theend = BA.numberCast(float.class, 0);Debug.locals.put("theend", _theend);Debug.locals.put("theend", _theend);
 BA.debugLineNum = 169;BA.debugLine="anim.startBounceInterpolator(Label1, 2000, \"trans";
Debug.ShouldStop(256);
main.mostCurrent._anim.runVoidMethod ("startBounceInterpolator",(Object)((main.mostCurrent._label1.getObject())),(Object)(BA.numberCast(int.class, 2000)),(Object)(BA.ObjectToString("translationX")),(Object)(_target),(Object)(_theend));
 BA.debugLineNum = 170;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button6_click() throws Exception{
try {
		Debug.PushSubsStack("Button6_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,172);
if (RapidSub.canDelegate("button6_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button6_click");}
RemoteObject _target = RemoteObject.createImmutable(0f);
RemoteObject _theend = RemoteObject.createImmutable(0f);
 BA.debugLineNum = 172;BA.debugLine="Private Sub Button6_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 173;BA.debugLine="Dim target As Float = 200";
Debug.ShouldStop(4096);
_target = BA.numberCast(float.class, 200);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 174;BA.debugLine="Dim theend As Float = 0";
Debug.ShouldStop(8192);
_theend = BA.numberCast(float.class, 0);Debug.locals.put("theend", _theend);Debug.locals.put("theend", _theend);
 BA.debugLineNum = 175;BA.debugLine="anim.startCycleInterpolator(Label1, 2000, \"transl";
Debug.ShouldStop(16384);
main.mostCurrent._anim.runVoidMethod ("startCycleInterpolator",(Object)((main.mostCurrent._label1.getObject())),(Object)(BA.numberCast(int.class, 2000)),(Object)(BA.ObjectToString("translationX")),(Object)(_target),(Object)(_theend));
 BA.debugLineNum = 176;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button7_click() throws Exception{
try {
		Debug.PushSubsStack("Button7_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,178);
if (RapidSub.canDelegate("button7_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button7_click");}
RemoteObject _target = RemoteObject.createImmutable(0f);
RemoteObject _theend = RemoteObject.createImmutable(0f);
 BA.debugLineNum = 178;BA.debugLine="Private Sub Button7_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 179;BA.debugLine="Dim target As Float = 200";
Debug.ShouldStop(262144);
_target = BA.numberCast(float.class, 200);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 180;BA.debugLine="Dim theend As Float = 0";
Debug.ShouldStop(524288);
_theend = BA.numberCast(float.class, 0);Debug.locals.put("theend", _theend);Debug.locals.put("theend", _theend);
 BA.debugLineNum = 181;BA.debugLine="anim.startDecelerateInterpolator(Label1, 2000, \"t";
Debug.ShouldStop(1048576);
main.mostCurrent._anim.runVoidMethod ("startDecelerateInterpolator",(Object)((main.mostCurrent._label1.getObject())),(Object)(BA.numberCast(int.class, 2000)),(Object)(BA.ObjectToString("translationX")),(Object)(_target),(Object)(_theend));
 BA.debugLineNum = 182;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button8_click() throws Exception{
try {
		Debug.PushSubsStack("Button8_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,184);
if (RapidSub.canDelegate("button8_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button8_click");}
RemoteObject _target = RemoteObject.createImmutable(0f);
RemoteObject _theend = RemoteObject.createImmutable(0f);
 BA.debugLineNum = 184;BA.debugLine="Private Sub Button8_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 185;BA.debugLine="Dim target As Float = 200";
Debug.ShouldStop(16777216);
_target = BA.numberCast(float.class, 200);Debug.locals.put("target", _target);Debug.locals.put("target", _target);
 BA.debugLineNum = 186;BA.debugLine="Dim theend As Float = 0";
Debug.ShouldStop(33554432);
_theend = BA.numberCast(float.class, 0);Debug.locals.put("theend", _theend);Debug.locals.put("theend", _theend);
 BA.debugLineNum = 187;BA.debugLine="anim.startLinearInterpolator(Label1, 2000, \"trans";
Debug.ShouldStop(67108864);
main.mostCurrent._anim.runVoidMethod ("startLinearInterpolator",(Object)((main.mostCurrent._label1.getObject())),(Object)(BA.numberCast(int.class, 2000)),(Object)(BA.ObjectToString("translationX")),(Object)(_target),(Object)(_theend));
 BA.debugLineNum = 188;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button9_click() throws Exception{
try {
		Debug.PushSubsStack("Button9_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,191);
if (RapidSub.canDelegate("button9_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button9_click");}
 BA.debugLineNum = 191;BA.debugLine="Private Sub Button9_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 193;BA.debugLine="End Sub";
Debug.ShouldStop(1);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim layout As xLinearLayout";
main.mostCurrent._layout = RemoteObject.createNew ("com.tummosoft.layout.xLinearLayout");
 //BA.debugLineNum = 26;BA.debugLine="Private Button1 As Button";
main.mostCurrent._button1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private Panel1 As Panel";
main.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private Button2 As Button";
main.mostCurrent._button2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private Button3 As Button";
main.mostCurrent._button3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private Button4 As Button";
main.mostCurrent._button4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private Button5 As Button";
main.mostCurrent._button5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private Button6 As Button";
main.mostCurrent._button6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private Label1 As Label";
main.mostCurrent._label1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Dim anim As xAnimation";
main.mostCurrent._anim = RemoteObject.createNew ("com.tummosoft.animation.xAnimation");
 //BA.debugLineNum = 36;BA.debugLine="Private Button7 As Button";
main.mostCurrent._button7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private Button8 As Button";
main.mostCurrent._button8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private Button9 As Button";
main.mostCurrent._button9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Dim radius As Float";
main._radius = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 40;BA.debugLine="Dim centerX As Float";
main._centerx = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 41;BA.debugLine="Dim centerY As Float";
main._centery = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 42;BA.debugLine="Private xImageView1 As xImageView";
main.mostCurrent._ximageview1 = RemoteObject.createNew ("com.tummosoft.control.image.xImageView");
 //BA.debugLineNum = 43;BA.debugLine="Private socket As Socket";
main.mostCurrent._socket = RemoteObject.createNew ("anywheresoftware.b4a.objects.SocketWrapper");
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 21;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _test1() throws Exception{
try {
		Debug.PushSubsStack("test1 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,104);
if (RapidSub.canDelegate("test1")) { return b4a.example.main.remoteMe.runUserSub(false, "main","test1");}
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lb2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _img = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
 BA.debugLineNum = 104;BA.debugLine="Sub test1()";
Debug.ShouldStop(128);
 BA.debugLineNum = 105;BA.debugLine="Dim layout As xLinearLayout";
Debug.ShouldStop(256);
main.mostCurrent._layout = RemoteObject.createNew ("com.tummosoft.layout.xLinearLayout");
 BA.debugLineNum = 106;BA.debugLine="layout.initialize(\"layout\", layout.ROW_VERTICAL,";
Debug.ShouldStop(512);
main.mostCurrent._layout.runVoidMethod ("initialize",main.processBA,(Object)(BA.ObjectToString("layout")),(Object)(main.mostCurrent._layout.getField(true,"ROW_VERTICAL")),(Object)(main.mostCurrent._layout.getField(true,"COL_CENTER_VERTICAL")));
 BA.debugLineNum = 107;BA.debugLine="Activity.AddView(layout, 0,0,100%x, 100%y)";
Debug.ShouldStop(1024);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._layout.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 109;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(4096);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 110;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(8192);
_lbl.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 111;BA.debugLine="lbl.Text = \"Hello, wellcome to Vietnam\"";
Debug.ShouldStop(16384);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence("Hello, wellcome to Vietnam"));
 BA.debugLineNum = 112;BA.debugLine="lbl.Color = Colors.Red";
Debug.ShouldStop(32768);
_lbl.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 113;BA.debugLine="lbl.Gravity = Gravity.CENTER";
Debug.ShouldStop(65536);
_lbl.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 114;BA.debugLine="layout.AddView(lbl,0, 20dip, 0, 0, 50%x, 50dip)";
Debug.ShouldStop(131072);
main.mostCurrent._layout.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 20)))),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 50)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 116;BA.debugLine="Dim lb2 As Label";
Debug.ShouldStop(524288);
_lb2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lb2", _lb2);
 BA.debugLineNum = 117;BA.debugLine="lb2.Initialize(\"\")";
Debug.ShouldStop(1048576);
_lb2.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 118;BA.debugLine="lb2.Text = \"Xin chao cac ban\"";
Debug.ShouldStop(2097152);
_lb2.runMethod(true,"setText",BA.ObjectToCharSequence("Xin chao cac ban"));
 BA.debugLineNum = 119;BA.debugLine="lb2.Color = Colors.Green";
Debug.ShouldStop(4194304);
_lb2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 120;BA.debugLine="lb2.Gravity = Gravity.CENTER";
Debug.ShouldStop(8388608);
_lb2.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 121;BA.debugLine="layout.AddView(lb2,0, 0, 0, 0, 100%x, 50dip)";
Debug.ShouldStop(16777216);
main.mostCurrent._layout.runVoidMethod ("AddView",(Object)((_lb2.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 123;BA.debugLine="Dim img As ImageView";
Debug.ShouldStop(67108864);
_img = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");Debug.locals.put("img", _img);
 BA.debugLineNum = 124;BA.debugLine="img.Initialize(\"\")";
Debug.ShouldStop(134217728);
_img.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 125;BA.debugLine="img.Bitmap = LoadBitmap(File.DirAssets, \"img2.jpg";
Debug.ShouldStop(268435456);
_img.runMethod(false,"setBitmap",(main.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("img2.jpg"))).getObject()));
 BA.debugLineNum = 126;BA.debugLine="img.Gravity = Gravity.FILL";
Debug.ShouldStop(536870912);
_img.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 127;BA.debugLine="layout.AddView(img,0, 0, 0, 0, 100dip, 100dip)";
Debug.ShouldStop(1073741824);
main.mostCurrent._layout.runVoidMethod ("AddView",(Object)((_img.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))));
 BA.debugLineNum = 128;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _test2() throws Exception{
try {
		Debug.PushSubsStack("test2 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,72);
if (RapidSub.canDelegate("test2")) { return b4a.example.main.remoteMe.runUserSub(false, "main","test2");}
RemoteObject _line = RemoteObject.declareNull("com.tummosoft.animation.line.xLineView");
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _lst = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
int _i = 0;
 BA.debugLineNum = 72;BA.debugLine="Sub test2()";
Debug.ShouldStop(128);
 BA.debugLineNum = 73;BA.debugLine="Activity.LoadLayout(\"layout3\")";
Debug.ShouldStop(256);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layout3")),main.mostCurrent.activityBA);
 BA.debugLineNum = 75;BA.debugLine="layout.initialize(\"layout\", layout.ROW_HORIZONTAL";
Debug.ShouldStop(1024);
main.mostCurrent._layout.runVoidMethod ("initialize",main.processBA,(Object)(BA.ObjectToString("layout")),(Object)(main.mostCurrent._layout.getField(true,"ROW_HORIZONTAL")),(Object)(main.mostCurrent._layout.getField(true,"COL_CENTER_VERTICAL")));
 BA.debugLineNum = 78;BA.debugLine="layout.Color = Colors.Magenta";
Debug.ShouldStop(8192);
main.mostCurrent._layout.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Magenta"));
 BA.debugLineNum = 79;BA.debugLine="Dim line As xLineView";
Debug.ShouldStop(16384);
_line = RemoteObject.createNew ("com.tummosoft.animation.line.xLineView");Debug.locals.put("line", _line);
 BA.debugLineNum = 80;BA.debugLine="line.initialize(5dip)";
Debug.ShouldStop(32768);
_line.runVoidMethod ("initialize",main.processBA,(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))));
 BA.debugLineNum = 83;BA.debugLine="Dim lbl As Label";
Debug.ShouldStop(262144);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 84;BA.debugLine="lbl.Initialize(\"\")";
Debug.ShouldStop(524288);
_lbl.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 85;BA.debugLine="layout.AddView(lbl, 0, 0, 0, 0, 100%x, 100%y)";
Debug.ShouldStop(1048576);
main.mostCurrent._layout.runVoidMethod ("AddView",(Object)((_lbl.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 86;BA.debugLine="lbl.Text = \"BOTTOM MENU\"";
Debug.ShouldStop(2097152);
_lbl.runMethod(true,"setText",BA.ObjectToCharSequence("BOTTOM MENU"));
 BA.debugLineNum = 87;BA.debugLine="lbl.Color = Colors.Red";
Debug.ShouldStop(4194304);
_lbl.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 88;BA.debugLine="lbl.Gravity = Gravity.CENTER";
Debug.ShouldStop(8388608);
_lbl.runMethod(true,"setGravity",main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER"));
 BA.debugLineNum = 91;BA.debugLine="Dim lst As ListView";
Debug.ShouldStop(67108864);
_lst = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");Debug.locals.put("lst", _lst);
 BA.debugLineNum = 92;BA.debugLine="lst.Initialize(\"\")";
Debug.ShouldStop(134217728);
_lst.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 93;BA.debugLine="layout.AddView(lst, 0, 5dip, 0, 0, 100%x, 100%y)";
Debug.ShouldStop(268435456);
main.mostCurrent._layout.runVoidMethod ("AddView",(Object)((_lst.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 94;BA.debugLine="lst.Color = Colors.DarkGray";
Debug.ShouldStop(536870912);
_lst.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"DarkGray"));
 BA.debugLineNum = 95;BA.debugLine="For i=0 To 10";
Debug.ShouldStop(1073741824);
{
final int step16 = 1;
final int limit16 = 10;
_i = 0 ;
for (;(step16 > 0 && _i <= limit16) || (step16 < 0 && _i >= limit16) ;_i = ((int)(0 + _i + step16))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 96;BA.debugLine="lst.AddTwoLines(\"This is LinearLayout with anima";
Debug.ShouldStop(-2147483648);
_lst.runVoidMethod ("AddTwoLines",(Object)(BA.ObjectToCharSequence("This is LinearLayout with animation")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("item loadding..."))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 101;BA.debugLine="Activity.AddView(layout, 0,0,100%x, 100%y)";
Debug.ShouldStop(16);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._layout.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 102;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ximageview1_click() throws Exception{
try {
		Debug.PushSubsStack("xImageView1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,130);
if (RapidSub.canDelegate("ximageview1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","ximageview1_click");}
 BA.debugLineNum = 130;BA.debugLine="Sub xImageView1_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 131;BA.debugLine="Log(\"Image has clicked\")";
Debug.ShouldStop(4);
main.mostCurrent.__c.runVoidMethod ("LogImpl","6524289",RemoteObject.createImmutable("Image has clicked"),0);
 BA.debugLineNum = 132;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}