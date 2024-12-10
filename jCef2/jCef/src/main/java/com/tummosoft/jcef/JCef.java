package com.tummosoft.jcef;

import anywheresoftware.b4a.BA;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import me.friwi.jcefmaven.*;
import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.handler.CefDisplayHandlerAdapter;
import org.cef.handler.CefFocusHandlerAdapter;

@BA.ShortName("JCef")
public class JCef {
    private String eventName;
    private static final long serialVersionUID = -5570653778104813836L;    
    private CefApp cefApp_;
    private CefClient client_;
    private Component browerUI_;
    private CefBrowser browser_;    
    private boolean browserFocus_ = true;
    private boolean _useOSR;
    
    public void initialize(final BA ba, String event) {
       eventName = event.toLowerCase();
    }
    
    public void BuildCef(boolean useOSR) {
         // (0) Initialize CEF using the maven loader
        CefAppBuilder builder = new CefAppBuilder();
        // windowless_rendering_enabled must be set to false if not wanted.
        _useOSR = useOSR;
        builder.getCefSettings().windowless_rendering_enabled = _useOSR;
        // USE builder.setAppHandler INSTEAD OF CefApp.addAppHandler!
        // Fixes compatibility issues with MacOSX
        builder.setAppHandler(new MavenCefAppHandlerAdapter() {
            @Override
            public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                // Shutdown the app if the native CEF part is terminated
                if (state == CefAppState.TERMINATED) System.exit(0);
            }
        });
        
        try {
            cefApp_ = builder.build();
        } catch (IOException | UnsupportedPlatformException | InterruptedException | CefInitializationException e) {        
            e.printStackTrace();
        }

    }
    
    private void createClient() {
         client_ = cefApp_.createClient();
        CefMessageRouter msgRouter = CefMessageRouter.create();
        client_.addMessageRouter(msgRouter);        
        browerUI_ = browser_.getUIComponent();
        

       
    }
    
    public void LoadURL(String startURL, boolean isTransparent) {
        browser_ = client_.createBrowser(startURL, _useOSR, isTransparent);
        browser_.loadURL(startURL);
        
        client_.addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
               //address_.setText(url);
            }
        });

//        // Clear focus from the browser when the address field gains focus.
//        address_.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (!browserFocus_) return;
//                browserFocus_ = false;
//                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
//                address_.requestFocus();
//            }
//        });

        // Clear focus from the address field when the browser gains focus.
        client_.addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onGotFocus(CefBrowser browser) {
                if (browserFocus_) return;
                browserFocus_ = true;
                //KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
               // browser.setFocus(true);
            }

            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                browserFocus_ = false;
            }
        });

     

       
    }
}
