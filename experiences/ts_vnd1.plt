set title "Déviation de chaque instance avec la stratégie first improvement, solution initiale MDD et 2 algos différents"
set ylabel "Déviation (%)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/ts/FIRST_IMPROVEMENT_MDD.dat" using 0:1 with lines title "TS" linetype 7, "../data/results/vnd/FIRST_IMPROVEMENT_MDD_v1.dat" using 0:1 with lines title "VND" linetype 6