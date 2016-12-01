set title "Déviation de chaque instance avec solution initiale MDD, population de 20 individus et plusieurs nombres de générations"
set ylabel "Déviation (%)"
set xlabel "Instance"
set xrange [0:124]
plot "../data/results/ga/20_100_MDD.dat" using 0:1 with lines title "100" linetype 7, "../data/results/ga/20_1000_MDD.dat" using 0:1 with lines title "1000" linetype 6, "../data/results/ga/20_2000_MDD.dat" using 0:1 with lines title "2000" linetype 4