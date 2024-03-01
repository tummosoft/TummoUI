package com.tummosoft.vector;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class VectorXMLParser extends DefaultHandler {
    private AndroidVector vector;
    final List<AndroidVector> result;
     private StringBuilder currentValue = new StringBuilder();
    private boolean isVectorTag = false;
    
    public  VectorXMLParser() {
        result = new ArrayList<AndroidVector> ();
    }
    
    public List<AndroidVector> getVectorPath() {
        return result;
    }
    
    @Override
    public void startDocument() {
       
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {        
        currentValue.setLength(0);
        
        if (qName.equals("vector")) { 
            if (isVectorTag == false) {
                vector = new AndroidVector ();
               
                isVectorTag = true;
            }
            
            String wd = attributes.getValue("android:width");        
            String hd = attributes.getValue("android:height");     
            String vpw = attributes.getValue("android:viewortWidth");     
            String vph = attributes.getValue("android:viewportHeight");   
            
            if (wd != null) {
                wd = wd.replaceAll("dp", "");
                float width = Float.parseFloat(wd);
                vector.setWidth(width); }               
            if (hd != null) {
                hd = hd.replaceAll("dp", "");
                float height = Float.parseFloat(hd);
                vector.setHeight(height);}
            if (vpw != null) {
                vpw = vpw.replaceAll("dp", "");                
                float viewportw = Float.parseFloat(vpw);
                vector.setViewportWidth(viewportw);}
              if (vph != null) {
                  vph = vph.replaceAll("dp", "");
                float viewporth = Float.parseFloat(vph);
                vector.setViewportWidth(viewporth);
            }
        } else if (qName.equals("path")) {
            String pData = attributes.getValue("android:pathData"); 
            vector.setPathData(pData); 
             result.add(vector);              
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
         
        if (qName.equals("vector")) {
            isVectorTag = false;
        }
    }
    
   
}
