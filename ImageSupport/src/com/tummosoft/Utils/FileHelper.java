package com.tummosoft.Utils;

import android.annotation.SuppressLint;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@BA.ShortName("FileHelper")
public class FileHelper {
     private static final String LINE_SEP = System.getProperty("line.separator");
        
    public static File getFileByPath(String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }
    
    public static boolean isFileExists(String filePath) {
        return isFileExists2(getFileByPath(filePath));
    }
    
    private static boolean isFileExists2(File file) {
        return file != null && file.exists();
    }
    
    public static boolean rename(String filePath, String newName) {
        return rename2(getFileByPath(filePath), newName);
    }
    
    private static boolean rename2(File file, String newName) {        
        if (file == null) return false;        
        if (!file.exists()) return false;        
        if (isSpace(newName)) return false;        
        if (newName.equals(file.getName())) return true;
        File newFile = new File(file.getParent() + File.separator + newName);        
        return !newFile.exists()
                && file.renameTo(newFile);
    }
    
    public static boolean isDir(String dirPath) {
        return isDir2(getFileByPath(dirPath));
    }
    
    private static boolean isDir2(File file) {
        return isFileExists2(file) && file.isDirectory();
    }
    
    public static boolean isFile(String filePath) {
        return isFile2(getFileByPath(filePath));
    }
    
    private static boolean isFile2(File file) {
        return isFileExists2(file) && file.isFile();
    }
    
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir2(getFileByPath(dirPath));
    }
    
    @Hide
    public static boolean createOrExistsDir2(File file) {        
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }
    
    public static boolean createOrExistsFile(String filePath) {
        return createOrExistsFile2(getFileByPath(filePath));
    }
    
    @Hide
    public static boolean createOrExistsFile2(File file) {
        if (file == null) return false;       
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir2(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    private static boolean createFileByDeleteOldFile(File file) {
        if (file == null) return false;        
        if (file.exists() && file.isFile() && !file.delete()) return false;        
        if (!createOrExistsDir2(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyOrMoveDir(String srcDirPath, String destDirPath, boolean isMove) throws IOException {
        return copyOrMoveDir2(getFileByPath(srcDirPath), getFileByPath(destDirPath), isMove);
    }
    
    private static boolean copyOrMoveDir2(File srcDir, File destDir, boolean isMove) throws IOException {
        if (srcDir == null || destDir == null) return false;
        String srcPath = srcDir.getPath() + File.separator;
        String destPath = destDir.getPath() + File.separator;
        if (destPath.contains(srcPath)) return false;
        if (!srcDir.exists() || !srcDir.isDirectory()) return false;        
        if (!createOrExistsDir2(destDir)) return false;
        File[] files = srcDir.listFiles();
        for (File file : files) {
            File oneDestFile = new File(destPath + file.getName());
            if (file.isFile()) {                
                if (!copyOrMoveFile2(file, oneDestFile, isMove)) return false;
            } else if (file.isDirectory()) {                
                if (!copyOrMoveDir2(file, oneDestFile, isMove)) return false;
            }
        }
        return !isMove || deleteDir2(srcDir);
    }
    
    private static boolean copyOrMoveFile(String srcFilePath, String destFilePath, boolean isMove) throws IOException {
        return copyOrMoveFile2(getFileByPath(srcFilePath), getFileByPath(destFilePath), isMove);
    }
        
    private static boolean copyOrMoveFile2(File srcFile, File destFile, boolean isMove) throws IOException {
        if (srcFile == null || destFile == null) return false;        
        if (!srcFile.exists() || !srcFile.isFile()) return false;        
        if (destFile.exists() && destFile.isFile()) return false;        
        if (!createOrExistsDir2(destFile.getParentFile())) return false;
        try {
            return writeFileFromIS(destFile, new FileInputStream(srcFile), false)
                    && !(isMove && !deleteFile2(srcFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean writeFileFromIS(File file, final InputStream is, boolean append) throws IOException {
        if (!createOrExistsFile2(file) || is == null) return false;
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, append));
            byte data[] = new byte[1024];
            int len;
            while ((len = is.read(data, 0, 1024)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
           is.close();
           os.close();
        }
    }
    
    public static boolean copyDir(String srcDirPath, String destDirPath) throws IOException {
        return copyDir2(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

  
    private static boolean copyDir2(File srcDir, File destDir) throws IOException {
        return copyOrMoveDir2(srcDir, destDir, false);
    }
   
    public static boolean copyFile(String srcFilePath, String destFilePath) throws IOException {
        return copyFile2(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }
    
    private static boolean copyFile2(File srcFile, File destFile) throws IOException {
        return copyOrMoveFile2(srcFile, destFile, false);
    }

  
    public static boolean moveDir(String srcDirPath, String destDirPath) throws IOException {
        return moveDir2(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    private static boolean moveDir2(File srcDir, File destDir) throws IOException {
        return copyOrMoveDir2(srcDir, destDir, true);
    }
    
    public static boolean moveFile(String srcFilePath, String destFilePath) throws IOException {
        return moveFile2(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }
    
   private static boolean moveFile2(File srcFile, File destFile) throws IOException {
        return copyOrMoveFile2(srcFile, destFile, true);
    }
    
    public static boolean deleteDir(String dirPath) {
        return deleteDir2(getFileByPath(dirPath));
    }
    
    private static boolean deleteDir2(File dir) {
        if (dir == null) return false;        
        if (!dir.exists()) return true;        
        if (!dir.isDirectory()) return false;        
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!deleteFile2(file)) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir2(file)) return false;
                }
            }
        }
        return dir.delete();
    }
    
    public static boolean deleteFile(String srcFilePath) {
        return deleteFile2(getFileByPath(srcFilePath));
    }
       
    private static boolean deleteFile2(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }
    
    public static boolean deleteFilesInDir(String dirPath) {
        return deleteFilesInDir2(getFileByPath(dirPath));
    }
       
    private static boolean deleteFilesInDir2(File dir) {
        if (dir == null) return false;
        if (!dir.exists()) return true;        
        if (!dir.isDirectory()) return false;        
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!deleteFile2(file)) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir2(file)) return false;
                }
            }
        }
        return true;
    }
     
    public static anywheresoftware.b4a.objects.collections.List listFilesInDirWithFilter3(File dir, FilenameFilter filter, boolean isRecursive) {
        if (isRecursive) return listFilesInDirWithFilter2(dir, filter);
        if (dir == null || !isDir2(dir)) return null;
        anywheresoftware.b4a.objects.collections.List list = new anywheresoftware.b4a.objects.collections.List();
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (filter.accept(file.getParentFile(), file.getName())) {
                    list.Add(file);;
                }
            }
        }
        return list;
    }
    
    public static  anywheresoftware.b4a.objects.collections.List listFilesInDirWithFilter(String dirPath, FilenameFilter filter) {
        return listFilesInDirWithFilter2(getFileByPath(dirPath), filter);
    }
    
    public static anywheresoftware.b4a.objects.collections.List listFilesInDirWithFilter2(File dir, FilenameFilter filter) {
        if (dir == null || !isDir2(dir)) return null;
        anywheresoftware.b4a.objects.collections.List list = new anywheresoftware.b4a.objects.collections.List();
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (filter.accept(file.getParentFile(), file.getName())) {
                    list.Add(file);
                }
                if (file.isDirectory()) {
                    list.AddAll(listFilesInDirWithFilter2(file, filter));
                }
            }
        }
        return list;
    }

    public static anywheresoftware.b4a.objects.collections.List searchFileInDir(String dirPath, String fileName) {
        anywheresoftware.b4a.objects.collections.List lstFile = new anywheresoftware.b4a.objects.collections.List();
        lstFile.Initialize();        
        return searchFileInDir2(getFileByPath(dirPath), fileName);
    }

      
    private static anywheresoftware.b4a.objects.collections.List searchFileInDir2(File dir, String fileName) {
        if (dir == null || !isDir2(dir)) return null;
        anywheresoftware.b4a.objects.collections.List list = new anywheresoftware.b4a.objects.collections.List();
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.getName().toUpperCase().equals(fileName.toUpperCase())) {
                    list.Add(file);
                }
                if (file.isDirectory()) {
                    anywheresoftware.b4a.objects.collections.List ll = searchFileInDir2(file, fileName);
                    list.AddAll(ll);
                }
            }
        }
        return list;
    }
    
    public static long getFileLastModified(String filePath) {
        return getFileLastModified2(getFileByPath(filePath));
    }
    
    private static long getFileLastModified2(File file) {
        if (file == null) return -1;
        return file.lastModified();
    }
    
    public static String getFileCharsetSimple(String filePath) throws IOException {
        return getFileCharsetSimple2(getFileByPath(filePath));
    }
    
    public static String getFileCharsetSimple2(File file) throws IOException {
        int p = 0;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            p = (is.read() << 8) + is.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        switch (p) {
            case 0xefbb:
                return "UTF-8";
            case 0xfffe:
                return "Unicode";
            case 0xfeff:
                return "UTF-16BE";
            default:
                return "GBK";
        }
    }
    
    public static int getFileLines(String filePath) throws IOException {
        return getFileLines2(getFileByPath(filePath));
    }
    
    private static int getFileLines2(File file) throws IOException {
        int count = 1;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int readChars;
            if (LINE_SEP.endsWith("\n")) {
                while ((readChars = is.read(buffer, 0, 1024)) != -1) {
                    for (int i = 0; i < readChars; ++i) {
                        if (buffer[i] == '\n') ++count;
                    }
                }
            } else {
                while ((readChars = is.read(buffer, 0, 1024)) != -1) {
                    for (int i = 0; i < readChars; ++i) {
                        if (buffer[i] == '\r') ++count;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           is.close();
        }
        return count;
    }
    
    public static String getDirSize(String dirPath) {
        return getDirSize2(getFileByPath(dirPath));
    }
 
    private static String getDirSize2(File dir) {
        long len = getDirLength2(dir);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }
    
    public static String getFileSize(String filePath) {
        return getFileSize2(getFileByPath(filePath));
    }
    
    private static String getFileSize2(File file) {
        long len = getFileLength2(file);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }
       
    public static long getDirLength(String dirPath) {
        return getDirLength2(getFileByPath(dirPath));
    }
    
    static private long getDirLength2(File dir) {
        if (!isDir2(dir)) return -1;
        long len = 0;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    len += getDirLength2(file);
                } else {
                    len += file.length();
                }
            }
        }
        return len;
    }
    
    public static long getFileLength(String filePath) {
        return getFileLength2(getFileByPath(filePath));
    }
    
    private static long getFileLength2(File file) {
        if (!isFile2(file)) return -1;
        return file.length();
    }
       
    public static byte[] getFileMD52(String filePath) throws IOException {
        File file = isSpace(filePath) ? null : new File(filePath);
        return getFileMD5(file);
    }
       
    public static byte[] getFileMD5(File file) throws IOException {
        if (file == null) return null;
        DigestInputStream dis = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            dis = new DigestInputStream(fis, md);
            byte[] buffer = new byte[1024 * 256];
            while (true) {
                if (!(dis.read(buffer) > 0)) break;
            }
            md = dis.getMessageDigest();
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {            
            dis.close();
        }
        return null;
    }
    
    public static String getDirName1(File file) {
        if (file == null) return null;
        return getDirName(file.getPath());
    }
    
    public static String getDirName(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? "" : filePath.substring(0, lastSep + 1);
    }
    
    public static String getFileName1(File file) {
        if (file == null) return null;
        return getFileName(file.getPath());
    }
    
    public static String getFileName(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
    }
    
    public static String getFileNameNoExtension1(File file) {
        if (file == null) return null;
        return getFileNameNoExtension(file.getPath());
    }
    
    public static String getFileNameNoExtension(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastSep == -1) {
            return (lastPoi == -1 ? filePath : filePath.substring(0, lastPoi));
        }
        if (lastPoi == -1 || lastSep > lastPoi) {
            return filePath.substring(lastSep + 1);
        }
        return filePath.substring(lastSep + 1, lastPoi);
    }

    public static String getFileExtension1(File file) {
        if (file == null) return null;
        return getFileExtension(file.getPath());
    }
    
    public static String getFileExtension(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
    }

    ///////////////////////////////////////////////////////////////////////////
    // copy from ConvertUtils
    ///////////////////////////////////////////////////////////////////////////

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   
    private static String bytes2HexString(byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }
    
    @SuppressLint("DefaultLocale")
    private static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < MemoryConstants.KB) {
            return String.format("%.3fB", (double) byteNum + 0.0005);
        } else if (byteNum < MemoryConstants.MB) {
            return String.format("%.3fKB", (double) byteNum / MemoryConstants.KB + 0.0005);
        } else if (byteNum < MemoryConstants.GB) {
            return String.format("%.3fMB", (double) byteNum / MemoryConstants.MB + 0.0005);
        } else {
            return String.format("%.3fGB", (double) byteNum / MemoryConstants.GB + 0.0005);
        }
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
