export interface LeagueFrequencyChart {
  goal: number;
  frequency: number;
}

export interface LeagueFrequencyChartWrapper {
  all: LeagueFrequencyChart[];
  home: LeagueFrequencyChart[];
  guest: LeagueFrequencyChart[];
}

export interface LeagueFrequencyTable {
  goal: number;
  all: number;
  home: number;
  guest: number;
}
