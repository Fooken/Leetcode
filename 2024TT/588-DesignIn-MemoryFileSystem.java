import java.util.*;

// Design a data structure that simulates an in-memory file system.
// Implement the FileSystem class:

//     FileSystem() Initializes the object of the system.


/**
 * 
 * Constraints:
 *  - 1 <= path.length, filePath.length <= 100
 *  - path and filePath are absolute paths which begin with '/' 
 *    and do not end with '/' except that the path is just "/".
 *  - You can assume that all directory names and file names only contain lowercase letters, 
 *    and the same names will not exist in the same directory.
 *  - You can assume that all operations will be passed valid parameters, 
 *    and users will not attempt to retrieve file content or list a 
 *    directory or file that does not exist.
 *  - 1 <= content.length <= 50
 *  - At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 * 
 */
public class Main {
    public static void main(String[] args) {
        
        // Input:
        // ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
        // [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
        // Output:
        // [null, [], null, null, ["a"], "hello"]
        System.out.println("New FileSystem");
        FileSystem obj = new FileSystem();
        
        System.out.println("ls: ");
        List<String> param_1 = obj.ls("/");
        if (param_1 == null || param_1.size() == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < param_1.size() - 1; i++) {
                System.out.print(param_1.get(i));
            }
            System.out.print(param_1.get(param_1.size() - 1) + "]");
        }
        
        // Output: []

        System.out.println("mkdir: ");
        obj.mkdir("/a/b/c");
        // Output: null

        System.out.println("addContentToFile: ");
        obj.addContentToFile("/a/b/c/d","hello word");
        // Output: null

        obj.addContentToFile("/a/b/c/d"," kai");

        System.out.println("ls: ");
        List<String> param_33 = obj.ls("/");
        System.out.print("[");
        for (int i = 0; i < param_33.size() - 1; i++) {
            System.out.print(param_33.get(i));
        }
        System.out.println(param_33.get(param_33.size() - 1) + "]");
        // Output: ["a"]
        System.out.println("readContentFromFile: ");
        String param_4 = obj.readContentFromFile("/a/b/c/d");
        System.out.println("readContentFromFile: "+param_4);
    }
}
class FileNode {
    boolean isFile;
    Map<String, FileNode> children;
    String content;
    public FileNode() {
        this.isFile = false;
        this.children = new HashMap<>();
        this.content = "";
    }
}

class FileSystem {
    private FileNode root;
    public FileSystem() {
        this.root = new FileNode();
    }

    // List<String> ls(String path)
    //     If path is a file path, returns a list that only contains this file's name.
    //     If path is a directory path, returns the list of file and directory names in this directory.
    // The answer should in lexicographic order.
    public List<String> ls(String path) {
        FileNode node = root;
        String[] paths = path.split("/");
        List<String> res = new ArrayList<>();

        for (String str: paths) {
            if (!str.isEmpty()) {
                if (!node.children.containsKey(str)) {
                    return res;
                }
                node = node.children.get(str);
            }
        }
        if (node.isFile) {
            res.add(paths[paths.length - 1]);
        } else {
            res.addAll(node.children.keySet());
        }
        Collections.sort(res);

        return res;
    }
    // void mkdir(String path) Makes a new directory according to the given path. 
    // The given directory path does not exist. 
    // If the middle directories in the path do not exist, you should create them as well.
    public void mkdir(String path){
        FileNode node = root;
        String[] paths = path.split("/");
        for (String str: paths) {
            if (!str.isEmpty()) {
                if (!node.children.containsKey(str)) {
                    node.children.put(str, new FileNode());
                }
                node = node.children.get(str);
            }
        }
    }

    // void addContentToFile(String filePath, String content)
    //     If filePath does not exist, creates that file containing given content.
    //     If filePath already exists, appends the given content to original content.

    public void addContentToFile(String filePath, String content){
        FileNode node = root;
        String[] paths = filePath.split("/");

        for (String str: paths) {
            if (!str.isEmpty()) {
                if (!node.children.containsKey(str)) {
                    node.children.put(str, new FileNode());
                }
                node = node.children.get(str);
            }
        }
        node.isFile = true;
        node.content += content;
    }

    // String readContentFromFile(String filePath) Returns the content in the file at filePath.
    public String readContentFromFile(String filePath) {
        FileNode node = root;
        String[] paths = filePath.split("/");

        for (String str: paths) {
            if (!str.isEmpty()) {
                if (!node.children.containsKey(str)) {
                    return "";
                }
                node = node.children.get(str);
            }
        }
        return node.content;
    }
}