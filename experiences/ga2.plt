set title "Déviation de chaque instance 2 solutions initiales, 1000 générations et une population de 20"
set ylabel "Déviation (%)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/ga/20_1000_MDD.dat" using 0:1 with lines title "MDD" linetype 7, "../data/results/ga/20_1000_RND.dat" using 0:1 with lines title "RND" linetype 6