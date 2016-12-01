set title "Instance 86"
set ylabel "Déviation (%)"
set xlabel "Temps (ms)"
set xrange [0:124]
plot "../data/results/ts/FIRST_IMPROVEMENT_MDD_58.dat" using 1:2 with lines title "TS" linetype 7, "../data/results/vnd/FIRST_IMPROVEMENT_MDD_58.dat" using 1:2 with lines title "VND" linetype 6