package ltregan;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ltregan.indexsynch.IndexBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    public AppTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    public void testApp() throws Exception{
        Injector injector = Guice.createInjector(new TestModule());

        IndexBuilder builder = injector.getInstance(IndexBuilder.class);
        builder.root(new File("../lookbooks")).run();

        Client client = builder.elasticSearch();


        GetResponse response = client.prepareGet("lookbooks", "lookbook","3")
                .execute()
                .actionGet();
        System.out.println( response.getSource() );
    }
}
