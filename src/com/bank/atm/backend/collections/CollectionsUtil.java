package com.bank.atm.backend.collections;

import java.io.File;

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
    /**
     * Deletes the file identified by fileName
     * @param fileName the name of the file to delete
     */
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }
}
