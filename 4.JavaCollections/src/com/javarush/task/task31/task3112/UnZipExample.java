package com.javarush.task.task31.task3112;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipExample {

    private static final String ZIP_ARCHIVE = "D:\\temp\\files.zip";

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        UnZipExample app = new UnZipExample();
        app.unZip();
    }

    private void unZip() {
        byte[] buffer = new byte[BUFFER_SIZE];

        // Создаем каталог, куда будут распакованы файлы
        final String dstDirectory = destinationDirectory();
        final File dstDir = new File(dstDirectory);
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        try {
            // Получаем содержимое ZIP архива
            final ZipInputStream zipInputStream = new ZipInputStream(
                    new FileInputStream(UnZipExample.ZIP_ARCHIVE));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            String nextFileName;
            while (zipEntry != null) {
                nextFileName = zipEntry.getName();
                File nextFile = new File(dstDirectory + File.separator
                        + nextFileName);
                System.out.println("Распаковываем: "
                        + nextFile.getAbsolutePath());
                // Если мы имеем дело с каталогом - надо его создать. Если
                // этого не сделать, то не будут созданы пустые каталоги
                // архива
                if (zipEntry.isDirectory()) {
                    nextFile.mkdir();
                } else {
                    // Создаем все родительские каталоги
                    new File(nextFile.getParent()).mkdirs();
                    // Записываем содержимое файла
                    try (FileOutputStream fos
                                 = new FileOutputStream(nextFile)) {
                        int length;
                        while((length = zipInputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(UnZipExample.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private String destinationDirectory() {
        return UnZipExample.ZIP_ARCHIVE.substring(0, UnZipExample.ZIP_ARCHIVE.lastIndexOf("."));
    }
}