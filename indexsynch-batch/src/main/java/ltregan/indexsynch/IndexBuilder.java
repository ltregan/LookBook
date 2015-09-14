package ltregan.indexsynch;

import com.google.inject.Inject;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.io.stream.OutputStreamStreamOutput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by loic on 8/21/2015.
 */
public class IndexBuilder {

    private java.io.File m_root;
    private URL m_elasticSearchURL;
    private Client m_elasticSearch;



    @Inject
    public IndexBuilder(Client elasticSearch){
        m_elasticSearch = elasticSearch;
    }

    public Client elasticSearch() {
        return m_elasticSearch;
    }

    public IndexBuilder elasticSearch(Client elasticSearch) {
        m_elasticSearch = elasticSearch;
        return this;
    }


    public File root() {
        return m_root;
    }


    public IndexBuilder root(File root) {
        m_root = root;
        return this;
    }

    public URL elasticSearchURL() {
        return m_elasticSearchURL;
    }

    public IndexBuilder elasticSearchURL(URL elasticSearchURL) {
        m_elasticSearchURL = elasticSearchURL;
        return this;
    }

    public IndexBuilder run() throws Exception {
        if (!root().isDirectory())
            throw new RuntimeException("Root \"" + root().getCanonicalPath() + "\" must be a directory.");
        for (File brandDir : root().listFiles()) {
            if (!brandDir.isDirectory())
                throw new RuntimeException("Unexpected file \"" + brandDir.getCanonicalPath() + "\".");
            String brand = brandDir.getName();
            for (File lookbook : brandDir.listFiles()) {
                if( lookbook.isFile() )
                    throw new RuntimeException("Unexpected file \"" + lookbook.getCanonicalPath() + "\".");
                File jsonFile = new File( lookbook, "lookbook.json");
                if( ! jsonFile.isFile() )
                    throw new RuntimeException("Cannot find \"" + jsonFile.getCanonicalPath() + "\".");
                JSONObject o = new JSONObject( new JSONTokener(new FileReader(jsonFile) ));
                o.put("brand", brand);
                o.put("name", lookbook.getName());
                System.out.println(o);


                IndexResponse r = elasticSearch()
                        .prepareIndex("lookbooks", "lookbook")
                        .setId( o.get("id").toString())
                        .setSource(o.toString())
                        .execute()
                        .actionGet();
                assert( r.getId().equals( o.get("id").toString() ) );

            }
        }
        return this;
    }

}
