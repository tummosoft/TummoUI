package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layout{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Layout/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="pnlBottomSheet.Width = 100%x"[Layout/General script]
views.get("pnlbottomsheet").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 5;BA.debugLine="pnlBottomSheet.Height = 30%y"[Layout/General script]
views.get("pnlbottomsheet").vw.setHeight((int)((30d / 100 * height)));
//BA.debugLineNum = 6;BA.debugLine="pnlBottomSheet.Top =  -2000"[Layout/General script]
views.get("pnlbottomsheet").vw.setTop((int)(0-2000d));

}
}