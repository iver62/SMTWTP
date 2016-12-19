set title "Instance 86"
set ylabel "Déviation (%)"
set xlabel "Temps (ms)"
plot "../data/results/ts/FIRST_IMPROVEMENT_MDD_86.dat" using 2:1 with lines title "TS" linetype 7, "../data/results/vnd/FIRST_IMPROVEMENT_MDD_86.dat" using 2:1 with lines title "VND" linetype 6