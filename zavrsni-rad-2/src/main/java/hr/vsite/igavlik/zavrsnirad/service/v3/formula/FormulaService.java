package hr.vsite.igavlik.zavrsnirad.service.v3.formula;

public interface FormulaService {

    Double relativeFrequency(Integer frequency, Integer numberOfElements);

    Double arithmeticMiddle(Integer goal, Double relativeFrequency);

    Double dispersion(Integer goal, Double relativeFrequency);

    Double deviation(Double dispersion);

    Double calculatePoisson(Double average, Integer x);
}
