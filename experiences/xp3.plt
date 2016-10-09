set output "C:/Users/Pierrick/workspace/SMTWTP/experiences/xp3.png"
set title "Temps d'exécution de chaque instance avec voisinage insert, solution initiale MDD et 2 stratégies différentes"
set ylabel "Temps (ms)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/best_insert_mdd.dat" using 0:2 with lines title "best", "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/first_insert_mdd.dat" using 0:2 with lines title "first"