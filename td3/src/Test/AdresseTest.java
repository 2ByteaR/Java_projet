package Test;

import static Process.ProcessAdresse.*;
import static org.junit.Assert.*;
import Process.*;

import Metier.Adresse;


import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdresseTest {

    private Adresse adresse1;

     @BeforeEach
     public void setUp(){
         adresse1 = new Adresse("3","bd beauxmarchaix","L-57890","diesen","letzebuerg");
     }


    @Test
    public void testNormalizeVoie(){
        Assertions.assertEquals("boulevard beauxmarchaix", normalizeVoie(adresse1));
    }

    @Test
    public void testNormalizeVille(){
        assertEquals("Diesen", normalizeVille(adresse1));
    }

    @Test
    public void testNormalizeVilleLes(){
         adresse1.setVille("montigny lès metz");
        assertEquals("Montigny-lès-Metz", normalizeVille(adresse1));
    }

    @Test
    public void testNormalizeVillesous(){
        adresse1.setVille("ham sous vasberg");
        assertEquals("Ham-sous-Vasberg", normalizeVille(adresse1));
    }

    @Test
    public void testNormalizeVilleA(){
        adresse1.setVille("pont à mousson");
        assertEquals("Pont-à-Mousson", normalizeVille(adresse1));
    }

    public void testNormalizeVilleAux(){
        adresse1.setVille("la ville aux près");
        assertEquals("La ville aux près", normalizeVille(adresse1));
    }

    @Test
    public void testNormalizePaysLuxembourg(){
        assertEquals("Luxembourg", normalizePays(adresse1));
    }

    @Test
    public void testNormalizeNoRue(){
        assertEquals("3,", normalizeNoRue(adresse1));
    }

    @Test
    public void testNormalizeCodePostalAvecLettre(){
        assertEquals("57890",normalizeCodePostal(adresse1));
    }

    @Test
    public void testNormalizeCodePostalAvecChiffre(){
         adresse1.setCodePostal("L-8200");
        assertEquals("08200",normalizeCodePostal(adresse1));
    }

    @Test
    public void testNormalizeCodePostalDeux(){
        adresse1.setCodePostal("7890");
        assertEquals("07890",normalizeCodePostal(adresse1));
    }

    @Test
    public void testNormalizePaysBelgique(){
         adresse1.setPays("belgium");
        assertEquals("Belgique", normalizePays(adresse1));
    }

    @Test
    public void testNormalizeSuisse(){
        adresse1.setPays("Switzerland");
        assertEquals("Suisse", normalizePays(adresse1));
        adresse1.setPays("Schweiz");
        assertEquals("Suisse", normalizePays(adresse1));
    }

    @Test
    public void testToStringAdresse(){
         assertEquals("3,boulevard beauxmarchaix 57890 Diesen Luxembourg",normalizeAdresse(adresse1).toString());
    }

    @Test
    public void testconstructAdresse(){
        assertNotNull(normalizeAdresse(adresse1));
    }
}
