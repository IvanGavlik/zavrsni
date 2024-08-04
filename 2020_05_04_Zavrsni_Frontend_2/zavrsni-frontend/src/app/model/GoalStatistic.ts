/**
private int numberOfEvents;
 private int scoreSum;
 private double scoreAverage;
 private int receivedSum;
 private double receivedAverage;

 private int homeNumberOfEvents;
 private int homeScoreSum;
 private double homeScoreAverage;
 private int homeReceivedSum;
 private double homeReceivedAverage;

 private int guestNumberOfEvents number;
 private int guestScoreSum number ;
 private double guestScoreAverage number;
 private int guestReceivedSum number;
 private double ;
 */
export interface GoalStatistic {
  numberOfEvents: number;
  scoreSum: number;
  scoreAverage: number;
  receivedSum: number;
  receivedAverage: number;

  homeNumberOfEvents: number;
  homeScoreSum: number;
  homeScoreAverage: number;
  homeReceivedSum: number;
  homeReceivedAverage: number;

  guestNumberOfEvents: number;
  guestScoreSum: number;
  guestScoreAverage: number;
  guestReceivedSum: number;
  guestReceivedAverage: number;
}
