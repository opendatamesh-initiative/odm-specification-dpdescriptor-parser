package org.opendatamesh.dpds.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomFileUtils {

    // === GET =========================================================================================================

    private static Path getFileInDirPath(File directory, String fileInDirectoryName) {
        String directoryAbsolutePath = directory.getAbsolutePath();
        return Paths.get(directoryAbsolutePath, fileInDirectoryName);
    }


    // === EXISTS ======================================================================================================

    public static Boolean existsAsFileInDirectory(File directoryToCheck, String fileNameToCheckInDirectory) {
        Path fileInDirPath = getFileInDirPath(directoryToCheck, fileNameToCheckInDirectory);
        if (exists(fileInDirPath) && Files.isRegularFile(fileInDirPath))
            return true;
        else
            return false;
    }

    public static Boolean existsAsDirectoryInDirectory(File directoryToCheck, String directoryNameToCheckInDirectory) {
        Path dirInDirPath = getFileInDirPath(directoryToCheck, directoryNameToCheckInDirectory);
        if (exists(dirInDirPath) && Files.isDirectory(dirInDirPath))
            return true;
        else
            return false;
    }

    private static Boolean exists(Path filePathToCheck) {
        return Files.exists(filePathToCheck);
    }


    // === BOH =========================================================================================================

    public static void removeDirectory(File directory) {
        try {
            FileUtils.deleteDirectory(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cleanDirectoryExceptOneDir(File dirToClean, String subdirectoryToKeep) {
        for (File file : dirToClean.listFiles()) {
            if (!file.getName().equals(subdirectoryToKeep)) {
                if (file.isDirectory()) {
                    removeDirectory(file);
                } else {
                    file.delete();
                }
            }
        }

    }

    public static void copyDirectory(File sourceDirectory, File destinationDirectory) {
        try {
            FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

