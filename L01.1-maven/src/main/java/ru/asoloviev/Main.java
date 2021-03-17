package ru.asoloviev;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = FileUtils.getFile(Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("fileTest.txt"))
                .getPath());
        File tempDir = FileUtils.getTempDirectory();
        FileUtils.copyFileToDirectory(file, tempDir);
        File newTempFile = FileUtils.getFile(tempDir, file.getName());
        String data = FileUtils.readFileToString(newTempFile,
                Charset.defaultCharset());
    }
}
