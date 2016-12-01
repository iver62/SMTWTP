set title "Déviation de chaque instance avec solution initiale MDD, voisinage insert et 2 stratégies différentes"
set ylabel "Déviation (%)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/hc/BEST_IMPROVEMENT_INSERT_MDD.dat" using 0:1 with lines title "best improvement" linetype 7, "../data/results/hc/FIRST_IMPROVEMENT_INSERT_MDD.dat" using 0:1 with lines title "first improvement" linetype 6