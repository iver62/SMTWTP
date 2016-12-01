set title "Déviation de chaque instance avec la stratégie first improvement, solution initiale mdd et 2 algorithmes différents"
set ylabel "Déviation (%)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/vnd/FIRST_IMPROVEMENT_MDD_v1.dat" using 0:1 with lines title "VND" linetype 7, "../data/results/hc/FIRST_IMPROVEMENT_INSERT_MDD.dat" using 0:1 with lines title "HC" linetype 6