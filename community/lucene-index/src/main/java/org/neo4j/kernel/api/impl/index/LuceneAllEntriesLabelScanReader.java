/**
 * Copyright (c) 2002-2013 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.api.impl.index;

import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.search.IndexSearcher;

import org.neo4j.kernel.api.direct.AllEntriesLabelScanReader;
import org.neo4j.kernel.api.direct.NodeLabelRange;

public class LuceneAllEntriesLabelScanReader implements AllEntriesLabelScanReader
{
    private final IndexSearcher searcher;
    private final BitmapDocumentFormat format;

    public LuceneAllEntriesLabelScanReader( IndexSearcher searcher, BitmapDocumentFormat format )
    {
        this.searcher = searcher;
        this.format = format;
    }

    @Override
    public Iterator<NodeLabelRange> iterator()
    {
        return new NodeLabelRangeIterator( searcher, format);
    }

    @Override
    public void close() throws IOException
    {
        searcher.close();
    }

    @Override
    public long maxCount()
    {
        return searcher.maxDoc();
    }
}
