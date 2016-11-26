set output "C:/Users/Pierrick/workspace/SMTWTP/experiences/xp4.png"
set title "Déviation de chaque instance avec 2 solutions initiales différentes et stratégie best"
set ylabel "Déviation (%)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/BEST_IMPROVEMENT_INSERT_MDD.dat" using 0:2 with lines title "best" linetype 7, "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/BEST_IMPROVEMENT_INSERT_EDD" using 0:2 with lines title "first" linetype 6