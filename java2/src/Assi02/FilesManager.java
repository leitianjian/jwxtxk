package Assi02;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class FilesManager {
    private long bigFileSize = 1000000;
    private String directory;
    private File targetFile;

    public FilesManager(int operationCode, String directory) {
        if (fileValidator(directory)) {
            this.directory = directory;
            this.targetFile = new File(this.directory);
        } else {
            System.exit(2);
        }

        switch (operationCode) {
            case 1:
                getFileNumber();
                break;
            case 2:
                calSize();
                break;
            case 3:
                findBigFile();
                break;
            case 4:
                extCategory();
                break;
            default:
                System.out.println("Unknown operation code.");
                System.exit(1);
        }
    }

    public void getFileNumber() {
        System.out.println(calFileNum(targetFile));
    }

    public void calSize() {
        System.out.println((calSize(targetFile) >> 10) + "k");
    }

    public void findBigFile() {
        Stack<File> stack = new Stack<>();

        stack.push(targetFile);
        while (!stack.empty()) {
            File temp = stack.pop();
            File[] files = temp.listFiles();

            if (files == null) {
                System.out.println("I/O exception may occur, cannot count the directory");
                System.exit(3);
            }

            for (File x : files) {
                if (x.isDirectory()) {
                    stack.push(x);
                } else {
                    if (x.length() > bigFileSize) {
                        System.out.println(x.toPath().toString());
                    }
                }
            }
        }
    }


    public void extCategory() {
        Stack<File> stack = new Stack<>();
        Map<String, Integer> category = new HashMap<>();

        stack.push(targetFile);
        while (!stack.empty()) {
            File temp = stack.pop();
            File[] files = temp.listFiles();

            if (files == null) {
                System.out.println("I/O exception may occur, cannot count the directory");
                System.exit(3);
            }
            for (File x : files) {
                if (x.isDirectory()) {
                    stack.push(x);
                } else {
                    String extension = "";
                    String fileName = x.getName();

                    int i = fileName.lastIndexOf('.');
                    int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                    if (i > p) {
                        extension = fileName.substring(i);
                    }

                    if (category.containsKey(extension)) {
                        category.put(extension, category.get(extension) + 1);
                    } else {
                        category.put(extension, 1);
                    }
                }
            }
        }
        category.entrySet().stream().map(i -> i.getKey() + ", " + i.getValue()).forEach(System.out::println);
    }

    private long calSize(File directory) {
        File[] files = directory.listFiles();
        long totalSize = 0;
        if (files == null) {
            System.out.println("I/O exception may occur, cannot count the directory");
            System.exit(3);
        }
        for (File file : files) {
            if (file.isDirectory()) {
                totalSize += calSize(file);
            } else {
                totalSize += file.length();
            }
        }
        return totalSize;
    }

    private int calFileNum(File directory) {
        File[] files = directory.listFiles();
        int fileNum = 0;

        if (files == null) {
            System.out.println("I/O exception may occur, cannot count the directory");
            System.exit(3);
        }

        for (File x : files) {
            if (x.isDirectory()) {
                fileNum += calFileNum(x);
            } else {
                ++fileNum;
            }
        }
        return fileNum;
    }

    private boolean fileValidator(String directory) {
        File file = new File(directory);
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            } else {
                System.out.println("This file is not a directory.");
                return false;
            }
        } else {
            System.out.println("Target file not found");
            return false;
        }
    }

    public void setBigFileSize(long bigFileSize) {
        this.bigFileSize = bigFileSize;
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            int operationCode = -1;
            try {
                operationCode = Integer.parseInt(args[1]);
            } catch (NumberFormatException n) {
                System.out.println("Argument two is not a number");
                System.exit(4);
            }
            new FilesManager(operationCode, args[0]);
        } else {
            System.out.println("Wrong usage (fileName operationCode)");
        }
    }
}
