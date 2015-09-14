package ltregan;

import com.google.inject.AbstractModule;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by loic on 9/1/2015.
 */
public class TestModule extends AbstractModule {

    protected void configure(){
        Node node = nodeBuilder().local(true).node();
        Client client = node.client();
        bind(Client.class).toInstance(client);

    }
}
