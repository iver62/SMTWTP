set title "comparaison algo VND stratégie first improvement mdd et rnd"
set ylabel "Déviation (%)"
set xlabel "time"
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/vnd/FIRST_IMPROVEMENT_MDD.dat" using 2:1 with lines title "MDD" linetype 7, "C:/Users/Pierrick/workspace/SMTWTP/data/results/vnd/FIRST_IMPROVEMENT_RND.dat" using 2:1 with lines title "RND" linetype 6