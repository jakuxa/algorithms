import java.io.File;

public class Test {

    public static void main(String[] args) {
        String filepath = "/Users/jakuxa/Projects/java/algorithms/Script/target/Script-1.0-SNAPSHOT-jar-with-dependencies.jar";
        File file = new File(filepath);
        System.out.println(File.separator);
        System.out.println(file.getParent());
    }
}
