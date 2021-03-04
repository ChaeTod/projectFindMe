package Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class FindWord extends Component {
    String[] openFile(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));
        int returnVal = jFileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            try {
                return makeReadFromFile(jFileChooser);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return new String[0];
    }

    private String[] makeReadFromFile(JFileChooser jFileChooser) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(jFileChooser.getSelectedFile());
        byte[] content = new byte[fileInputStream.available()];
        fileInputStream.read(content);
        return new String(content, "UTF-8").split("\n");
    }
}

class WordFinder {
    private FindWord findWord = new FindWord();

    void findNextWord(String word){
        int i = 1;
        for (String lines : findWord.openFile()){
            int j = 1;
            String[] line = lines.split(" ");
            for (String words : line){
                if (words.equalsIgnoreCase(word)) {
                    System.out.println("The word is placed at the line " + i + " on position " + j);
                }
                j++;
            }
            i++;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        WordFinder wordFinder = new WordFinder();
        wordFinder.findNextWord("was");
    }
}
