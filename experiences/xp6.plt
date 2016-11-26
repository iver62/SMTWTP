set title "comparaison hc et vnd first mdd"
set ylabel "temps (ms)"
set xlabel "Instances"
set xrange [0:124]
plot "C:/Users/Pierrick/workspace/SMTWTP/data/results/hc/first_insert_mdd.dat" using 0:3 with lines title "HC", "C:/Users/Pierrick/workspace/SMTWTP/data/results/vnd/first_mdd.dat" using 0:3 with lines title "VND"