package org.commonjava.maven.galley.maven.parse;

import java.util.HashMap;
import java.util.Map;

import org.commonjava.maven.atlas.ident.ref.ProjectRef;
import org.commonjava.maven.galley.model.Location;

import com.ximpleware.VTDNav;

public final class DocRef<T extends ProjectRef>
{

    private final VTDNav doc;

    private final T ref;

    private final Location location;

    private final Map<String, Object> attributes = new HashMap<>();

    private DocCacheKey<T> key;

    public DocRef( final T ref, final Location location, final VTDNav doc )
    {
        this.ref = ref;
        this.location = location;
        this.doc = doc;
    }

    public VTDNav getDoc()
    {
        return doc;
    }

    public T getRef()
    {
        return ref;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setAttribute( final String key, final Object value )
    {
        attributes.put( key, value );
    }

    public <C> C getAttribute( final String key, final Class<C> type )
    {
        final Object val = attributes.get( key );
        return val == null ? null : type.cast( val );
    }

    @Override
    public String toString()
    {
        return String.format( "DocRef [%s] (from: %s)", ref, location );
    }

    public synchronized DocCacheKey<T> getCacheKey()
    {
        if ( key == null )
        {
            key = new DocCacheKey<T>( ref, location );
        }

        return key;
    }

}