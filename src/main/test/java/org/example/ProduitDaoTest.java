package org.example;

import org.example.entities.Categorie;
import org.example.entities.Produit;
import org.example.service.CrudService;
import org.example.service.ProduitService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ProduitDaoTest {

    private static CrudService<Categorie, Long> catService;
    private static ProduitService prodService;

    @BeforeClass
    public static void init() {
        catService = new CrudService<>(Categorie.class);
        prodService = new ProduitService();
    }

    @Test
    public void testCreateAndSearchProduit() {
        Categorie cat = new Categorie("Test");
        catService.create(cat);

        Produit p1 = new Produit("PC Portable", new BigDecimal("9000.00"), cat);
        Produit p2 = new Produit("Souris", new BigDecimal("30.00"), cat);

        prodService.create(p1);
        prodService.create(p2);

        assertNotNull(p1.getId());
        assertNotNull(p2.getId());

        List<Produit> produitsCat = prodService.findByCategorie(cat.getId());
        assertEquals(2, produitsCat.size());

        List<Produit> searchResults = prodService.search("portable", new BigDecimal("5000.00"), null);
        assertEquals(1, searchResults.size());
        assertEquals("PC Portable", searchResults.get(0).getLibelle());
    }
}