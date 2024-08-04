export interface TeamReview {
  name: string;

  allNumberOFGoals: number;
  allNumberOFGames: number;
  allAverageGoals: number;
  homeNumberOFGoals: number;
  homeNumberOFGames: number;
  homeAverageGoals: number;
  guestNumberOFGoals: number;
  guestNumberOFGames: number;
  guestAverageGoals: number;

  receivedAllNumberOFGoals: number;
  receivedAllNumberOFGames: number;
  receivedAllAverageGoals: number;
  receivedHomeNumberOFGoals: number;
  receivedHomeNumberOFGames: number;
  receivedHomeAverageGoals: number;
  receivedGuestNumberOFGoals: number;
  receivedGuestNumberOFGames: number;
  receivedGuestAverageGoals: number;

  // attack/defense factors
  asHomeAttackFactor: number;
  asHomeDefensekFactor: number;
  asGuestAttackFactor: number;
  asGuestDefensekFactor: number;
  allAttackFactor: number;
  allDefensekFactor: number;

}
