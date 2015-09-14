package ltregan.indexsynch;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.File;

/**
 * Created by loic on 8/27/2015.
 */
public class App {

    public static void  main( String args[] ) throws Exception{
        Injector injector = Guice.createInjector(new ProductionModule());

        IndexBuilder builder = injector.getInstance(IndexBuilder.class);
        builder.root(new File("../lookbooks")).run();

    }

}
