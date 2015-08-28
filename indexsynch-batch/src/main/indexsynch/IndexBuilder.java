package main.indexsynch;

import java.io.File;
import java.net.URL;

/**
 * Created by loic on 8/21/2015.
 */
public class IndexBuilder {

    private java.io.File m_root;
    private URL m_elasticSearchURL;


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

    public IndexBuilder run() throws Exception{
        if( ! root().isDirectory() )
            throw new RuntimeException("Root \""+root().getCanonicalPath()+"\" must be a directory.");
        for( File brandDir: root().listFiles() ){
            if( ! brandDir.isDirectory() )
                throw new RuntimeException("Unexpected file \""+brandDir.getCanonicalPath()+"\".");
            String brand = brandDir.getName();
            for( File lookbook: brandDir.listFiles() ){
                if( lookbook.isDirectory() )
                    throw new RuntimeException("Unexpected directory \""+lookbook.getCanonicalPath()+"\".");
                System.out.println("");
            }
        }
    }

}
