package com.tummosoft.Utils;

import anywheresoftware.b4a.BA.Hide;
import static com.tummosoft.Utils.MemoryConstants.KB;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public final class ZipHelper {
      
    public static boolean zipFiles(anywheresoftware.b4a.objects.collections.List resFiles, String zipFilePath, String comment)
            throws IOException {            
            Collection<File> _resFiles = null;
            for (int i=0; i < resFiles.getSize(); i++) {
                
                _resFiles.add(FileHelper.getFileByPath((String)resFiles.Get(i)));
            }
        return zipFiles2(_resFiles,FileHelper.getFileByPath(zipFilePath), comment);
    }
    
    @Hide
    public static boolean zipFiles3(Collection<File> resFiles, File zipFile)
            throws IOException {
        return zipFiles2(resFiles, zipFile, null);
    }
       
    @Hide
    public static boolean zipFiles2(Collection<File> resFiles, File zipFile, String comment)
            throws IOException {
        if (resFiles == null || zipFile == null) return false;
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for (File resFile : resFiles) {
                if (!zipFile2(resFile, "", zos, comment)) return false;
            }
            return true;
        } finally {
            if (zos != null) {
                zos.finish();
                zos.close();
            }
        }
    }
 
    public static boolean zipFile(String resFilePath, String zipFilePath)
            throws IOException {
        return zipFile5(resFilePath, zipFilePath, null);
    }
    
    @Hide
    public static boolean zipFile5(String resFilePath, String zipFilePath, String comment)
            throws IOException {
        return zipFile3(FileHelper.getFileByPath(resFilePath), FileHelper.getFileByPath(zipFilePath), comment);
    }
    
    @Hide
    public static boolean zipFile4(File resFile, File zipFile)
            throws IOException {
        return zipFile3(resFile, zipFile, null);
    }

    @Hide
    public static boolean zipFile3(File resFile, File zipFile, String comment)
            throws IOException {
        if (resFile == null || zipFile == null) return false;
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            return zipFile2(resFile, "", zos, comment);
        } finally {
            if (zos != null) {
                zos.close();
            }
        }
    }
        
    private static boolean zipFile2(File resFile, String rootPath, ZipOutputStream zos, String comment)
            throws IOException {
        rootPath = rootPath + (isSpace(rootPath) ? "" : File.separator) + resFile.getName();
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();            
            if (fileList == null || fileList.length <= 0) {
                ZipEntry entry = new ZipEntry(rootPath + '/');
                if (!comment.isEmpty()) entry.setComment(comment);
                zos.putNextEntry(entry);
                zos.closeEntry();
            } else {
                for (File file : fileList) {                    
                    if (!zipFile2(file, rootPath, zos, comment)) return false;
                }
            }
        } else {
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(resFile));
                ZipEntry entry = new ZipEntry(rootPath);
                if (!comment.isEmpty()) entry.setComment(comment);
                zos.putNextEntry(entry);
                byte buffer[] = new byte[KB];
                int len;
                while ((len = is.read(buffer, 0, KB)) != -1) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            } finally {
                is.close();
            }
        }
        return true;
    }
     
    public static boolean unzipFiles(Collection<File> zipFiles, String destDirPath)
            throws IOException {
        return unzipFiles2(zipFiles, FileHelper.getFileByPath(destDirPath));
    }
    
    public static boolean unzipFiles2(Collection<File> zipFiles, File destDir)
            throws IOException {
        if (zipFiles == null || destDir == null) return false;
        for (File zipFile : zipFiles) {
            if (!unzipFile2(zipFile, destDir)) return false;
        }
        return true;
    }
    
    public static boolean unzipFile(String zipFilePath, String destDirPath)
            throws IOException {
        return unzipFile2(FileHelper.getFileByPath(zipFilePath), FileHelper.getFileByPath(destDirPath));
    }
      
    @Hide
    public static boolean unzipFile2(File zipFile, File destDir)
            throws IOException {
        return unzipFileByKeyword(zipFile, destDir, null) != null;
    }
       
    public static anywheresoftware.b4a.objects.collections.List unzipFileByKeyword(String zipFilePath, String destDirPath, String keyword)
            throws IOException {
        return unzipFileByKeyword(FileHelper.getFileByPath(zipFilePath),
                FileHelper.getFileByPath(destDirPath), keyword);
    }
    
    @Hide
    public static anywheresoftware.b4a.objects.collections.List unzipFileByKeyword(File zipFile, File destDir, String keyword)
            throws IOException {
        if (zipFile == null || destDir == null) return null;
        anywheresoftware.b4a.objects.collections.List files = new anywheresoftware.b4a.objects.collections.List();
        ZipFile zf = new ZipFile(zipFile);
        Enumeration<?> entries = zf.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            String entryName = entry.getName();
            if (keyword.isEmpty() || FileHelper.getFileName(entryName).toLowerCase().contains(keyword.toLowerCase())) {
                String filePath = destDir + File.separator + entryName;
                File file = new File(filePath);
                files.Add(file);
                if (entry.isDirectory()) {
                    if (!FileHelper.createOrExistsDir2(file)) return null;
                } else {
                    if (!FileHelper.createOrExistsFile2(file)) return null;
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = new BufferedInputStream(zf.getInputStream(entry));
                        out = new BufferedOutputStream(new FileOutputStream(file));
                        byte buffer[] = new byte[KB];
                        int len;
                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }
                    } finally {
                       in.close();
                       out.close();
                    }
                }
            }
        }
        return files;
    }
    
    public static anywheresoftware.b4a.objects.collections.List getFilesPath(String zipFilePath)
            throws IOException {
        return getFilesPath2(FileHelper.getFileByPath(zipFilePath));
    }
       
    private static anywheresoftware.b4a.objects.collections.List getFilesPath2(File zipFile)
            throws IOException {
        if (zipFile == null) return null;
        anywheresoftware.b4a.objects.collections.List paths = new anywheresoftware.b4a.objects.collections.List();
        Enumeration<?> entries = getEntries2(zipFile);
        while (entries.hasMoreElements()) {
            paths.Add(((ZipEntry) entries.nextElement()).getName());
        }
        return paths;
    }
    
    private static List<String> getComments(String zipFilePath)
            throws IOException {
        return getComments(FileHelper.getFileByPath(zipFilePath));
    }
    
    private static List<String> getComments(File zipFile)
            throws IOException {
        if (zipFile == null) return null;
        List<String> comments = new ArrayList<>();
        Enumeration<?> entries = getEntries2(zipFile);
        while (entries.hasMoreElements()) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            comments.add(entry.getComment());
        }
        return comments;
    }
       
    private static Enumeration<?> getEntries(String zipFilePath)
            throws IOException {
        return getEntries2(FileHelper.getFileByPath(zipFilePath));
    }
    
    private static Enumeration<?> getEntries2(File zipFile)
            throws IOException {
        if (zipFile == null) return null;
        return new ZipFile(zipFile).entries();
    }

    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
