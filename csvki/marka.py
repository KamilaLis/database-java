#!usr/bin/env python

import csv
file_w = open('marka.csv', 'w')
 
cars = ['Chevrolet', 'Chrysler', 'Honda', 'Mazda', 'Fiat', 'Toyota', 'BMW', 'Mercedes', 'Mitsubishi', 'Audi']

file_w.write('marka\n')

for i in range(len(cars)):
	file_w.write(cars[i]+'\n')


	
