package ltregan.indexsynch;

import java.io.File;

/**
 * Created by loic on 8/27/2015.
 */
public class App {

    public static void  main( String args[] ) throws Exception{
        IndexBuilder builder = new  IndexBuilder();
        builder.root( new File("../lookbooks" ))
                .elasticSearchURL( new java.net.URL("http://localhost:80")).run();


    }

}
