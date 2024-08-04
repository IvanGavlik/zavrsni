export interface PoissonCard {
  homeTeamName: string;
  homeTeamGoalGuess: number;
  homeTeamLambda: number;
  homeTeamHomeAttackFactor: number;
  homeTeamHomeDefenseFactor: number;

  guestTeamName: string;
  guestTeamGoalGuess: number;
  guestTeamLambda: number;
  guestTeamGuestAttackFactor: number;
  guestTeamGuestDefenseFactor: number;

  probabilityOfGuess: number;

  // details panel
  homeTeamProbabilityOf0: number;
  homeTeamProbabilityOf1: number;
  homeTeamProbabilityOf2: number;
  homeTeamProbabilityOf3: number;
  homeTeamProbabilityOf4: number;
  homeTeamProbabilityOf5: number;

  guestTeamProbabilityOf0: number;
  guestTeamProbabilityOf1: number;
  guestTeamProbabilityOf2: number;
  guestTeamProbabilityOf3: number;
  guestTeamProbabilityOf4: number;
  guestTeamProbabilityOf5: number;

}
