package com.tummosoft.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import anywheresoftware.b4a.BA;

/**
 * <code>LayoutLoader</code> helps you load compiled layouts from any source.
 *
 * <p>
 *     The layout can be loaded from either an {@link InputStream} or raw bytes.
 * </p>
 *
 * <p>
 *     A typical application using {@link LayoutLoader} will have code similar to that given below:
 * </p>
 *
 * <pre>
 * class MyApplication extends Application {
 *     private LayoutLoader layoutLoader;
 *     private LayoutCache layoutCache;
 *
 *     protected void onCreate() {
 *         layoutLoader = new LayoutLoader().initialize();
 *         layoutCache = new LayoutCache();
 *     }
 *
 *     public LayoutLoader getLayoutLoader() {
 *         return layoutLoader;
 *     }
 *
 *     public LayoutCache getLayoutCache() {
 *         return layoutCache;
 *     }
 * }
 *
 * class LayoutCache {
 *     private LruCache&lt;String, byte[]&gt; cache = new LruCache&lt;&gt;();
 *     private Map&lt;String, Boolean&gt; fetchedURLs = new HashMap&lt;&gt;();
 *
 *     public void load(String url) {
 *         //load from network
 *         //validate signature
 *         //add to cache
 *         if(data != null) {
 *             cache.put(ur, data);
 *         }
 *         fetchedURLs.put(url, data != null);
 *     }
 *
 *     public boolean shouldFetch(String url) {
 *         return !fetchedURLs.containsKey(url);
 *     }
 *
 *     public boolean hasLayout(String url) {
 *         Boolean isDataValid = fetchedURLs.get(url);
 *         if(isDataValid == null) {
 *             return false;
 *         }
 *         return isDataValid;
 *     }
 *
 *     public byte[] get(String url) {
 *         return cache.get(url);
 *     }
 * }
 *
 * class SomeActivity extends Activity {
 *     protected void onCreate(Bundle savedInstanceState) {
 *         MyApplication app = (MyApplication) getApplicationContext();
 *         LayoutCache cache = app.getLayoutCache();
 *         String url = getFromSomeConfig(this); //load app config, a/b test config etc
 *
 *         View root = null;
 *         if(cache.shouldFetch(url)) {
 *             cache.load(url);
 *         } else if(cache.hasLayout(url) {
 *             root = app.getLayoutLoader().load(cache.get(url), this, null, false);
 *         }
 *
 *         //If the layout was loaded dynamically, great!
 *         // else, use the bundled layout.
 *         if(root != null) {
 *             setContentView(root);
 *         } else {
 *             setContentView(R.layout.activity_some);
 *         }
 *     }
 * }
 *
 * </pre>
 *
 * @author Gaurav Vaish
 * @since v1.0
 * @see #load(InputStream, Context, ViewGroup, boolean)
 * @see #load(byte[], Context, ViewGroup, boolean)
 */

@BA.ShortName("LayoutLoader")
public class LayoutLoader {

    public static final String TAG = "LayoutLoader";
    private Constructor<?> xmlBlockCtor;
    private Method newParserMethod;
    private boolean ready;

   
    public BA _ba;
    
    public LayoutLoader initialize(final BA ba) {
        _ba = ba;
        if(!ready) {
            synchronized(this) {
                if(!ready) {
                    initializeImpl();
                    ready = true;
                }
            }
        }
        return this;
    }
    
    public LayoutLoader cleanup() {
        if(ready) {
            synchronized(this) {
                if(ready) {
                    xmlBlockCtor = null;
                    newParserMethod = null;
                    ready = false;
                }
            }
        }
        return this;
    }

    protected void initializeImpl() {
        try {
            Class<?> cls = Class.forName("android.content.res.XmlBlock");
            xmlBlockCtor = cls.getDeclaredConstructor(byte[].class);
            xmlBlockCtor.setAccessible(true);

            newParserMethod = cls.getDeclaredMethod("newParser");
            newParserMethod.setAccessible(true);
        } catch(RuntimeException e) {
            Log.e(TAG, "Failed initializing loader", e);
        } catch(Exception e) {
            Log.e(TAG, "Failed initializing loader", e);
        }
    }
   
    public View load(InputStream input, ViewGroup root, boolean attachToRoot) {
        View rv = null;
        Context context = _ba.context;
        if(ready && xmlBlockCtor != null && newParserMethod != null) {
            byte[] data = readAll(input);
            rv = load(data, root, attachToRoot);
        }

        return rv;
    }

     public ImageButton getImageButton(InputStream input, ViewGroup root, boolean attachToRoot) {
      
        Context context = _ba.context;
          ImageButton rv = new ImageButton(context);
        if(ready && xmlBlockCtor != null && newParserMethod != null) {
            byte[] data = readAll(input);
            rv = (ImageButton)load(data, root, attachToRoot);
        }

        return rv;
    }
   
    public View load(byte[] data, ViewGroup root, boolean attachToRoot) {        
         Context context = _ba.context;
         View rv = new View(context);
        if(ready && xmlBlockCtor != null && newParserMethod != null && data != null) {
            try {
                Object xmlBlock = xmlBlockCtor.newInstance(data);
                XmlResourceParser parser = (XmlResourceParser) newParserMethod.invoke(xmlBlock);
                
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                 rv = layoutInflater.inflate(parser, root, attachToRoot);
                 
                //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //rv = inflater.inflate(parser, root, attachToRoot);
                
                //rv.buildLayer();
            } catch(RuntimeException e) {
                Log.e(TAG, "Failed loading layout", e);
            } catch(Exception e) {
                Log.e(TAG, "Failed loading layout", e);
            }
        }
        return rv;
    }

    private byte[] readAll(InputStream input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        byte[] rv = null;

        try {
            read = input.read(buffer, 0, buffer.length);
            while(read >= 0) {
                baos.write(buffer, 0, read);
                read = input.read(buffer, 0, buffer.length);
            }
            rv = baos.toByteArray();
        } catch(IOException e) {
            Log.e(TAG, "Failed reading layout content", e);
        }

        return rv;
    }
}
