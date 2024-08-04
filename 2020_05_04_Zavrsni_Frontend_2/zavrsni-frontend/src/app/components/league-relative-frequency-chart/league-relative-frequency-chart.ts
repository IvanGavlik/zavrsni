export interface RelativeFrequency {
  goal: number;
  relativeFrequency: number;
}

export interface LeagueRelativeFrequencyChartWrapper {
  all: RelativeFrequency[];
  home: RelativeFrequency[];
  guest: RelativeFrequency[];
}

export interface LeagueRelativeFrequencyTable {
  goal: number;
  all: number;
  home: number;
  guest: number;
}
