package com.tummosoft.services;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.streams.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Links {
    private anywheresoftware.b4a.objects.collections.List lst;
    private String saveto;
    
    public Links(String path, String saveTo) {
        lst = new anywheresoftware.b4a.objects.collections.List();
        saveto = saveTo;
        try {
            lst.Initialize();
            lst = File.ReadList(path, "");
        } catch (IOException ex) {
            BA.LogError(ex.getMessage());
        }                
    }
    
    public String getDownloadFolder() {
        return saveto;
    }
    
    public anywheresoftware.b4a.objects.collections.List getLinks() {
        return lst;
    }
    
}
