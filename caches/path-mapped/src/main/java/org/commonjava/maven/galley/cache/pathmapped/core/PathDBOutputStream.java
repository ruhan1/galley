package org.commonjava.maven.galley.cache.pathmapped.core;

import org.commonjava.maven.galley.cache.pathmapped.model.PathKey;
import org.commonjava.maven.galley.cache.pathmapped.model.PathMap;
import org.commonjava.maven.galley.cache.pathmapped.spi.PathDB;
import org.commonjava.maven.galley.cache.pathmapped.spi.PhysicalStore;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import static org.commonjava.maven.galley.cache.pathmapped.util.PathMapUtils.getPathKey;

public class PathDBOutputStream
                extends FilterOutputStream
{
    private final PathDB pathDB;

    private final PhysicalStore physicalStore;

    private final String fileSystem;

    private final String path;

    private final FileInfo fileInfo;

    private final String fileId;

    private final String fileStorage;

    private int size;

    public PathDBOutputStream( PathDB pathDB, PhysicalStore physicalStore, String fileSystem, String path, FileInfo fileInfo, OutputStream out )
    {
        super( out );
        this.pathDB = pathDB;
        this.physicalStore = physicalStore;
        this.fileSystem = fileSystem;
        this.path = path;
        this.fileInfo = fileInfo;
        this.fileId = fileInfo.getFileId();
        this.fileStorage = fileInfo.getFileStorage();
    }

    @Override
    public void write( int b ) throws IOException
    {
        try
        {
            super.write( b );
            size += 1;
        }
        catch ( IOException e )
        {
            // the generated physical file should be deleted immediately
            physicalStore.delete( fileInfo );
            throw e;
        }
    }

    @Override
    public void close() throws IOException
    {
        super.close();
        PathMap pathMap = new PathMap();
        pathMap.setPathKey( getPathKey( fileSystem, path ) );
        pathMap.setCreation( new Date() );
        pathMap.setFileId( fileId );
        pathMap.setFileStorage( fileStorage );
        pathMap.setSize( size );
        pathDB.insert( pathMap );
    }
}