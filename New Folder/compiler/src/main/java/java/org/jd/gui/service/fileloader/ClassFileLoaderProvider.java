/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use, 
 * copy and modify the code freely for non-commercial purposes.
 */

package java.org.jd.gui.service.fileloader;

import org.jd.gui.api.API;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.jd.core.v1.service.converter.classfiletojavasyntax.util.ExceptionUtil;
import org.objectweb.asm.ClassReader;

public class ClassFileLoaderProvider extends AbstractTypeFileLoaderProvider {
    protected static final String[] EXTENSIONS = { "class" };

    public String[] getExtensions() { return EXTENSIONS; }
    public String getDescription() { return "Class files (*.class)"; }

    public boolean accept(API api, File file) {
        return file.exists() && file.isFile() && file.canRead() && file.getName().toLowerCase().endsWith(".class");
    }

    public boolean load(API api, File file) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            ClassReader classReader = new ClassReader(bis);
            String pathInFile = classReader.

            return load(api, file, pathInFile);
        } catch (IOException e) {
            assert ExceptionUtil.printStackTrace(e);
            return false;
        }
    }
}
