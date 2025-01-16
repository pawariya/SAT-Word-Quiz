package satWordQuiz;

import java.io.File;

public class WordSorter {

    private String word;
    private String definition;
    private boolean visited;
    
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public WordSorter(String word, String definition) {
        // TODO Auto-generated constructor stub
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }
    
    public String getDefinition() {
        return definition;
    }
    
    
}
