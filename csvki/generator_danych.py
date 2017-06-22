#!/usr/bin/python

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

#file_w.write('marka\n')

for i in range(len(marka)):
	file_w.write(marka[i]+'\n')

file_w.close()
	
#typ_pojazdu
file_w1 = open('typ_pojazdu.csv', 'w')
 
typ_pojazdu = ['ciezarowy', 'osobowy', 'dostawczy']

#file_w1.write('typ_pojazdu\n')

for i in range(len(typ_pojazdu)):
	file_w1.write(typ_pojazdu[i]+'\n')

file_w1.close() 

#model
file_w2 = open('model.csv', 'w')
 
model = ['Corvette', 'Crossfire', 'Civic', '2', '3', '5', '6', '126p', 'Punto', 'Avensis', 'Corolla', 'Touring', 'Coupe', 'Klasa E', 'Klasa G', 'Lancer', 'Colt', 'a4', 'a6']

#file_w2.write('model\n')

for i in range(len(model)):
	file_w2.write(model[i]+'\n')

file_w2.close() 

#Kategoria_prawa_jazdy
file_w3 = open('kategoria_prawa_jazdy.csv', 'w')
 
kategoria_prawa_jazdy = ['AM', 'A1', 'A2', 'A', 'B1', 'B', 'C1', 'C', 'D1', 'D', 'BE', 'C1E', 'CE', 'D1E', 'DE', 'T']

#file_w3.write('kategoria_prawa_jazdy\n')

for i in range(len(kategoria_prawa_jazdy)):
	file_w3.write(kategoria_prawa_jazdy[i]+'\n')

file_w3.close()

#Rodzaj_ladunku
file_w4 = open('rodzaj_ladunku.csv', 'w')
 
rodzaj_ladunku = ['ladunki wysokiej wartosci', 'szybko psujace sie', 'sypkie', 'ponadgabarytowe', 'niebezpieczne', 'plynne', 'drobnicowe']

#file_w4.write('rodzaj_ladunku\n')

for i in range(len(rodzaj_ladunku)):
	file_w4.write(rodzaj_ladunku[i]+'\n')

file_w4.close()

#Czesci_samochodowe
file_w5 = open('czesc_samochodowa.csv', 'w')
 
czesci_samochodowe = ['akumulator', 'przeguby', 'silnik', 'drazki kierownicze', 'karoseria', 'wycieraczki', 'instalacja elektryczna', 'turbosprezarka', 'klimatyzacja', 'skrzynia biegow', 'waly i polosie']

#file_w5.write('czesc_samochodowa\n')

for i in range(len(czesci_samochodowe)):
	file_w5.write(czesci_samochodowe[i]+'\n')

file_w5.close()

#rodzaj_materialu
file_w6 = open('rodzaj_materialu.csv', 'w')
 
rodzaj_materialu = ['plyn do spryskiwaczy', 'paliwo', 'gaz', 'ropa', 'olej']

#file_w6.write('rodzaj_materialu\n')

for i in range(len(rodzaj_materialu)):
	file_w6.write(rodzaj_materialu[i]+'\n')

file_w6.close()

#czynnosc
file_w7 = open('czynnosc.csv', 'w')
 
czynnosc = ['naprawa', 'wymiana','przeglad']

#file_w7.write('czynnosc\n')

for i in range(len(czynnosc)):
	file_w7.write(czynnosc[i]+'\n')

file_w7.close()

#pojad
file_w8 = open('pojazd.csv', 'w')

#file_w8.write('id_typ_pojazdu,id_marki,id_modelu,id_wymagana_kat_p_j,przebieg,rodz_paliwa,nr_rejestr\n')
paliwo = ['benzyna', 'olej', 'gaz', 'ropa']

for j in range(750):#ciezarowe
	pojazd_int = [1,random.randint(1,len(marka)),random.randint(1,len(model)),random.randint(1,len(kategoria_prawa_jazdy))]
	pojazd_float = random.uniform(0,300000)
	pal = paliwo[random.randint(0,len(paliwo)-1)]
	nr_rejstr = 'WI' + str(random.randint(10000,99999))
	for i in range(len(pojazd_int)):
		file_w8.write(str(pojazd_int[i])+',')
	file_w8.write(str(pojazd_float)+',')
	file_w8.write(pal+',')
	file_w8.write(nr_rejstr+'\n')
for j in range(50):#osobowe
	pojazd_int = [2,random.randint(1,len(marka)),random.randint(1,len(model)),random.randint(1,len(kategoria_prawa_jazdy))]
	pojazd_float = random.uniform(0,300000)
	pal = paliwo[random.randint(0,len(paliwo)-1)]
	nr_rejstr = 'WI' + str(random.randint(10000,99999))
	for i in range(len(pojazd_int)):
		file_w8.write(str(pojazd_int[i])+',')
	file_w8.write(str(pojazd_float)+',')
	file_w8.write(pal+',')
	file_w8.write(nr_rejstr+'\n')
for j in range(1000):#dostawcze
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

#file_w9.write('imie,nazwisko,PESEL,id_zastepcy,id_kat_prawa_jazdy,id_pojazdu\n')
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
#file_w10.write('id_marki,id_modelu,numer_rejstr\n')

for i in range(20):
	nr_rejestr = 'WI' + str(random.randint(10000,99999))
	file_w10.write(str(random.randint(1,len(marka)))+',')
	file_w10.write(str(random.randint(1,len(model)))+',')
	file_w10.write(nr_rejestr+'\n')
	
file_w10.close()

#historia_przejazdu
#ciezarowy:500x10, dostawczy:5x365x10 
file_w11 = open('historia_przejazdu.csv', 'w')
#file_w11.write('id_kierowcy,id_pojazdu,miejsce_stratu,'+
		#'miejsce_docelowe,liczba_km,srednie_zuzycie_paliwa,'
         #       'cena_zuzytego_paliwa,data,przyczepa,id_przyczepy\n')
kierowcy = read_words('kierowcy.csv')
miasta = read_words('miasta.txt')
miasta_pol = read_words('miasta_pol.txt')
for rok in range(2007,2018):
	for i in range(500):#ciezarowe
		id_kierowcy = random.randint(1,len(kierowcy))
		id_pojazdu = random.randint(1,751)
		miejsce_startu = miasta[random.randint(0,len(miasta)-1)]
		while True:
			miejsce_docelowe=miasta[random.randint(0,len(miasta)-1)]
			if miejsce_docelowe!=miejsce_startu:
				break
		liczba_km = random.uniform(500,5000)
		srednie_zuzycie_paliwa = random.uniform(5,13)
		cena_zuzytego_paliwa = liczba_km*(srednie_zuzycie_paliwa/100)*4
		dzien = random.randint(1,28)
		miesiac = random.randint(1,12)
		przyczepa = random.choice(['true', 'false'])
		if przyczepa=='true':
			id_przyczepy = random.randint(1,20)
		else:
			id_przyczepy = None
		file_w11.write(str(id_kierowcy)+',')
		file_w11.write(str(id_pojazdu)+',')
		file_w11.write(str(miejsce_startu)+',')
		file_w11.write(str(miejsce_docelowe)+',')
		file_w11.write(str(liczba_km)+',')
		file_w11.write(str(srednie_zuzycie_paliwa)+',')
		file_w11.write(str(cena_zuzytego_paliwa)+',')
		file_w11.write(str(rok)+'-'+str(miesiac)+'-'+str(dzien)+',')
		file_w11.write(przyczepa+',')
		file_w11.write(str(id_przyczepy)+'\n')
	for i in range(1000):#osobowe
		id_kierowcy = random.randint(1,len(kierowcy))
		id_pojazdu = random.randint(752,801)
		miejsce_startu = miasta[random.randint(0,len(miasta)-1)]
		while True:
			miejsce_docelowe=miasta[random.randint(0,len(miasta)-1)]
			if miejsce_docelowe!=miejsce_startu:
				break
		liczba_km = random.uniform(500,5000)
		srednie_zuzycie_paliwa = random.uniform(5,13)
		cena_zuzytego_paliwa = liczba_km*(srednie_zuzycie_paliwa/100)*4
		dzien = random.randint(1,28)
		miesiac = random.randint(1,12)
		przyczepa = random.choice(['true', 'false'])
		if przyczepa=='true':
			id_przyczepy = random.randint(1,20)
		else:
			id_przyczepy = None
		file_w11.write(str(id_kierowcy)+',')
		file_w11.write(str(id_pojazdu)+',')
		file_w11.write(str(miejsce_startu)+',')
		file_w11.write(str(miejsce_docelowe)+',')
		file_w11.write(str(liczba_km)+',')
		file_w11.write(str(srednie_zuzycie_paliwa)+',')
		file_w11.write(str(cena_zuzytego_paliwa)+',')
		file_w11.write(str(rok)+'-'+str(miesiac)+'-'+str(dzien)+',')
		file_w11.write(przyczepa+',')
		file_w11.write(str(id_przyczepy)+'\n')
	for miesiac in range(1,12):
		for dzien in range(1,28):
			for i in range(5):#dostawcze
				id_kierowcy = random.randint(1,len(kierowcy))
				id_pojazdu = random.randint(802,1800)
				miejsce_startu = miasta_pol[random.randint(0,len(miasta_pol)-1)]
				while True:
					miejsce_docelowe=miasta_pol[random.randint(0,len(miasta_pol)-1)]
					if miejsce_docelowe!=miejsce_startu:
						break
				liczba_km = random.uniform(30,500)
				srednie_zuzycie_paliwa = random.uniform(5,13)
				cena_zuzytego_paliwa = liczba_km*(srednie_zuzycie_paliwa/100)*4
				przyczepa = random.choice(['true', 'false'])
				if przyczepa=='true':
					id_przyczepy = random.randint(1,20)
				else:
					id_przyczepy = None
				file_w11.write(str(id_kierowcy)+',')
				file_w11.write(str(id_pojazdu)+',')
				file_w11.write(str(miejsce_startu)+',')
				file_w11.write(str(miejsce_docelowe)+',')
				file_w11.write(str(liczba_km)+',')
				file_w11.write(str(srednie_zuzycie_paliwa)+',')
				file_w11.write(str(cena_zuzytego_paliwa)+',')
				file_w11.write(str(rok)+'-'+str(miesiac)+'-'+str(dzien)+',')
				file_w11.write(przyczepa+',')
				file_w11.write(str(id_przyczepy)+'\n')

file_w11.close()
	

#mandat
file_w12 = open('mandat.csv', 'w')
#file_w12.write('id_kierowcy,data,oplata,pkt_karne,id_historii\n')
historia = read_words('historia_przejazdu.csv')

for i in range(10):
	id_historii = random.randint(1,len(historia))
	dane_historii = historia[id_historii].split(',')
	id_kierowcy = dane_historii[0]
	data = dane_historii[7]
	oplata = random.randint(50,1000)
	pkt_karne = random.randint(1,10)
	file_w12.write(str(id_kierowcy)+',')
	file_w12.write(str(data)+',')
	file_w12.write(str(oplata)+',')
	file_w12.write(str(pkt_karne)+',')
	file_w12.write(str(id_historii)+'\n')

file_w12.close()


#ladunek
file_w13 = open('ladunek.csv', 'w')
for id_historii in range(1,len(historia)):
	waga = random.uniform(100,10000)
	id_rodzaju = random.randint(1,len(rodzaj_ladunku))
	file_w13.write(str(id_rodzaju)+',')
	file_w13.write(str(waga)+',')
	file_w13.write(str(id_historii)+'\n')
file_w13.close()

#serwis
file_w14 = open('serwis.csv', 'w')
#id_pojazdu,data,id_przedmiotu,id_czynnosci,miejsce_serwisu,cena
for rok in range(2007,2018):
	for i in range(750):#ciezarowe
		id_pojazdu = random.randint(1,751)
		dzien = random.randint(1,28)
		miesiac = random.randint(1,12)
		id_czynnosci = random.randint(1,len(czynnosc))
		id_przedmiotu = None
		if czynnosc[id_czynnosci-1]!='przeglad':
			id_przedmiotu = random.randint(1,len(czesci_samochodowe))		
		miejsce_serwisu = miasta_pol[random.randint(0,len(miasta_pol)-1)]
		cena = random.uniform(100,5000)
		file_w14.write(str(id_pojazdu)+',')
		file_w14.write(str(rok)+'-'+str(miesiac)+'-'+str(dzien)+',')
		file_w14.write(str(id_przedmiotu)+',')
		file_w14.write(str(id_czynnosci)+',')
		file_w14.write(str(miejsce_serwisu)+',')
		file_w14.write(str(cena)+'\n')
	for i in range(1000):#dostawcze
		id_pojazdu = random.randint(801,1800)
		dzien = random.randint(1,28)
		miesiac = random.randint(1,12)
		id_czynnosci = random.randint(1,len(czynnosc))
		id_przedmiotu = None
		if czynnosc[id_czynnosci-1]!='przeglad':
			id_przedmiotu = random.randint(1,len(czesci_samochodowe))		
		miejsce_serwisu = miasta_pol[random.randint(0,len(miasta_pol)-1)]
		cena = random.uniform(100,5000)
		file_w14.write(str(id_pojazdu)+',')
		file_w14.write(str(rok)+'-'+str(miesiac)+'-'+str(dzien)+',')
		file_w14.write(str(id_przedmiotu)+',')
		file_w14.write(str(id_czynnosci)+',')
		file_w14.write(str(miejsce_serwisu)+',')
		file_w14.write(str(cena)+'\n')
	for j in range(5):
		for i in range(50):#osobowe
			id_pojazdu = random.randint(751,800)
			dzien = random.randint(1,28)
			miesiac = random.randint(1,12)
			id_czynnosci = random.randint(1,len(czynnosc))
			id_przedmiotu = None
			if czynnosc[id_czynnosci-1]!='przeglad':
				id_przedmiotu = random.randint(1,len	(czesci_samochodowe))		
			miejsce_serwisu = miasta_pol[random.randint(0,len	(miasta_pol)-1)]
			cena = random.uniform(100,5000)
			file_w14.write(str(id_pojazdu)+',')
			file_w14.write(str(rok)+'-'+str(miesiac)+'-'+str(dzien)+',')
			file_w14.write(str(id_przedmiotu)+',')
			file_w14.write(str(id_czynnosci)+',')
			file_w14.write(str(miejsce_serwisu)+',')
			file_w14.write(str(cena)+'\n')
file_w14.close()

#materialy
file_w15 = open('materialy_eksploatacyjne.csv', 'w')
#id_rodzaj_materialu,cena,data_zakupu,litry
for rok in range(2007,2018):
	for miesiac in range(1,12):
		id_rodzaju_materialu = random.randint(1,len(rodzaj_materialu))
		cena = random.uniform(100,1000)
		dzien = random.randint(1,28)
		litry = random.uniform(100,1000)
		file_w15.write(str(id_rodzaju_materialu)+',')
		file_w15.write(str(cena)+',')
		file_w15.write(str(rok)+'-'+str(miesiac)+'-'+str(dzien)+',')
		file_w15.write(str(litry)+'\n')
file_w15.close()

