import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import java.io.File;

public class ListingFiles {
    /**
     * Recursively lists all files and folders starting from the given file reference.
     * Uses a Queue to stage directory items before recursive depth-first traversal.
     * 
     * @param file the target file or directory
     * @param depth current nesting level in the directory hierarchy (for indentation)
     */
    public static void listFiles(File file, int depth) {
        if (!file.exists()) {
            return;
        }

        // Print indentation corresponding to current hierarchy depth
        for (int i = 0; i < depth; i++) {
            StdOut.print("      "); // 4 spaces per depth level
        }
        StdOut.println(file.getName());

        // If file is a directory, enqueue its sub-files/folders for processing
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                Queue<File> queue = new Queue<File>();
                for (File f : files) {
                    queue.enqueue(f);
                }
                while (!queue.isEmpty()) {
                    listFiles(queue.dequeue(), depth + 1);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        if (args.length == 0) {
            StdOut.println("Usage: java ListingFiles <folder-name>");
            return;
        }

        File root = new File(args[0]);
        if (!root.exists()) {
            StdOut.println("Error: Path does not exist: " + args[0]);
            return;
        }

        listFiles(root, 0);
    }
}