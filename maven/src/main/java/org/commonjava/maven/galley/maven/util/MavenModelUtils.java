package org.commonjava.maven.galley.maven.util;

import org.commonjava.maven.atlas.graph.model.EProjectDirectRelationships;
import org.commonjava.maven.galley.maven.GalleyMavenException;
import org.commonjava.maven.galley.maven.model.view.MavenPomView;
import org.commonjava.maven.galley.maven.rel.MavenModelProcessor;
import org.commonjava.maven.galley.maven.rel.ModelProcessorConfig;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by ruhan on 2/15/17.
 */
public class MavenModelUtils
{
    public static InputStream getSerializedRelationships (final MavenPomView pomView, final URI source,
                                   final ModelProcessorConfig discoveryConfig )
            throws GalleyMavenException
    {
        MavenModelProcessor modelProcessor = new MavenModelProcessor();
        EProjectDirectRelationships rel = modelProcessor.readRelationships(pomView, source, discoveryConfig);
        return getSerializedRelationships( rel );
    }

    private static InputStream getSerializedRelationships( final EProjectDirectRelationships relationships )
    {
        return null;
    }

}
