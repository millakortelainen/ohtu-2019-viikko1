package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto rikkinainenVarasto;
    Varasto kuormitettuVarasto;
    Varasto rikkinainenKuormitettuVarasto;
    Varasto tasapainotonKuormitettuVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        rikkinainenVarasto = new Varasto(-1);
        kuormitettuVarasto = new Varasto(100, 8.8);
        rikkinainenKuormitettuVarasto = new Varasto(-100, -100);
        tasapainotonKuormitettuVarasto = new Varasto(2, 400);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenVarasto() {
        assertEquals(0, rikkinainenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuVarasto() {
        assertEquals(100, kuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(8.8, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenLKuormitettuVarasto() {
        assertEquals(0, rikkinainenKuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, rikkinainenKuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoMeneeYli() {
        assertEquals(2, tasapainotonKuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, tasapainotonKuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void koitetaanLisätäVarastoonLiikaa() {
        varasto.lisaaVarastoon(122);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void koitetaanLisätäVarastoonOlematonta() {
        varasto.lisaaVarastoon(-122);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanVarastostaOlematonta() {
        double varastosta = varasto.otaVarastosta(-1000);
        assertEquals(0, varastosta, vertailuTarkkuus);
    }

    @Test
    public void otetaanVarastostaLiikaa() {
        double varastosta = varasto.otaVarastosta(10000);
        assertEquals(0, varastosta, vertailuTarkkuus);
    }

    @Test
    public void tulostusOnOikein() {
        String varastossa = varasto.toString();
        assertEquals("saldo = 1, vielä tilaa 10.0", varastossa);
    }

}
