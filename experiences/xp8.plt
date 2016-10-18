set terminal png
set output "/home/m2mocad/deshayes/Bureau/xp8.png"
set title "performances vnd et ts first mdd"
set ylabel "Déviation (%)"
set xlabel "time"

plot "/home/m2mocad/deshayes/workspace/SMTWTP/data/results/vnd/first_rnd.dat" using 0:1 with lines title "VND", "/home/m2mocad/deshayes/workspace/SMTWTP/data/results/ts/first_rnd.dat" using 0:1 with lines title "TS"
