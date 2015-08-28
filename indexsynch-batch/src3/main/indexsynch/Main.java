package indexsynch;

/**
 * Created by loic on 8/21/2015.
 */
public class Main {

    public void static main( String args[] ){
        IndexBuilder builder = new  IndexBuilder();
        builder.root( new File("../lookbooks" ))
                .elasticSearchUrl( new URL("http://localhost:80")).run();


    }
}
