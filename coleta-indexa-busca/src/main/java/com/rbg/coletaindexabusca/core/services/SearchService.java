package com.rbg.coletaindexabusca.core.services;

import com.rbg.coletaindexabusca.core.ports.ISearchService;
import com.rbg.coletaindexabusca.shared.enums.StoreEnum;
import com.rbg.coletaindexabusca.shared.filters.ProductFilterDTO;
import com.rbg.coletaindexabusca.shared.responses.ProductResponseDTO;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.rbg.coletaindexabusca.core.services.IndexService.getDirectoryIndex;

@Service
public class SearchService implements ISearchService {
    @Override
    public List<ProductResponseDTO> findProduct(ProductFilterDTO filterDTO) {
        List<ProductResponseDTO> result = new ArrayList<>();

        filterDTO.setNome(filterDTO.getNome() == null ? "" : filterDTO.getNome());

        try {
            Directory directory = FSDirectory.open(Paths.get(getDirectoryIndex(StoreEnum.KABUM, filterDTO.getProduto())));
            IndexReader reader = DirectoryReader.open(directory);

            IndexSearcher searcher = new IndexSearcher(reader);

            QueryParser parser = new QueryParser("produto", new StandardAnalyzer());
            Query query = parser.parse(filterDTO.getNome());

            TopDocs topDocs = searcher.search(query, 10);

            int cont = 0;
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);

                if (!doc.get("preco").equals("R$ ---")) {
                    System.out.println("Produto: " + doc.get("produto"));
                    System.out.println("Preço: " + doc.get("preco"));
                    System.out.println("Link: " + doc.get("link"));
                    System.out.println("-----------------------------");
                    result.add(ProductResponseDTO.builder().produto(doc.get("produto")).preco(doc.get("preco")).link(doc.get("link")).build());
                    cont++;
                }
            }

            reader.close();
            System.out.println("Total Produtos: " + cont);
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao buscar os documentos: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
