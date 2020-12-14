package com.bank.atm.backend.collections;

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
}
