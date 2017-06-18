#!usr/bin/env python

import csv

# model
file_w = open('marka.csv', 'w')
 
cars = ['Chevrolet', 'Chrysler', 'Honda', 'Mazda', 'Fiat', 'Toyota', 'BMW', 'Mercedes', 'Mitsubishi', 'Audi']

file_w.write('marka\n')

for i in range(len(cars)):
	file_w.write(cars[i]+'\n')

file_w.close()
	
#typ_pojazdu
file_w1 = open('typ_pojazdu.csv', 'w')
 
typ_pojazdu = ['ciezarowy', 'osobowy', 'dostawczy']

file_w1.write('typ_pojazdu\n')

for i in range(len(typ_pojazdu)):
	file_w1.write(typ_pojazdu[i]+'\n')

file_w1.close() 

#model
file_w2 = open('model.csv', 'w')
 
model = ['Corvette', 'Crossfire', 'Civic', '2', '3', '5', '6', '126p', 'Punto', 'Avensis', 'Corolla', 'Touring', 'Coupe', 'Klasa E', 'Klasa G', 'Lancer', 'Colt', 'a4', 'a6']

file_w2.write('model\n')

for i in range(len(model)):
	file_w2.write(model[i]+'\n')

file_w2.close() 

#Kategoria_prawa_jazdy
file_w3 = open('kategoria_prawa_jazdy.csv', 'w')
 
kategoria_prawa_jazdy = ['AM', 'A1', 'A2', 'A', 'B1', 'B', 'C1', 'C', 'D1', 'D', 'BE', 'C1E', 'CE', 'D1E', 'DE', 'T']

file_w3.write('kategoria_prawa_jazdy\n')

for i in range(len(kategoria_prawa_jazdy)):
	file_w3.write(kaztegoria_prawa_jazdy[i]+'\n')

file_w3.close()


	
