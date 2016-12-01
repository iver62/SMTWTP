set title "Déviation de chaque instance avec la stratégie first improvement et 2 solutions initiales différentes"
set ylabel "Déviation (%)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/vnd/FIRST_IMPROVEMENT_MDD_v1.dat" using 0:1 with lines title "MDD" linetype 7, "../data/results/vnd/FIRST_IMPROVEMENT_RND_v1.dat" using 0:1 with lines title "RND" linetype 6