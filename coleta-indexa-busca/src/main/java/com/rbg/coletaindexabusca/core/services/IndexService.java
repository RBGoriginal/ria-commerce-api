package com.rbg.coletaindexabusca.core.services;

import com.rbg.coletaindexabusca.core.ports.IIndexService;
import com.rbg.coletaindexabusca.shared.enums.ProductEnum;
import com.rbg.coletaindexabusca.shared.enums.StoreEnum;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import static com.rbg.coletaindexabusca.core.services.CollectService.getDirectoryCollect;

@Service
public class IndexService implements IIndexService {
    private static final String INDEX_DIR = "path/to/index/";

    @Override
    public void indexProduct(ProductEnum productFilter) {
        try {
            Directory directory = FSDirectory.open(Paths.get(getDirectoryIndex(StoreEnum.KABUM, productFilter)));

            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            IndexWriter writer = new IndexWriter(directory, config);

            BufferedReader reader = new BufferedReader(new FileReader(getDirectoryCollect(StoreEnum.KABUM, productFilter)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 3) {
                    String produto = fields[0];
                    String preco = fields[1];
                    String link = fields[2];

                    Document doc = new Document();
                    doc.add(new TextField("produto", produto, Field.Store.YES));
                    doc.add(new TextField("preco", preco, Field.Store.YES));
                    doc.add(new TextField("link", link, Field.Store.YES));

                    writer.addDocument(doc);
                }
            }

            writer.close();
            reader.close();

            System.out.println("Documentos indexados com sucesso a partir do arquivo CSV!");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao indexar os documentos: " + e.getMessage());
        }
    }

    public static String getDirectoryIndex(StoreEnum store, ProductEnum product) {

        return INDEX_DIR + "/" + store.getLoja() + "/" + product + "-" + store.getIndex();
    }

}
