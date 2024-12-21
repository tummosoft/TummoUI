package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,30);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _ctx = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _layout = RemoteObject.createImmutable(0);
RemoteObject _textid = RemoteObject.createImmutable(0);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 31;BA.debugLine="Activity.LoadLayout(\"Layout\")";
Debug.ShouldStop(1073741824);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 33;BA.debugLine="Dim ctx As JavaObject";
Debug.ShouldStop(1);
_ctx = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("ctx", _ctx);
 BA.debugLineNum = 34;BA.debugLine="ctx.InitializeContext";
Debug.ShouldStop(2);
_ctx.runVoidMethod ("InitializeContext",main.processBA);
 BA.debugLineNum = 35;BA.debugLine="resHelper.initialize";
Debug.ShouldStop(4);
main.mostCurrent._reshelper.runVoidMethod ("initialize",main.processBA);
 BA.debugLineNum = 38;BA.debugLine="Dim layout As Int = resHelper.getResourceId(\"xml\"";
Debug.ShouldStop(32);
_layout = main.mostCurrent._reshelper.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("xml")),(Object)(RemoteObject.createImmutable("notification")));Debug.locals.put("layout", _layout);Debug.locals.put("layout", _layout);
 BA.debugLineNum = 39;BA.debugLine="gpressĐowloadView.initialize(\"downloadgpress\", la";
Debug.ShouldStop(64);
main.mostCurrent._gpressđowloadview.runVoidMethod ("initialize",main.processBA,(Object)(BA.ObjectToString("downloadgpress")),(Object)(_layout));
 BA.debugLineNum = 41;BA.debugLine="Dim textID As Int = resHelper.getResourceId(\"prog";
Debug.ShouldStop(256);
_textid = main.mostCurrent._reshelper.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("progressDownload")),(Object)(RemoteObject.createImmutable("id")));Debug.locals.put("textID", _textid);Debug.locals.put("textID", _textid);
 BA.debugLineNum = 42;BA.debugLine="gpressĐowloadView.setProgressBar(textID, 100, 12,";
Debug.ShouldStop(512);
main.mostCurrent._gpressđowloadview.runVoidMethod ("setProgressBar",(Object)(_textid),(Object)(BA.numberCast(int.class, 100)),(Object)(BA.numberCast(int.class, 12)),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 44;BA.debugLine="tNotification.Initialize";
Debug.ShouldStop(2048);
main.mostCurrent._tnotification.runVoidMethod ("Initialize",main.processBA);
 BA.debugLineNum = 47;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,53);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 53;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 55;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,49);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 49;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(65536);
 BA.debugLineNum = 51;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,57);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
RemoteObject _icon = RemoteObject.createImmutable(0);
 BA.debugLineNum = 57;BA.debugLine="Sub Button1_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 59;BA.debugLine="Dim icon As Int = resHelper.getResourceId(\"drawab";
Debug.ShouldStop(67108864);
_icon = main.mostCurrent._reshelper.runMethod(true,"getResourceId",(Object)(BA.ObjectToString("drawable")),(Object)(RemoteObject.createImmutable("icon")));Debug.locals.put("icon", _icon);Debug.locals.put("icon", _icon);
 BA.debugLineNum = 64;BA.debugLine="tNotification.Icon = \"icon\"";
Debug.ShouldStop(-2147483648);
main.mostCurrent._tnotification.runVoidMethod ("setIcon",BA.ObjectToString("icon"));
 BA.debugLineNum = 65;BA.debugLine="tNotification.SetInfo3New(\"This is the title\", \"a";
Debug.ShouldStop(1);
main.mostCurrent._tnotification.runVoidMethod ("SetInfo3New",main.processBA,(Object)(BA.ObjectToCharSequence("This is the title")),(Object)(BA.ObjectToCharSequence("and this is the body.")),(Object)(BA.ObjectToCharSequence("")),(Object)(RemoteObject.createImmutable((""))),(Object)(main.mostCurrent._gpressđowloadview));
 BA.debugLineNum = 66;BA.debugLine="tNotification.Notify(3)";
Debug.ShouldStop(2);
main.mostCurrent._tnotification.runVoidMethod ("Notify",(Object)(BA.numberCast(int.class, 3)));
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim tNotification As NotificationHelper";
main.mostCurrent._tnotification = RemoteObject.createNew ("com.tummosoft.android.utils.NotificationHelper");
 //BA.debugLineNum = 26;BA.debugLine="Private resHelper As ResourcesHelper";
main.mostCurrent._reshelper = RemoteObject.createNew ("com.tummosoft.Utils.ResourcesHelper");
 //BA.debugLineNum = 27;BA.debugLine="Dim gpressĐowloadView As xRemoteView";
main.mostCurrent._gpressđowloadview = RemoteObject.createNew ("com.tummosoft.android.utils.xRemoteView");
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
 //BA.debugLineNum = 21;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}