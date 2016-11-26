set title "Déviation de chaque instance avec solution initiale MDD, voisinage insert et 2 stratégies différentes"
set ylabel "Déviation (%)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/BEST_IMPROVEMENT_INSERT_MDD.dat" using 0:1 with lines title "best" linetype 7, "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/FIRST_IMPROVEMENT_INSERT_MDD" using 0:1 with lines title "first" linetype 6