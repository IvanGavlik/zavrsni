
export interface PoissonStatistics {
  homeTeam: PoissonStatisticsTeam;
  guestTeam: PoissonStatisticsTeam;
  poissonMatrix: PoissonMatrix;
}

export interface PoissonStatisticsTeam {
  teamName: string;
  statisticalFeatureChart: StatisticalFeatureChart;
  statisticalFeatureTable: StatisticalFeatureTableItem[];
}

export interface StatisticalFeatureChart {
  title: string;
  data: ChartItem[];
}

export interface ChartItem {
  label: number;
  value: number;
}

export interface StatisticalFeatureTableItem {
  goalId: number;
  frequency: number;
  relativeFrequency: number;
  arithmeticMiddle: number; // aritmetička sredina
  dispersion: number;
  deviation: number;
}

export interface PoissonMatrix {
  homeTeamName: string;
  guestTeamName: string;
  homeGuestTeamsPredictionMatrix: Matrix5x5;
}

/**
 * indexXY
 * X - homeTeam
 * Y - guestTeam
 */
export interface Matrix5x5 {
  index00: number;
  index01: number;
  index02: number;
  index03: number;
  index04: number;
  index05: number;

  index10: number;
  index11: number;
  index12: number;
  index13: number;
  index14: number;
  index15: number;

  index20: number;
  index21: number;
  index22: number;
  index23: number;
  index24: number;
  index25: number;

  index30: number;
  index31: number;
  index32: number;
  index33: number;
  index34: number;
  index35: number;

  index40: number;
  index41: number;
  index42: number;
  index43: number;
  index44: number;
  index45: number;

  index50: number;
  index51: number;
  index52: number;
  index53: number;
  index54: number;
  index55: number;

}


