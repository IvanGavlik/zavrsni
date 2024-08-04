package hr.vsite.igavlik.zavrsnirad.service.v3.formula;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultFormulaService implements FormulaService {

    private static Logger logger = LoggerFactory.getLogger(DefaultFormulaService.class);

    @Override
    public Double relativeFrequency(Integer frequency, Integer numberOfElements) {
        return (double) frequency / numberOfElements;
    }

    @Override
    public Double arithmeticMiddle(Integer goal, Double relativeFrequency) {
        return goal * relativeFrequency;
    }

    @Override
    public Double dispersion(Integer goal, Double relativeFrequency) {
        final double POW_ON_2 = 2;
        return Math.pow(goal, POW_ON_2) * relativeFrequency;
    }

    @Override
    public Double deviation(Double dispersion) {
        return Math.sqrt(dispersion);
    }

    /**
     * P(x; μ) = (e-μ) (μx) / x!
     */
    @Override
    public Double calculatePoisson(Double average, Integer guess) {
        logger.info("calculatePoisson average {} guess {}", average, guess);
        final Double e = new Double("2.71828");
        final Double eOnNegativeAverage = Math.pow(e, (average * -1));
        final Double averageOnX = Math.pow(average, guess);
        final Double res = eOnNegativeAverage * averageOnX / factorial(guess);
        logger.info("calculatePoisson eOnNegativeAverage {} averageOnX {} res {}", eOnNegativeAverage, averageOnX, res);
        return res;
    }

    private long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
