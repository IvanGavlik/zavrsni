package hr.vsite.igavlik.zavrsnirad;


import hr.vsite.igavlik.zavrsnirad.service.v3.FootballGameService;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class ZavrsniRadApplication implements ApplicationContextAware  {

    private static ApplicationContext aC;

    public static void main(String[] args) {
        SpringApplication.run(ZavrsniRadApplication.class, args);
        // calculate liga and club statistic
        FootballGameService footballGameService = aC.getBean(FootballGameService.class);
        System.out.println(footballGameService.getLeagueReview());
        System.out.println(footballGameService.getTeamReviews());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        aC = applicationContext;
    }





}
