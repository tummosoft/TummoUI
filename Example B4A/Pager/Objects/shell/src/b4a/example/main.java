
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class main implements IRemote{
	public static main mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public main() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("main"), "b4a.example.main");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, main.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _xui = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
public static RemoteObject _pager = RemoteObject.declareNull("com.tummosoft.android.views.xPager");
public static RemoteObject _pagerfactory = RemoteObject.declareNull("com.tummosoft.android.views.xPagerFactory");
public static RemoteObject _currentpage = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
public static RemoteObject _mcurpage = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
public static RemoteObject _mnextpage = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
public static RemoteObject _mcurpagecanvas = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper");
public static RemoteObject _mnextpagecanvas = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper");
public static RemoteObject _pages = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _musics = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _screenwidth = RemoteObject.createImmutable(0);
public static RemoteObject _screenheight = RemoteObject.createImmutable(0);
public static RemoteObject _currentindex = RemoteObject.createImmutable(0);
public static RemoteObject _lastbitmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper");
public static RemoteObject _lastindex = RemoteObject.createImmutable(0);
public static RemoteObject _selectindex = RemoteObject.createImmutable(0);
public static RemoteObject _count = RemoteObject.createImmutable(0);
public static RemoteObject _lastx = RemoteObject.createImmutable(0);
public static RemoteObject _lasty = RemoteObject.createImmutable(0);
public static b4a.example.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"count",main._count,"currentIndex",main._currentindex,"currentPage",main.mostCurrent._currentpage,"lastBitmap",main.mostCurrent._lastbitmap,"lastIndex",main._lastindex,"lastX",main._lastx,"lastY",main._lasty,"mCurPage",main.mostCurrent._mcurpage,"mCurPageCanvas",main.mostCurrent._mcurpagecanvas,"mNextPage",main.mostCurrent._mnextpage,"mNextPageCanvas",main.mostCurrent._mnextpagecanvas,"musics",main.mostCurrent._musics,"pager",main.mostCurrent._pager,"pagerFactory",main.mostCurrent._pagerfactory,"pages",main.mostCurrent._pages,"screenHeight",main._screenheight,"screenWidth",main._screenwidth,"selectIndex",main._selectindex,"Starter",Debug.moduleToString(b4a.example.starter.class),"xui",main._xui};
}
}