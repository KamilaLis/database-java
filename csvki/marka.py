#!usr/bin/env python

import csv
import random

def read_words(words_file):
  with open(words_file, 'r') as f:
    ret = []
    for line in f:
      ret += line.split()
    return ret


# marka
file_w = open('marka.csv', 'w')
 
marka = ['Chevrolet', 'Chrysler', 'Honda', 'Mazda', 'Fiat', 'Toyota', 'BMW', 'Mercedes', 'Mitsubishi', 'Audi']

file_w.write('marka\n')

for i in range(len(marka)):
	file_w.write(marka[i]+'\n')

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
	file_w3.write(kategoria_prawa_jazdy[i]+'\n')

file_w3.close()

#Rodzaj_ladunku
file_w4 = open('rodzaj_ladunku.csv', 'w')
 
rodzaj_ladunku = ['ladunki wysokiej wartosci', 'szybko psujace sie', 'sypkie', 'ponadgabarytowe', 'niebezpieczne', 'plynne', 'drobnicowe']

file_w4.write('rodzaj_ladunku\n')

for i in range(len(rodzaj_ladunku)):
	file_w4.write(rodzaj_ladunku[i]+'\n')

file_w4.close()

#Czesci_samochodowe
file_w5 = open('czesc_samochodowa.csv', 'w')
 
czesci_samochodowe = ['akumulator', 'przeguby', 'silnik', 'drazki kierownicze', 'karoseria', 'wycieraczki', 'instalacja elektryczna', 'turbosprezarka', 'klimatyzacja', 'skrzynia biegow', 'waly i polosie']

file_w5.write('czesc_samochodowa\n')

for i in range(len(czesci_samochodowe)):
	file_w5.write(czesci_samochodowe[i]+'\n')

file_w5.close()

#rodzaj_materialu
file_w6 = open('rodzaj_materialu.csv', 'w')
 
rodzaj_materialu = ['plyn do spryskiwaczy', 'paliwo', 'gaz', 'ropa', 'olej']

file_w6.write('rodzaj_materialu\n')

for i in range(len(rodzaj_materialu)):
	file_w6.write(rodzaj_materialu[i]+'\n')

file_w6.close()

#czynnosc
file_w7 = open('czynnosc.csv', 'w')
 
czynnosc = ['naprawa', 'wymiana']

file_w7.write('czynnosc\n')

for i in range(len(czynnosc)):
	file_w7.write(czynnosc[i]+'\n')

file_w7.close()

#pojad
file_w8 = open('pojazd.csv', 'w')

file_w8.write('id_typ_pojazdu,id_marki,id_modelu,id_wymagana_kat_p_j,przebieg,rodz_paliwa,nr_rejestr\n')
paliwo = ['benzyna', 'olej', 'gaz', 'ropa']

for j in range(750):
	pojazd_int = [1,random.randint(1,len(marka)),random.randint(1,len(model)),random.randint(1,len(kategoria_prawa_jazdy))]
	pojazd_float = random.uniform(0,300000)
	pal = paliwo[random.randint(0,len(paliwo)-1)]
	nr_rejstr = 'WI' + str(random.randint(10000,99999))
	for i in range(len(pojazd_int)):
		file_w8.write(str(pojazd_int[i])+',')
	file_w8.write(str(pojazd_float)+',')
	file_w8.write(pal+',')
	file_w8.write(nr_rejstr+'\n')
for j in range(50):
	pojazd_int = [2,random.randint(1,len(marka)),random.randint(1,len(model)),random.randint(1,len(kategoria_prawa_jazdy))]
	pojazd_float = random.uniform(0,300000)
	pal = paliwo[random.randint(0,len(paliwo)-1)]
	nr_rejstr = 'WI' + str(random.randint(10000,99999))
	for i in range(len(pojazd_int)):
		file_w8.write(str(pojazd_int[i])+',')
	file_w8.write(str(pojazd_float)+',')
	file_w8.write(pal+',')
	file_w8.write(nr_rejstr+'\n')
for j in range(1000):
	pojazd_int = [3,random.randint(1,len(marka)),random.randint(1,len(model)),random.randint(1,len(kategoria_prawa_jazdy))]
	pojazd_float = random.uniform(0,300000)
	pal = paliwo[random.randint(0,len(paliwo)-1)]
	nr_rejstr = 'WI' + str(random.randint(10000,99999))
	for i in range(len(pojazd_int)):
		file_w8.write(str(pojazd_int[i])+',')
	file_w8.write(str(pojazd_float)+',')
	file_w8.write(pal+',')
	file_w8.write(nr_rejstr+'\n')

file_w8.close()

#kierowcy
file_w9 = open('kierowcy.csv', 'w')

file_w9.write('imie,nazwisko,PESEL,id_zastepcy,id_kat_prawa_jazdy,id_pojazdu\n')
imiona = read_words('imiona.txt')
nazwiska = read_words('nazwiska.txt')
pesele = read_words('pesele.txt')

for i in range(60):
	imie = imiona[random.randint(0,len(imiona)-1)]
	nazwisko = nazwiska[random.randint(0,len(nazwiska)-1)]
	while True:
		id_zastepcy = random.randint(1,60)
		if id_zastepcy != i:
			break
	kat = random.randint(1,len(kategoria_prawa_jazdy))
	file_w9.write(str(imie)+',')
	file_w9.write(str(nazwisko)+',')
	file_w9.write(str(pesele[i])+',')
	file_w9.write(str(id_zastepcy)+',')
	file_w9.write(str(kat)+',')
	file_w9.write(str(random.randint(1,1800))+'\n')

file_w9.close()

#przyczepa
file_w10 = open('przyczepa.csv', 'w')
file_w10.write('id_marki,id_modelu,numer_rejstr\n')

for i in range(20):
	nr_rejstr_przyczepy = 'WI' + str(random.randint(10000,99999))
	file_w10.write(str(random.randint(1,len(marka)))+',')
	file_w10.write(str(random.randint(1,len(model)))+',')
	file_w10.write(nr_rejstr_przyczepy+'\n')
	
file_w10.close()
