import java.util.ArrayList;
public class Folder implements FileItem {
    private final String folderName;
    private final ArrayList<FileItem> items;

    // Create a folder given the folderName
    public Folder(String folderName) {
        this.folderName = folderName;
        this.items = new ArrayList<>();
    }

    // Add a FileItem to the folder
    public void addToFolder(FileItem item) {
        this.items.add(item);
    }

    // Returns the total number of files
    // This is a folder, so it counts as 1
    // In addition, we need to count all the files in the folder,
    // and all the files in the folder's folders, etc.!
    public int countFiles() {
        int count = 1;

        // Use recursion to count the files
        // We don't need a base case because the
        // files are the "base case"
        for (FileItem item : items) {
            count += item.countFiles();
        }

        return count;
    }

    // Calculates the total size of the files and folders in the current FileItem
    // The starting size of a Folder should be 512;
    // furthermore, we should add 128 for each FileItem in the Folder,
    // plus the actual size of all the items in the folder.

    // For example, let's say that we have a Folder called folder1 that contains 3 Files:
    // file1 which is size 200, file2 which is size 300, and file3 which is size 150.
    // Then, the size of folder1 = 512 + 128*3 + 200 + 300 + 150 = 1546.
    public int calculateSize() {
        // YOUR CODE HERE

        int folderSize = 512;

        for (FileItem item : items) {
            folderSize += 128 + item.calculateSize();
        }

        return folderSize;
    }


    // Creates a copy of the current FileItem
    // We will need to copy the folder and then copy all the
    // stuff inside the folder!

    // In the Folder class, the copy() method should return a new Folder
    // with the new folderName consisting of the String "_copy" appended
    // to the end of folderName of the original Folder. In addition, the
    // copy() method should be called on all FileItems in the folder, such
    // that the contents of the folder is copied as well.
    public FileItem copy() {
        // YOUR CODE HERE

        Folder newFolder = new Folder(this.folderName + "_copy");

        for (FileItem item : items) {
            newFolder.addToFolder(item.copy());
        }

        return newFolder;
    }

    // toString method
    @Override
    public String toString() {
        return this.folderName + ": " + this.items;
    }
}