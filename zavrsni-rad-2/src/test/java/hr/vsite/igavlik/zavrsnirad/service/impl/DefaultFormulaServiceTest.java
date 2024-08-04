package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.service.v3.formula.DefaultFormulaService;
import hr.vsite.igavlik.zavrsnirad.service.v3.formula.FormulaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DefaultFormulaServiceTest {

    private static FormulaService formulaService;


    @BeforeAll
    public static void setUp() {
        formulaService = new DefaultFormulaService();
    }

    @Test
    public void testData() {
        Double d = formulaService.arithmeticMiddle(5, new Double(0.08));
        System.out.println("Aritmeticka sredina::" + d);

        Double d1 = formulaService.arithmeticMiddle(10, new Double(0.1));
        System.out.println("Aritmeticka sredina::" + d1);

        Double d2 = formulaService.arithmeticMiddle(15, new Double(0.16));
        System.out.println("Aritmeticka sredina::" + d2);

        Double d3 = formulaService.arithmeticMiddle(20, new Double(0.21));
        System.out.println("Aritmeticka sredina::" + d3);

        Double d4 = formulaService.arithmeticMiddle(25, new Double(0.17));
        System.out.println("Aritmeticka sredina::" + d4);

        Double d5 = formulaService.arithmeticMiddle(30, new Double(0.12));
        System.out.println("Aritmeticka sredina::" + d5);

        Double d6 = formulaService.arithmeticMiddle(35, new Double(0.09));
        System.out.println("Aritmeticka sredina::" + d6);

        Double d7 = formulaService.arithmeticMiddle(40, new Double(0.07));
        System.out.println("Aritmeticka sredina::" + d7);

        Double e = formulaService.dispersion(10, 0.10);
        System.out.println("Varijanca:: " + e);

        assert (true);
    }


}
