package com.bank.atm.backend.collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Class CollectionsUtil
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 12/13/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class CollectionsUtil {
    public static void clearFileContents(String fileName) throws IOException {
        Path path = FileSystems.getDefault().getPath(fileName);
        Files.newInputStream(path, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Deletes the file identified by fileName
     * @param fileName the name of the file to delete
     */
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }
}
