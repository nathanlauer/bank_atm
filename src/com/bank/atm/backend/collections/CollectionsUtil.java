package com.bank.atm.backend.collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new IOException(e.getMessage());
        }
        writer.print("");
        writer.close();
    }
}
